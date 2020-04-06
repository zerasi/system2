package com.sys.service.impl;

import com.alipay.api.domain.AlipayYebLqdDataResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sys.dao.TOrderMapper;
import com.sys.dao.TParkMapper;
import com.sys.dao.UserDao;
import com.sys.dto.TParkDto;
import com.sys.entity.*;
import com.sys.service.TParkService;
import com.sys.utils.PageResult;
import com.sys.utils.ResponseCode;
import com.sys.utils.Results;
import com.sys.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class TParkServiceImpl implements TParkService {

    @Autowired
    private TParkMapper tParkMapper;

    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public PageResult getAllParksByPage(Integer offset, Integer rows,TParkExample tParkExample) {
        PageHelper.startPage(offset,rows);
        Page<TPark> pages = (Page<TPark>)this.tParkMapper.selectByExample(tParkExample);
        List<TPark> list = pages.getResult();
        list.forEach(p ->{
            if(p.getBak1()!=null && !"".equals(p.getBak1())){
                p.setUser(this.userDao.getById(Long.parseLong(p.getBak1())));
            }
        });
        return new PageResult(pages.getTotal(), list);
    }

    @Override
    public PageResult getAllParksByPage_yd(Integer offset, Integer rows,TParkExample tParkExample) {
        PageHelper.startPage(offset,rows);
        //获取登录用户信息
        LoginUser loginUser = (LoginUser) SecurityUtils.getCurrentUserAuthentication().getPrincipal();
        Page<TPark> pages = (Page<TPark>)this.tParkMapper.selectByExample(tParkExample);
        List<TPark> list = pages.getResult();
        list.forEach(p ->{
            if(p.getBak1()!=null && !"".equals(p.getBak1())){
                p.setUser(this.userDao.getById(Long.parseLong(p.getBak1())));
                if(p.getBak1().equals(loginUser.getId().toString())){
                    p.setBak2("yes");
                }
            }
        });
        return new PageResult(pages.getTotal(), list);
    }

    @Override
    public Results park_yd(Integer id) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getCurrentUserAuthentication().getPrincipal();
        TPark tPark = this.tParkMapper.selectByPrimaryKey(id);
        tPark.setBak1(loginUser.getId().toString());
        tPark.setPark_status("3");
        this.tParkMapper.updateByPrimaryKey(tPark);
        return Results.success("预定成功");
    }

    @Override
    public Results park_yd_cansole(Integer id) {
        TPark tPark = this.tParkMapper.selectByPrimaryKey(id);
        tPark.setBak1(null);
        tPark.setPark_status("1");
        this.tParkMapper.updateByPrimaryKey(tPark);
        return Results.success("取消成功");
    }

    @Override
    public Results addPark(TParkDto tParkDto) {
        //添加
        tParkDto.setPark_status("1");
        this.tParkMapper.insertSelective(tParkDto);
        return Results.success();
    }

    @Override
    public Results editPark(TParkDto tParkDto) {
        //修改
        this.tParkMapper.updateByPrimaryKeySelective(tParkDto);
        return Results.success();
    }

    @Override
    public Results deleteParkByParkIds(List<Integer> parkIds) {
        parkIds.forEach(p ->{
            this.tParkMapper.deleteByPrimaryKey(p);
        });
        return Results.success();
    }

    @Override
    public PageResult getParksDo(Integer offset, Integer rows) {
        PageHelper.startPage(offset,rows);
        Page<TPark> pages = (Page<TPark>)this.tParkMapper.selectByExample(new TParkExample());
        List<TPark> list = pages.getResult();
        list.forEach(p ->{
            if(p.getBak1()!=null && !"".equals(p.getBak1())){
                p.setUser(this.userDao.getById(Long.parseLong(p.getBak1())));
            }
        });
        return new PageResult(pages.getTotal(), list);
    }

    @Override
    public Results getParksDashboard(TPark tPark) {
        Integer countByType = this.tParkMapper.countByType(tPark);
        Integer countAll = this.tParkMapper.countByTypeAll(tPark);

        BigDecimal decimal1 = new BigDecimal(countByType);
        BigDecimal decimal2 = new BigDecimal(countAll);
        BigDecimal decimal3 = new BigDecimal(100);

        String result = decimal1.divide(decimal2,2,BigDecimal.ROUND_HALF_UP).multiply(decimal3).toString();
        return Results.success(result);
    }

    @Override
    public Results statisticsPark(String flag) {
        List<Map<String,Object>> dtoList = new ArrayList<>();
        if(flag.equals("3")){
            dtoList = this.tParkMapper.statisticsPark3();
        }else if(flag.equals("4")){
            dtoList = this.tParkMapper.statisticsPark4();
        }

        List<Object> resultsList = new ArrayList<>();
        for (int i = 0 ;i<=23 ; i++){
            boolean f = true;
            for(Map<String, Object> map: dtoList){
                if(String.format("%02d",i).equals(map.get("hours"))){
                    resultsList.add(map.get("count"));
                    f = false;
                    break;
                }
            }
            if(f){
                resultsList.add(0);
            }
        }
        return Results.success(ResponseCode.SUCCESS,resultsList);
    }
}
