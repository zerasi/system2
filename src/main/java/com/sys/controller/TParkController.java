package com.sys.controller;

import com.sys.dto.TParkDto;
import com.sys.dto.UserDto;
import com.sys.entity.TPark;
import com.sys.entity.TParkExample;
import com.sys.service.TParkService;
import com.sys.utils.PageResult;
import com.sys.utils.PageTableRequest;
import com.sys.utils.Results;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("park")
@Slf4j
public class TParkController {

    @Autowired
    private TParkService tParkService;

    @PostMapping("/listByPage_yd")
    @ApiOperation(value = "分页获取车位信息", notes = "分页获取车位信息")//描述
    @ApiImplicitParam(name = "request", value = "分页查询实体类", required=false)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:query')")
    public PageResult getParks_yd(PageTableRequest request,TPark tPark) {
        TParkExample tParkExample = new TParkExample();
        TParkExample.Criteria criteria = tParkExample.createCriteria();
        if(tPark.getPark_name()!=null && !"".equals(tPark.getPark_name())){
            criteria.andPark_nameLike("%"+tPark.getPark_name()+"%");
        }
        if(tPark.getPark_type()!=null && !"".equals(tPark.getPark_type())){
            criteria.andPark_typeEqualTo(tPark.getPark_type());
        }
        if(tPark.getPark_price()!=null){
            criteria.andPark_priceGreaterThanOrEqualTo(tPark.getPark_price());
        }
        if(tPark.getPark_price_big()!=null){
            criteria.andPark_priceLessThanOrEqualTo(tPark.getPark_price_big());
        }
        request.countOffset();
        return tParkService.getAllParksByPage_yd(request.getOffset(),request.getRows(),tParkExample);
    }

    @PostMapping("/listByPage")
    @ApiOperation(value = "分页获取车位信息", notes = "分页获取车位信息")//描述
    @ApiImplicitParam(name = "request", value = "分页查询实体类", required=false)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:query')")
    public PageResult getParks(PageTableRequest request,TPark tPark) {
        TParkExample tParkExample = new TParkExample();
        TParkExample.Criteria criteria = tParkExample.createCriteria();
        if(tPark.getPark_name()!=null && !"".equals(tPark.getPark_name())){
            criteria.andPark_nameLike("%"+tPark.getPark_name()+"%");
        }
        if(tPark.getPark_type()!=null && !"".equals(tPark.getPark_type())){
            criteria.andPark_typeEqualTo(tPark.getPark_type());
        }
        if(tPark.getPark_price()!=null){
            criteria.andPark_priceGreaterThanOrEqualTo(tPark.getPark_price());
        }
        if(tPark.getPark_price_big()!=null){
            criteria.andPark_priceLessThanOrEqualTo(tPark.getPark_price_big());
        }
        request.countOffset();
        return tParkService.getAllParksByPage(request.getOffset(),request.getRows(),tParkExample);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加或修改车位信息", notes = "添加或修改车位信息，根据TParkDto id是否为null区分")
    @ApiImplicitParam(name = "request", value = "添加修改实体类", required=true)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:edit')")
    public Results savePark(TParkDto tParkDto){
        if(tParkDto.getId()==null){
            return this.tParkService.addPark(tParkDto);
        }
        return this.tParkService.editPark(tParkDto);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:del')")
    @ApiOperation(value = "批量删除车位信息", notes = "批量删除车位信息")//描述
    public Results deleteParks(@RequestBody TParkDto tParkDto) {
        return tParkService.deleteParkByParkIds(tParkDto.getParkIds());
    }

    @PostMapping("/listByPage_do")
    @ApiOperation(value = "模拟车辆驶入查询车位状态", notes = "模拟车辆驶入查询车位状态")//描述
    @ApiImplicitParam(name = "request", value = "分页查询实体类", required=false)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:park:do')")
    public PageResult getParksDo(PageTableRequest request) {
        request.countOffset();
        return tParkService.getParksDo(request.getOffset(),request.getRows());
    }

    @PostMapping("/dashboard")
    @ApiOperation(value = "车位监控", notes = "车位监控")//描述
    @ResponseBody
    public Results getParksDashboard(TPark tPark) {
        return tParkService.getParksDashboard(tPark);
    }

    @PostMapping("/statistics_park")
    @ApiOperation(value = "分时段统计进入进出车辆数以及驶入小区车辆数", notes = "分时段统计进入进出车辆数以及驶入小区车辆数")//描述
    @ResponseBody
    public Results statisticsPark(@RequestParam("flag") String flag) {
        return tParkService.statisticsPark(flag);
    }

    @PostMapping("/park_yd")
    @ApiOperation(value = "车位预定")//描述
    @ResponseBody
    public Results park_yd( Integer id) {
        return tParkService.park_yd(id);
    }

    @PostMapping("/park_yd_cansole")
    @ApiOperation(value = "车位预定取消")//描述
    @ResponseBody
    public Results park_yd_cansole( Integer id) {
        return tParkService.park_yd_cansole(id);
    }

}
