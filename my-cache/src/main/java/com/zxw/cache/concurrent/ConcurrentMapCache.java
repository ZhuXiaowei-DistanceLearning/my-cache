package com.zxw.cache.concurrent;

import com.zxw.cache.AbstractCache;
import com.zxw.util.Assert;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxw
 * @date 2020/5/31 11:00
 */
public class ConcurrentMapCache<K, V> extends AbstractCache<K, V> {
    private final String name;
    private final ConcurrentHashMap<K, V> store;
    private final String serialization;

    /**
     * 使用指定的名称创建一个ConcurrentMapCache
     *
     * @param name
     */
    public ConcurrentMapCache(String name) {
        this(name, new ConcurrentHashMap<>(256), true);
    }

    public ConcurrentMapCache(boolean allowNullValues, String name) {
        this(name, new ConcurrentHashMap<>(256), allowNullValues);
    }

    public ConcurrentMapCache(String name, ConcurrentHashMap<K, V> store, boolean allowNullValues) {
        this(name, store, allowNullValues, null);
    }

    public ConcurrentMapCache(String name, ConcurrentHashMap<K, V> store, boolean allowNullValues, String serialization) {
        super(allowNullValues);
        Assert.isNull(name, "Name must not be null");
        Assert.isNull(store, "store must not be null");
        this.name = name;
        this.store = store;
        this.serialization = serialization;
    }

    @Override
    protected V lookup(K key) {
        return this.store.get(key);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public V get(K key, Callable<? extends V> valueLoader) {
        return this.store.computeIfAbsent(key, k -> {
            try {
                return valueLoader.call();
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        });
    }

    @Override
    public void put(K key, V val) {
        this.store.put(key, val);
    }

    @Override
    public void evict(Object key) {
        this.store.remove(key);
    }

    @Override
    public void clear() {
        this.store.clear();
    }

    @Override
    public long size() {
        return 0;
    }
}
