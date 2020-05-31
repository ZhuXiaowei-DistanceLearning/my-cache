package com.zxw.cache.support;

import com.zxw.cache.Cache;
import com.zxw.cache.CacheManager;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxw
 * @date 2020/5/31 13:34
 */
public abstract class AbstractCacheManager implements CacheManager {
    private final ConcurrentHashMap<String, Cache> cacheMap;
    private volatile Set<String> cacheNames;

    public AbstractCacheManager() {
        this(new ConcurrentHashMap<>(16));
    }

    public AbstractCacheManager(ConcurrentHashMap<String, Cache> cacheMap) {
        this(cacheMap, Collections.emptySet());
    }

    public AbstractCacheManager(ConcurrentHashMap<String, Cache> cacheMap, Set<String> cacheNames) {
        this.cacheMap = cacheMap;
        this.cacheNames = cacheNames;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        Cache cache = this.cacheMap.get(name);
        // 如果存在该cache，则直接返回
        if (cache != null) {
            return cache;
        }
        // 如果cache不存在，则创建一个cache
        Cache missingCache = getMissingCache(name);
        if (missingCache == null) {
            synchronized (this.cacheMap) {
                cache = this.cacheMap.get(name);
                if (cache == null) {
                    cache = CreateCache(name);
                    cacheMap.putIfAbsent(name, cache);
                }
            }
        }
        return cache;
    }

    /**
     * 子类实现创建一个Cache实例
     * @param name
     * @return
     */
    public abstract Cache CreateCache(String name);

    protected abstract Collection<? extends Cache> loadCaches();

    @Override
    public Collection<String> getCacheNames() {
        return cacheNames;
    }

    public Cache getMissingCache(String name) {
        return null;
    }
}
