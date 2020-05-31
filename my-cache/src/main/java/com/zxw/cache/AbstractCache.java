package com.zxw.cache;

import java.util.IllegalFormatException;
import java.util.concurrent.Callable;

/**
 * @author zxw
 * @date 2020/5/31 10:21
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    private final boolean allowNullValues;

    public AbstractCache(boolean allowNullValues) {
        this.allowNullValues = allowNullValues;
    }

    public final boolean isAllowNullValues() {
        return this.allowNullValues;
    }


    @Override
    public V get(K Key) {
        V v = lookup(Key);
        return v;
    }

    @Override
    public V get(K key, Class<K> type) {
        V v = lookup(key);
        if (v != null && type != null && !type.isInstance(v)) {
            throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]:" + v);
        }
        return v;
    }

    /**
     * 子类实现获取key的逻辑
     *
     * @param key
     * @return
     */
    protected abstract V lookup(K key);
}
