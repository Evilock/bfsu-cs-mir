package com.shiye.mir.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Json工具类
 */
@Slf4j
public class JsonUtils {
    public static String toJsonString(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            log.error("[toJsonString][exception]", e);
            return "";
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            log.error("[parseObject][exception]", e);
            return null;
        }

    }


    public static <T> List<T> toList(String jsonString, Class<T> clz) {
        try {
            return JSON.parseArray(jsonString, clz);
        } catch (Exception e) {
            log.error("[toList][exception]", e);
            return null;
        }
    }
}
