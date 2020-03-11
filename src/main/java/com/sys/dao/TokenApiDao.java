package com.sys.dao;

import com.sys.entity.AipToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TokenApiDao {

    @Select("select type,apiKey,secretKey,token,status_cd from api_token where type = #{type}")
    AipToken getaipToken(String type);

    @Update("UPDATE api_token set token = #{token} ,last_update_time = #{last_update_time} " +
            "where type = #{type} and status_cd = '1'")
    int refreshToken(AipToken aipToken);
}
