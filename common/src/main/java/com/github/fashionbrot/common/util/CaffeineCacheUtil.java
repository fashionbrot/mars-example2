package com.github.fashionbrot.common.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CaffeineCacheUtil {

    static Cache<String, String> cache = Caffeine.newBuilder()
            // 数量上限
            .maximumSize(1024)
            // 过期机制
            .expireAfterWrite(5, TimeUnit.MINUTES)
            // 弱引用key
            .weakKeys()
            // 弱引用value
            .weakValues()
            // 剔除监听
            .removalListener((RemovalListener<String, String>) (key, value, cause) ->
                    log.debug("key:" + key + ", value:" + value + ", 删除原因:" + cause.toString()))
            .build();

    public static void setCache(String key ,String value){
        cache.put(key,value);
    }

    public static String getCache(String key){
        return cache.getIfPresent(key);
    }


}
