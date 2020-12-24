package com.shiye.mir.config;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 支付宝配置
 * @author fangshaozu_sx
 */
public class AlipayConfig {

    @Value("${alipay.app-id}")
    public static String APP_ID;

    @Value("${alipay.private-key}")
    public static String APP_PRIVATE_KEY;

    @Value("${alipay.alipay-public-key}")
    public static String ALIPAY_PUBLIC_KEY;

    @Value("${alipay.notify_url}")
    public static String notify_url;

    @Value("${alipay.return_url}")
    public static String return_url;

    @Value("${alipay.sign-type}")
    public static String SIGN_TYPE;

    @Value("${alipay.charset}")
    public static String CHARSET;

    @Value("${alipay.gateway.url}")
    public static String GATEWAY_URL;

    @Value("${alipay.log.path}")
    public static String log_path;


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
