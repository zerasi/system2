package com.sys.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sys.dao.TOrderMapper;
import com.sys.dto.TOrderDto;
import com.sys.entity.TOrder;
import com.sys.entity.TPay;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("pay")
@Slf4j
public class PayController {

    //应用的APPID
    private final String APP_ID = "2016101700711131";
    //生成的应用私钥
    private final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUkvFGMV6GbYTgpoFVciL2XC4KB3ubDCFdCSMFbym/gvwmOP/wU2IuPMxVMA6x0MUnC6GhzNdOxGCqganqtJoERYc7d/ibVUfuXgnZ98RRosIDZVL/pq6jNB1lL5jd9XGCUkmwBEGNKENSPyxlEIcyDMVB3ZtdowOIWP/06fAJ6/OwtiersxVXXKm0m3QTB4ni7BimjvBCSa9BlQiXc6S20ijvx4yFIELldkVsH4pbj8BXDEtCGs1b3CnJD4eR7EH5yd32cVAzHDIKUpge1lJWe3QRnWjqP51L5rLJJ3CSTdQjWODob9cqCqnAFp7mJ7YH5ng38DW0g4kRke8hN6EjAgMBAAECggEBAIKLWlRCrLuRH2AE7T8WuoGdHRbS3Fivn+EPViZANn5/qO4sxzVzDqFx6lgvkbnLpm9YWPzB76zV/9nRdGs1pN57+QbXsylJFZrsJdvPSEAysiYpsXPQEUJ1WB7AY6EjOEk55GU0WCSfbAeoWmmyzBe+ANSO0yTDRydWjA+2PaN4wp06nAIuP4vHzfKaPSWZoXU3S6D1TtPP2MuUspMCR7f9oxPjH7bGHsgmPD6q83GiFHMWrR5ckX/kP1CN5uX5kzXSa1T87LJdubLS+os10D7IehVpOZO2Dgj+HxePTNV06l63GVpIei2gGKIMIlpzHz8E69HiVRmEBn5tZsDaUQECgYEA6bSQRyi8ndBZXTwBbNZRFPaL4r8E4bUqrxHNECp9lorzDoMujlGdLCpa+pu7uei+VeMd6t3R/NUBjdEtgL2b0aikjn7k9ZX5WmpcHdlMv052s9FwHi/zeToppEw9CQim3PKF02gBPt8F2wtkI5tFRALKTeQdor8mSmHeJxRMv1sCgYEAor9X6X0DZ7EW9W6JK97KMyHmAQR8QibXPqjtm1opOM3iQUFAWQ/96DgPraoEs/Cx8u4Vo80ozmiZB7XQkQT3MyWo5bvMFGkqC0NvskJ6KL3KUChnjXZMRoA4ydJHozIC3hC85SoSLRJaWZ1qjHoXUtrpL/zWdIhsHHYj3FSc19kCgYEAkh7jVpqR09pBBZpPCc22RHUiYVMtqjGrzmpC3Ki0fTvDXH+IzFMbAlI5MBfO+B4dY3XtbrviX0KPBiyTVrs2gY//01hjr2llzrJRhhgndDWK4kNA5p70jAdhoehJASz3p427PsL4hGbNMnec1OZwGGXmN205WlhJiJhIXgbDNaECgYBUpJmvaQJtWpArQ7r54xxcraO3IcMSkKrf/vHdG25XCdVDxREXgRa4QPf+hW9+uo+CXktI7vnrKEF23hvEYMXEhzCpkxuX/f4M75zXJHXuXI8tU2ZQD5eqDyP563CwsuEE58WmllPvtxzggNuahiE9SXeAKsrKvu0jfFo4TJVqOQKBgCXuiFdQOuQmj0nzS1zuGdDpSpl84A16/p6sjj6Uk59ltOFbYT5O4rrekvUw60fIcDXG1zq3HXkaCrJWxe+eqY24eDEYByXS2x9JiWPy58CmmXNgOnvw4AZV1v4/3Q53/aKHeeajk2wlQzgOZ0Nv77ZyKkCfGoINJKuMKooCkEXR";
    private final String CHARSET = "UTF-8";
    //支付宝公钥
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqFIJUrlf0qZzMVSvl1Gl6QVkUxi496c62aOlj+jLVaParw6Zyx/FY5e8WX8OJq2h27sIdOUTQ2KFQKo/ACYm3faOYki+LkqX55lQ+JSf1xxh8TZL+q4ofc0hkDPBpWg6e5beNgjdkUG4hiv5XkVQtlEMe5C3IPNPno/sey97ERTzpZLlhnPqcEtQNndsjIZkaSAipDxsVB0haMexsMEJR16ssfD+lF1U4zAoI43s/9o896J4CNJRro3hELPr2WsQkL5sy5oUr1yUH7pRzOaurCgGfSBM51QS4cWGBBc1nbW0sNEuUg+zwt3vtQyrCm7+JY8ttnC+SOmSr44N3zZAewIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://127.0.0.1/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8001/pay/returnUrl";

    private final String strDateFormat = "yyyyMMddHHmmss";

    @Autowired
    private TOrderMapper tOrderMapper;

    @GetMapping("alipay")
    @ApiOperation(value = "支付包扫码支付", notes = "支付包扫码支付")//描述
    public void alipay(HttpServletResponse httpResponse, TOrderDto tOrderDto) throws IOException {
        TOrder tOrder = tOrderMapper.selectByPrimaryKey(tOrderDto.getId());
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
        String out_trade_no = UUID.randomUUID().toString();
        //付款金额，必填
        String total_amount = tOrder.getCount_money().toString();
        //订单名称，必填
        String subject =sdf.format(tOrder.getStart_date())+"停车费用";
        //商品描述，可空
        String body = sdf.format(tOrder.getStart_date())+"停车费用";

        String requestStr = "{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        log.info("调用阿里支付接口入参报文："+requestStr);
        request.setBizContent(requestStr);
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //插入缴费信息表
        TPay tPay = new TPay();
        tPay.setOut_trade_no(out_trade_no);
        tPay.setTotal_amount(total_amount);
        tPay.setSubject(subject);
        tPay.setBody(body);
        tPay.setOrder_id(tOrder.getId());
        tPay.setUser_id(tOrder.getUser_id());
        tPay.setCreate_date(new Date());
        tPay.setStatus_cd("1");
        this.tOrderMapper.insertPayMsg(tPay);

        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public void returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        log.info("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+total_amount);

            //支付成功，修复支付状态
            TPay tPay = this.tOrderMapper.selectByOut_trade_no(out_trade_no);
            this.tOrderMapper.updatePayStatusByOtn(out_trade_no);
            TOrder tOrder = new TOrder();
            tOrder.setId(tPay.getOrder_id());
            tOrder.setStatus_cd("3");
            this.tOrderMapper.updateByPrimaryKeySelective(tOrder);

            log.info("回调成功-----------------------------------------");
            response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
            response.getWriter().write("<html><body><script type='text/javascript'>alert('支付成功,请返回商户');window.close();</script></body></html>");

        }else{
            response.getWriter().write("<script language=JavaScript>alert('支付成功,请返回商户');window.close();</script>");
        }
        response.getWriter().flush();
        response.getWriter().close();

    }

}


