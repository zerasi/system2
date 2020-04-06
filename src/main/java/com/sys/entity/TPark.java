package com.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class TPark extends BaseEntity<Integer>{

    private String park_name;

    /**
     * 停车位类型 1小车位 2大车位
     */
    private String park_type;

    /**
     * 价格 15分钟 超出不足15分钟 按15分钟计算
     */
    private BigDecimal park_price;
    private BigDecimal park_price_big;

    /**
     * 1 未使用  2已被使用 3已被预订
     */
    private String park_status;

    private String park_des;

    private String bak1;

    private String bak2;

    //停靠车辆
    private String license_plate;

    private Integer order_id;

    private SysUser user;

}