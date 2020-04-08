package com.sys.dto;

import com.sys.entity.TUserCar;

import java.util.List;

public class TUserCarDto extends TUserCar {
    private List<Integer> carIds;


    public List<Integer> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<Integer> carIds) {
        this.carIds = carIds;
    }
}
