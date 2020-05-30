package com.zxw.cache;

import java.util.concurrent.Callable;

/**
 * @author zxw
 * Cache缓存基础操作类
 * 提升程序性能
 * @date 2020/5/30 18:47
 */
public interface Cache<K, V> {
    /**
     * 获取对应的Value
     *
     * @param Key
     * @return
     */
    V get(K Key);

    /**
     * 将Value转成K的类型
     *
     * @param key
     * @param type
     * @return
     */
    V get(K key, Class<K> type);

    /**
     * @param key
     * @param valueLoader
     * @return
     */
    V get(K key, Callable<? extends V> valueLoader);

    /**
     * 添加缓存
     *
     * @param key
     * @param val
     */
    void put(K key, V val);

    /**
     * 清除K的值
     *
     * @param key
     */
    void evict(Object key);

    /**
     * 清除所有
     */
    void clear();

    /**
     * 返回entries的数量
     * @return
     */
    long size();

    /**
     * 如果该值尚未有关联,则该值尚未设置
     * @param key
     * @param val
     * @return
     */
    default V putIfAbsent(K key, V val) {
        V v = get(key);
        if (v == null) {
            put(key, val);
        }
        return v;
    }
}

