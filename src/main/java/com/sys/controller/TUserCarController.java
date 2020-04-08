package com.sys.controller;

import com.sys.dto.TParkDto;
import com.sys.dto.TUserCarDto;
import com.sys.entity.LoginUser;
import com.sys.entity.TOrderExample;
import com.sys.entity.TUserCar;
import com.sys.entity.TUserCarExample;
import com.sys.service.TUserCarService;
import com.sys.utils.PageResult;
import com.sys.utils.PageTableRequest;
import com.sys.utils.Results;
import com.sys.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("userCar")
@Slf4j
public class TUserCarController {

    @Autowired
    private TUserCarService tUserCarService;

    @PostMapping("/listByPage")
    @ApiOperation(value = "分页展示", notes = "分页展示个人车辆")
    @ApiImplicitParam(name = "request",value = "分页查询实体类",required = true)
    @PreAuthorize("hasAuthority('sys:car:do')")
    @ResponseBody
    public PageResult getUserCars(PageTableRequest request){

        //获取登录用户信息
        LoginUser loginUser = (LoginUser) SecurityUtils.getCurrentUserAuthentication().getPrincipal();

        TUserCarExample tUserCarExample = new TUserCarExample();
        tUserCarExample.createCriteria().andUser_idEqualTo(loginUser.getId().intValue());

        request.countOffset();
        return tUserCarService.getUserCars(request.getOffset(),request.getRows(),tUserCarExample);
    }

    @PostMapping("/save")
    @ApiOperation(value = "用户车辆添加修改",notes = "用户车辆添加修改")
    @PreAuthorize("hasAuthority('sys:car:do')")
    @ResponseBody
    public Results saveUserCar(@RequestParam("myFile") MultipartFile multfile, @RequestParam("id") Integer id){
        try {
            // 获取文件名
            String fileName = multfile.getOriginalFilename();
            // 获取文件后缀
            String suffix=fileName.substring(fileName.lastIndexOf("."));
            // 用uuid作为文件名，防止生成的临时文件重复
            final File newFile = File.createTempFile(String.valueOf(UUID.randomUUID()), suffix);
            // MultipartFile to File
            multfile.transferTo(newFile);
            System.out.println(newFile.getAbsolutePath());
            //获取登录用户信息
            LoginUser loginUser = (LoginUser) SecurityUtils.getCurrentUserAuthentication().getPrincipal();

            TUserCar tUserCar = new TUserCar();
            tUserCar.setId(id);
            tUserCar.setUser_id(loginUser.getId().intValue());
            Results results = this.tUserCarService.saveUserCar(newFile, tUserCar);
            //程序结束时，删除临时文件
            deleteFile(newFile);
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return Results.failure();
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:car:do')")
    @ApiOperation(value = "批量删除车辆", notes = "批量删除车辆")//描述
    public Results deleteCarByIds(@RequestBody TUserCarDto tUserCar) {
        return this.tUserCarService.deleteCarByIds(tUserCar.getCarIds());
    }

    /**
     * 删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
