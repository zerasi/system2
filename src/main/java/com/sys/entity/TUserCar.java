package com.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TUserCar extends BaseEntity<Integer> {

    private Integer user_id;

    private String license_plate;

    private String bak1;

    private String bak2;

    private String bak3;

}