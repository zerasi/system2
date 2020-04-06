package com.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sys.dao.*;
import com.sys.dto.TOrderDto;
import com.sys.entity.*;
import com.sys.service.TOrderService;
import com.sys.utils.PageResult;
import com.sys.utils.Results;
import com.sys.utils.baidu.LicensePlate;
import com.sys.utils.baidu.utils.Base64Util;
import com.sys.utils.baidu.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
@Slf4j
public class TOrderServiceImpl implements TOrderService {

    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private TokenApiDao tokenApiDao;

    @Autowired
    private TParkMapper tParkMapper;

    @Autowired
    private TUserCarMapper tUserCarMapper;

    @Autowired
    private RoleUserDao roleUserDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Results start(File newFile, Integer park_id) throws IOException {
        //获取token
        AipToken aipToken = this.tokenApiDao.getaipToken("license_plate");

        //车牌识别
        String resultJson = LicensePlate.licensePlateUtils(newFile,aipToken.getToken());

        JSONObject jsonObject = JSON.parseObject(resultJson);

        String license_plate = null;
        try {
            //解析json报文获取车牌
            license_plate = jsonObject.getJSONObject("words_result").getString("number");
        }catch (RuntimeException e){
            //解析失败 返回无法识别车牌图片
            e.printStackTrace();
            return Results.failure(282103,"无法识别车牌图片");
        }
        TParkExample tParkExample = new TParkExample();
        tParkExample.createCriteria().andLicense_plateEqualTo(license_plate);
        List<TPark> list = this.tParkMapper.selectByExample(tParkExample);
        if(list.size()>0){
            //该车牌汽车已经在停车场了
            return Results.failure(282102,"对不起，该车牌："+license_plate+"汽车已经在停车场了");
        }

        //查询该车牌是否有用户已经注册拥有 没有则注册为临时用户
        TUserCarExample tUserCarExample = new TUserCarExample();
        tUserCarExample.createCriteria().andLicense_plateEqualTo(license_plate);
        List<TUserCar> userCarList = this.tUserCarMapper.selectByExample(tUserCarExample);
        Integer userId = null;

        TPark tPark_yd = this.tParkMapper.selectByPrimaryKey(park_id);
        if(tPark_yd.getPark_status()!=null && tPark_yd.getPark_status().equals("3")){
            //车位被预定
            TUserCarExample tUserCarExample_yd = new TUserCarExample();
            tUserCarExample_yd.createCriteria().andUser_idEqualTo(Integer.parseInt(tPark_yd.getBak1()));
            //获取预定用户的车辆信息
            AtomicBoolean flag = new AtomicBoolean(true);
            List<TUserCar> userCarList_yd = this.tUserCarMapper.selectByExample(tUserCarExample_yd);
            String finalLicense_plate = license_plate;
            userCarList_yd.forEach(p ->{
                if(p.getLicense_plate().equals(finalLicense_plate)){
                    flag.set(false);
                }
            });
            if(flag.get()){
                SysUser sysUser_yd = this.userDao.getById(Long.parseLong(tPark_yd.getBak1()));
                return Results.failure(282103,"车位已经被"+sysUser_yd.getUsername()+"预定，只可此用户车辆停入");
            }
        }

        if(userCarList.size()<=0){
            //注册临时用户 账号车牌号 密码111111
            SysUser sysUser = new SysUser();
            sysUser.setUsername(license_plate);
            sysUser.setPassword(new BCryptPasswordEncoder().encode("111111"));
            sysUser.setStatus(1);
            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());

            userDao.save(sysUser);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(2);//2临时用户 角色id
            sysRoleUser.setUserId(sysUser.getId().intValue());
            roleUserDao.save(sysRoleUser);

            TUserCar tUserCar = new TUserCar();
            tUserCar.setUser_id(sysUser.getId().intValue());
            tUserCar.setLicense_plate(license_plate);
            this.tUserCarMapper.insertSelective(tUserCar);

            userId = sysUser.getId().intValue();
        }else{
            TUserCar tUserCar = userCarList.get(0);
            userId = tUserCar.getUser_id();
        }

        TOrderDto tOrderDto = new TOrderDto();
        tOrderDto.setPark_id(park_id);
        tOrderDto.setLicense_plate(license_plate);
        tOrderDto.setStart_date(new Date());
        tOrderDto.setStatus_cd("1");
        byte[] imgData = FileUtil.readFileByBytes(newFile);
        String imgStr = Base64Util.encode(imgData);
        imgStr = URLEncoder.encode(imgStr, "UTF-8");
        tOrderDto.setLicense_img(imgStr);
        tOrderDto.setUser_id(userId);
        this.tOrderMapper.insertSelective(tOrderDto);

        //修改车位状态 改为2已占有
        TPark tPark = new TPark();
        tPark.setId(park_id);
        tPark.setPark_status("2");
        tPark.setLicense_plate(license_plate);
        tPark.setOrder_id(tOrderDto.getId());
        this.tParkMapper.updateByPrimaryKeySelective(tPark);


        return Results.success();
    }

    @Override
    public Results orderEnd(TOrderDto tOrderDto) {

        //车辆驶出 车位状态改为1 未使用 license_plate置空 order_id置空
        TPark tPark = this.tParkMapper.selectByPrimaryKey(tOrderDto.getPark_id());

        tPark.setPark_status("1");
        tPark.setLicense_plate(null);
        tPark.setOrder_id(null);
        tPark.setBak1(null);
        this.tParkMapper.updateByPrimaryKey(tPark);

        //订单状态改为2车辆出场未付款 计算出需要缴付金额
        TOrder tOrder = this.tOrderMapper.selectByPrimaryKey(tOrderDto.getId());
        tOrder.setEnd_date(new Date());
        tOrder.setStatus_cd("2");
        tOrder.setCount_money(countMoney(tOrder.getStart_date(),tOrder.getEnd_date(),tPark.getPark_price()));
        this.tOrderMapper.updateByPrimaryKeySelective(tOrder);
        return Results.success();
    }

    /**
     *
     * @param start_date 开始时间
     * @param end_date 结束世间
     * @param price 每15分钟价格
     * @return
     */
    public BigDecimal countMoney(Date start_date, Date end_date, BigDecimal price){
        Long between = end_date.getTime() - start_date.getTime();
        Long remain = between%(1000*60*15);
        Long min15 = between/(1000*60*15);
        if(remain == 0L){
            return new BigDecimal(min15).multiply(price);
        }
        return new BigDecimal(min15+1L).multiply(price);
    }


    @Override
    public PageResult getAllOrderByUserId(Integer offset, Integer rows, TOrderExample tOrderExample) {
        PageHelper.startPage(offset,rows);
        Page<TOrder> pages = (Page<TOrder>)this.tOrderMapper.selectByExample(tOrderExample);

        List<TOrder> list = pages.getResult();
        /*Iterator<TOrder> iter = list.iterator();
        while (iter.hasNext()){
            TOrder tOrder = iter.next();
            if(tOrder.getPark_id()!=null){
                tOrder.setTPark(this.tParkMapper.selectByPrimaryKey(tOrder.getPark_id()));
            }
        }*/
        list.forEach(p ->{
            if(p.getPark_id()!=null){
                p.setTPark(this.tParkMapper.selectByPrimaryKey(p.getPark_id()));
            }
        });
        return new PageResult(pages.getTotal(), list);
    }

    @Override
    public PageResult getAllOrder(Integer offset, Integer rows) {
        PageHelper.startPage(offset,rows);
        Page<TOrder> pages = (Page<TOrder>)this.tOrderMapper.selectByExample(new TOrderExample());
        List<TOrder> list = pages.getResult();
        list.forEach(p ->{
            if(p.getPark_id()!=null){
                p.setTPark(this.tParkMapper.selectByPrimaryKey(p.getPark_id()));
            }
            if(p.getUser_id()!=null){
                p.setSysUser(this.userDao.getById(p.getUser_id().longValue()));
            }
        });
        return new PageResult(pages.getTotal(), list);
    }

    public static void main(String[] args) {
        String str = "[{'key':2,'name':'张三'},{'key':1,'name':'张三2'}]";
        JSONArray jsonArray = JSON.parseArray(str);
    }
}
