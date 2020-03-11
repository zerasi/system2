package com.sys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TPay {

    private String out_trade_no;
    private String total_amount;
    private String subject;
    private String body;
    private Integer order_id;
    private Date create_date;
    private String status_cd;
    private Integer user_id;
    private String bak1;
    private String bak2;
}
