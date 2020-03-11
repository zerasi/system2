package com.sys.dao;

import com.sys.entity.TPark;
import com.sys.entity.TParkExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TParkMapper {
    int countByExample(TParkExample example);

    int deleteByExample(TParkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPark record);

    int insertSelective(TPark record);

    List<TPark> selectByExample(TParkExample example);

    TPark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPark record, @Param("example") TParkExample example);

    int updateByExample(@Param("record") TPark record, @Param("example") TParkExample example);

    int updateByPrimaryKeySelective(TPark record);

    int updateByPrimaryKey(TPark record);

    @Select("SELECT count(1) FROM t_park WHERE park_type = #{park_type} and park_status = '2'")
    Integer countByType(TPark tPark);

    @Select("SELECT count(1) FROM t_park WHERE park_type = #{park_type} ")
    Integer countByTypeAll(TPark tPark);

    @Select("SELECT count(1) count,DATE_FORMAT(t1.start_date,'%H') as hours  FROM t_order t1 INNER JOIN t_park t2 ON t1.park_id = t2.id " +
            "GROUP BY DATE_FORMAT(t1.start_date,'%H')")
    List<Map<String, Object>> statisticsPark3();

    @Select("SELECT count(1) count,DATE_FORMAT(t1.end_date,'%H') as hours  FROM t_order t1 INNER JOIN t_park t2 ON t1.park_id = t2.id where t1.end_date is not null  " +
            "GROUP BY DATE_FORMAT(t1.end_date,'%H')")
    List<Map<String, Object>> statisticsPark4();
}