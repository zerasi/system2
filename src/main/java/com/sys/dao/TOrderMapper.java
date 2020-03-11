package com.sys.dao;

import com.sys.entity.SysPermission;
import com.sys.entity.TOrder;
import com.sys.entity.TOrderExample;
import java.util.List;

import com.sys.entity.TPay;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TOrderMapper {
    int countByExample(TOrderExample example);

    int deleteByExample(TOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    List<TOrder> selectByExample(TOrderExample example);

    TOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByExample(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    @Insert("INSERT into t_pay(out_trade_no,total_amount,subject,body,order_id,create_date,status_cd,user_id,bak1,bak2) " +
            "VALUES(#{out_trade_no},#{total_amount},#{subject},#{body},#{order_id},#{create_date},#{status_cd},#{user_id},#{bak1},#{bak2}) ")
    int insertPayMsg(TPay tpay);

    @Select("select t.out_trade_no,t.total_amount,t.subject,t.body,t.order_id,t.create_date,t.status_cd,t.user_id,t.bak1,t.bak2 " +
            "from t_pay t where t.out_trade_no = #{out_trade_no}" )
    TPay selectByOut_trade_no(@Param("out_trade_no") String out_trade_no);

    @Update("UPDATE t_pay set status_cd = '2' WHERE out_trade_no = #{out_trade_no}")
    int updatePayStatusByOtn(@Param("out_trade_no") String out_trade_no);

}