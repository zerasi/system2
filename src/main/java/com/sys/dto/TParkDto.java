package com.sys.dto;

import com.sys.entity.TPark;

import java.util.List;

public class TParkDto extends TPark {

    private List<Integer> parkIds;

    public List<Integer> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<Integer> parkIds) {
        this.parkIds = parkIds;
    }
}
