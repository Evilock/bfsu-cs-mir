package com.shiye.mir.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.*;
import com.shiye.mir.config.AlipayConfig;
import com.shiye.mir.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 支付宝实现类
 * @author fangshaozu
 */
@Slf4j
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {
    /** 调取支付宝接口 web端支付 */
    DefaultAlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json",
            AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
    /** 调取支付宝接口app端支付 */
    AlipayClient alipayClients = new DefaultAlipayClient(
            AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json",
            AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);


    @Override
    public String webPagePay(String outTradeNo, BigDecimal totalAmount, String subject) throws Exception {
        System.out.println("serviceHERE！");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+outTradeNo+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+totalAmount+"," +
                "    \"subject\":\""+subject+"\"," +
                "    \"body\":\""+"商品介绍"+"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //转换格式
        //TODO 为null
        return alipayClient.pageExecute(alipayRequest).getBody().replace('\"','\'').replace('\n',' ');
    }

    @Override
    public String appPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {
        AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //销售产品码（固定）
        String productCode="QUICK_WAP_WAY";
        //进行赋值
        AlipayTradeWapPayModel wapPayModel=new AlipayTradeWapPayModel();
        wapPayModel.setOutTradeNo(outTradeNo);
        wapPayModel.setSubject(subject);
        wapPayModel.setTotalAmount(totalAmount.toString());
        wapPayModel.setBody("商品名称");
        wapPayModel.setTimeoutExpress("200000m");
        wapPayModel.setProductCode(productCode);
        alipayRequest.setBizModel(wapPayModel);
        //格式转换
        return alipayClients.pageExecute(alipayRequest).getBody().replace('\"','\'').replace('\n',' ');
    }

    @Override
    public String refund(String outTradeNo, String refundReason, Integer refundAmount, String outRequestNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        //调取接口
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"refund_amount\":\""+ refundAmount +"\","
                + "\"refund_reason\":\""+ refundReason +"\","
                + "\"out_request_no\":\""+ outRequestNo +"\"}");
        return alipayClient.execute(alipayRequest).getBody();
    }

    @Override
    public String query(String outTradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        //请求接口
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","+"\"trade_no\":\""+ "" +"\"}");
        //转换格式
        return alipayClient.execute(alipayRequest).getBody();
    }

    @Override
    public String close(String outTradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\"," +"\"trade_no\":\""+ "" +"\"}");
        return alipayClient.execute(alipayRequest).getBody();
    }

    @Override
    public String refundQuery(String outTradeNo, String outRequestNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        //请求接口
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                +"\"out_request_no\":\""+ outRequestNo +"\"}");
        //格式转换
        return alipayClient.execute(alipayRequest).getBody();
    }
}
