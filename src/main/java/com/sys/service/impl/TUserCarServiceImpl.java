package com.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sys.dao.TUserCarMapper;
import com.sys.dao.TokenApiDao;
import com.sys.entity.AipToken;
import com.sys.entity.TUserCar;
import com.sys.entity.TUserCarExample;
import com.sys.service.TUserCarService;
import com.sys.utils.PageResult;
import com.sys.utils.Results;
import com.sys.utils.aop.StoredFile;
import com.sys.utils.baidu.LicensePlate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TUserCarServiceImpl implements TUserCarService {

    @Autowired
    private TUserCarMapper tUserCarMapper;

    @Autowired
    private TokenApiDao tokenApiDao;

    @Override
    public PageResult getUserCars(Integer offset, Integer rows, TUserCarExample tUserCarExample) {
        PageHelper.startPage(offset,rows);
        Page<TUserCar> pages = (Page<TUserCar>) this.tUserCarMapper.selectByExample(tUserCarExample);
        return new PageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    //@StoredFile
    public Results saveUserCar(File newFile, TUserCar tUserCar) {
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

        //车辆只可一个用户拥有
        TUserCarExample tUserCarExample = new TUserCarExample();
        tUserCarExample.createCriteria().andLicense_plateEqualTo(license_plate);
        List<TUserCar> list = this.tUserCarMapper.selectByExample(tUserCarExample);
        if(list.size()>0){
            //存在其他用户拥有 不可新增
            if(tUserCar.getId()==null){
                return Results.failure(282104,"车辆只可一个用户拥有");
            }
            if(!tUserCar.getId().equals(list.get(0).getId())){
                return Results.failure(282104,"车辆只可一个用户拥有");
            }
        }

        tUserCar.setLicense_plate(license_plate);
        if(tUserCar.getId()==null){
            this.tUserCarMapper.insert(tUserCar);
            return Results.success();
        }
        this.tUserCarMapper.updateByPrimaryKeySelective(tUserCar);
        return Results.success();
    }

    @Override
    public Results deleteCarByIds(List<Integer> carIds) {
        carIds.forEach( p ->{
            this.tUserCarMapper.deleteByPrimaryKey(p);
        });
        return Results.success();
    }
}
