package com.zxw.cache.support;

import com.zxw.cache.Cache;
import com.zxw.cache.concurrent.ConcurrentMapCache;

import java.util.Collection;
import java.util.Collections;

/**
 * @author zxw
 * @date 2020/5/31 14:17
 */
public class SimpleCacheManager extends AbstractCacheManager {
    private Collection<? extends Cache> caches = Collections.emptySet();

    /**
     * Specify the collection of Cache instances to use for this CacheManager.
     */
    public void setCaches(Collection<? extends Cache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection loadCaches() {
        return this.caches;
    }

    @Override
    public Cache CreateCache(String name) {
        return new ConcurrentMapCache(name);
    }
}
