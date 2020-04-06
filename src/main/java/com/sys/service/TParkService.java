package com.sys.service;

import com.sys.dto.TParkDto;
import com.sys.entity.TPark;
import com.sys.entity.TParkExample;
import com.sys.utils.PageResult;
import com.sys.utils.Results;

import java.util.List;

public interface TParkService {
    PageResult getAllParksByPage(Integer offset, Integer rows, TParkExample tParkExample);

    Results addPark(TParkDto tParkDto);

    Results editPark(TParkDto tParkDto);

    Results deleteParkByParkIds(List<Integer> parkIds);

    PageResult getParksDo(Integer offset, Integer rows);

    Results getParksDashboard(TPark tPark);

    Results statisticsPark(String flag);

    PageResult getAllParksByPage_yd(Integer offset, Integer rows, TParkExample tParkExample);

    Results park_yd(Integer id);

    Results park_yd_cansole(Integer id);
}
