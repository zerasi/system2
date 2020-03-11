package com.sys.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sys.dto.TOrderDto;
import com.sys.entity.LoginUser;
import com.sys.entity.TOrder;
import com.sys.entity.TOrderExample;
import com.sys.service.TOrderService;
import com.sys.utils.PageResult;
import com.sys.utils.PageTableRequest;
import com.sys.utils.Results;
import com.sys.utils.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("order")
@Slf4j
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

    @PostMapping("/start")
    @ApiOperation(value = "模拟车辆驶入", notes = "模拟车辆驶入")//描述
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:do')")
    public Results orderStart(@RequestParam("myFile") MultipartFile multfile, @RequestParam("park_id") Integer park_id){
        try {
            // 获取文件名
            String fileName = multfile.getOriginalFilename();
            // 获取文件后缀
            String suffix=fileName.substring(fileName.lastIndexOf("."));
            // 用uuid作为文件名，防止生成的临时文件重复
            final File newFile = File.createTempFile(String.valueOf(UUID.randomUUID()), suffix);
            // MultipartFile to File
            multfile.transferTo(newFile);
            Results results = this.tOrderService.start(newFile, park_id);
            //程序结束时，删除临时文件
            deleteFile(newFile);
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return Results.failure();
        }
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

    @GetMapping("/end")
    @ApiOperation(value = "模拟车辆驶出", notes = "模拟车辆驶出")//描述
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:do')")
    public Results orderEnd(TOrderDto tOrderDto){
        return this.tOrderService.orderEnd(tOrderDto);
    }

    @PostMapping("/listByPageAndUserid")
    @ApiOperation(value = "个人缴费", notes = "个人缴费，查看个人所有订单信息")//描述
    @ResponseBody
    public PageResult getAllOrderByUserId(PageTableRequest request){
        //获取登录用户信息
        LoginUser loginUser = (LoginUser) SecurityUtils.getCurrentUserAuthentication().getPrincipal();
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andUser_idEqualTo(loginUser.getId().intValue());

        request.countOffset();
        return tOrderService.getAllOrderByUserId(request.getOffset(),request.getRows(),tOrderExample);
    }

    @PostMapping("/listByPage")
    @ApiOperation(value = "订单管理", notes = "查看订单信息")//描述
    @PreAuthorize("hasAuthority('sys:order:query')")
    @ResponseBody
    public PageResult getAllOrder(PageTableRequest request){
        request.countOffset();
        return tOrderService.getAllOrder(request.getOffset(),request.getRows());
    }

}
