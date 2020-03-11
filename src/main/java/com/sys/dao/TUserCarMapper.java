package com.sys.dao;

import com.sys.entity.TUserCar;
import com.sys.entity.TUserCarExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TUserCarMapper {
    int countByExample(TUserCarExample example);

    int deleteByExample(TUserCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserCar record);

    int insertSelective(TUserCar record);

    List<TUserCar> selectByExample(TUserCarExample example);

    TUserCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserCar record, @Param("example") TUserCarExample example);

    int updateByExample(@Param("record") TUserCar record, @Param("example") TUserCarExample example);

    int updateByPrimaryKeySelective(TUserCar record);

    int updateByPrimaryKey(TUserCar record);
}