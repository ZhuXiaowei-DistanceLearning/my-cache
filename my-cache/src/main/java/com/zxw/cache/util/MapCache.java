package com.zxw.cache.util;

import com.zxw.cache.AbstractCache;

import java.util.concurrent.Callable;

/**
 * @author zxw
 * @date 2020/5/31 10:59
 */
public class MapCache<K, V> extends AbstractCache<K, V> {
    public MapCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    @Override
    protected V lookup(K key) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public V get(K key, Callable<? extends V> valueLoader) {
        return null;
    }

    @Override
    public void put(K key, V val) {

    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public long size() {
        return 0;
    }
}
