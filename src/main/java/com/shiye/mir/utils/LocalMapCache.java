package com.shiye.mir.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 临时本地缓存
 * @author fangshaozu_sx
 */
@Slf4j
@Component
public class LocalMapCache {
    private static Map<String,String> EMAIL_CACHE = new HashMap<>();

    public static Map<String,String> getEmailCache(){
        return EMAIL_CACHE;
    }
}
