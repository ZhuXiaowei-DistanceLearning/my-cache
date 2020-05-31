package com.zxw;

import com.zxw.cache.Cache;
import com.zxw.cache.CacheManager;
import com.zxw.cache.support.SimpleCacheManager;

import javax.xml.transform.Source;

/**
 * @author zxw
 * @date 2020/5/31 16:52
 */
public class TestController {
    public static void main(String[] args) {
        CacheManager cacheManager = new SimpleCacheManager();
        Cache<Object, Object> cache = cacheManager.getCache("key");
        System.out.println(cache);
        System.out.println("***");
        Cache<Object, Object> cache2 = cacheManager.getCache("key");
        System.out.println(cache2);
    }
}
