package com.shiye.mir.config;

import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存
 *
 * @author fangshaozu_sx
 */
@Configuration
public class LocalCacheConfig {
    @Bean(name="stringLocalCache")
    public LoadingCache<String, String> expiryCache(){
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .writer(new CacheWriter<String, String>() {
                    @Override
                    public void write(String key, String value) {
                        System.out.println("--缓存写入--:key=" + key + ", value=" + value);
                    }
                    @Override
                    public void delete(String key, String value, RemovalCause cause) { System.out.println("--缓存删除--:key=" + key); }
                })
                //过期时间
                .expireAfterAccess(10, TimeUnit.MINUTES)
                //cacheLoad实现类,刷新时候调用
                .build((String key)->"刷新的数据");
        //loadingCache.put("testKey","testValue");
        return loadingCache;
    }
}
