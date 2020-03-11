package com.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TOrder extends BaseEntity<Integer>{

    /**
     * 车牌
     */
    private String license_plate;

    private Integer park_id;

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date start_date;

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss",timezone="GMT+8")
    private Date end_date;

    private String license_img;

    private Integer user_id;

    /**
     * 1 车辆入场 2车辆出场未付款 3已付款
     */
    private String status_cd;

    //需要交付总金额
    private BigDecimal count_money;

    private String bak1;

    private String bak2;

    private TPark tPark;

    private SysUser sysUser;

}