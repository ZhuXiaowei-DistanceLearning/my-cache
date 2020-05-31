package com.zxw.cache;

import java.util.Collection;

/**
 * 提供并维护Cache实例的生命周期
 *
 * @author zxw
 * @date 2020/5/31 13:28
 */
public interface CacheManager {
    /**
     * 根据名称获取Cache
     * @param name
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Cache<K, V> getCache(String name);

    /**
     * 获取所有的Cache名称
     * @return
     */
    Collection<String> getCacheNames();
}
