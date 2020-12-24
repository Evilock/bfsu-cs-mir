package com.shiye.mir.config;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存配置
 * @author fangshaozu_sx
 */
@Configuration
public class LocalCacheConfig {
    //TODO 出null
    @Bean(name = "localCacheOne")
    public Cache<String,Object> localCacheOne(){
        return Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }
}
