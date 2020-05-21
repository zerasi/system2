package com.sys.service;

import com.sys.dto.TOrderDto;
import com.sys.entity.TOrderExample;
import com.sys.utils.PageResult;
import com.sys.utils.Results;

import java.io.File;
import java.io.IOException;

public interface TOrderService {

    Results start(File newFile, Integer park_id) throws IOException;

    Results orderEnd(TOrderDto tOrderDto);

    PageResult getAllOrderByUserId(Integer offset, Integer rows, TOrderExample tOrderExample);

    PageResult getAllOrder(Integer offset, Integer rows);

    Results startBylicensePlate(String license_plate, Integer park_id);
}
