package com.shiye.mir.utils;

import com.google.gson.Gson;
import com.shiye.mir.entity.dto.UrlResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String DOMAINUSER ="vipmessage";
    public static String TOKEN ="8u36q9d63g96hqlr";

    /**
     * 调用getPassword接口
     */
    public static String interfaceUtil(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = br.readLine();
            is.close();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPassword(String domainuser, String token) {
        Gson gson = new Gson();
        String jsonData = interfaceUtil("http://itpwd.qiyi.domain/api/GetPassword?domainuser=" + domainuser+"&token="+ token);
        UrlResult result= gson.fromJson(jsonData, UrlResult.class);
        return result.getPassword();

    }
    public static void main(String[] args){
        System.out.println(getPassword(DOMAINUSER,TOKEN));
    }

}
