package com.sys.service;

import com.sys.entity.TUserCar;
import com.sys.entity.TUserCarExample;
import com.sys.utils.PageResult;
import com.sys.utils.Results;

import java.io.File;
import java.util.List;

public interface TUserCarService {
    PageResult getUserCars(Integer offset, Integer rows, TUserCarExample tUserCarExample);

    Results saveUserCar(File newFile, TUserCar tUserCar);

    Results deleteCarByIds(List<Integer> carIds);
}
