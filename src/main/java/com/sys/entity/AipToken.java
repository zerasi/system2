package com.sys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AipToken {
    private String type;
    private String apiKey;
    private String secretKey;
    private String token;
    private String status_cd;//1启用 0弃用
    private Date last_update_time;

}

