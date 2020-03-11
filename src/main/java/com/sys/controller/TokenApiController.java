package com.sys.controller;

import com.sys.dao.TokenApiDao;
import com.sys.entity.AipToken;
import com.sys.utils.Results;
import com.sys.utils.baidu.utils.AuthService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("token")
@Slf4j
public class TokenApiController {

    @Autowired
    private TokenApiDao tokenApiDao;

    @GetMapping("refresh/{type}")
    @ResponseBody
    @ApiOperation(value = "刷新token", notes = "token一次大概可以使用十几天 链接" +
            "/token/refresh/license_plate")
    public Results refreshToken(@PathVariable("type") String type){
        AipToken aipToken = this.tokenApiDao.getaipToken(type);

        //更新token
        String newToken = AuthService.getAuth(aipToken.getApiKey(),aipToken.getSecretKey());

        //保存到数据库
        aipToken.setToken(newToken);
        aipToken.setLast_update_time(new Date());

        this.tokenApiDao.refreshToken(aipToken);
        return Results.success();

    }
}
