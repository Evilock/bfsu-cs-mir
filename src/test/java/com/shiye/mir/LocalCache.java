package com.shiye.mir;

import com.github.benmanes.caffeine.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalCache {

    @Resource(name="localCacheOne")
    private Cache<String,Object> localCache;

    @Test
    public void test1(){
        System.out.println("====Test PUT====");
        localCache.put("testKey","testValue");
        System.out.println("====Test GET====");
        System.out.println(localCache.getIfPresent("testKey"));
    }

}
