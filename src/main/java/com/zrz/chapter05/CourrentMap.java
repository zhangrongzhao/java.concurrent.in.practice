package com.zrz.chapter05;

import java.util.Map;

//原子操作
public interface CourrentMap<K,V> extends Map<K,V> {
    //仅当没有相应的映射值时才插入
    V putIfAbsent(K key,V value);

    //boolean remove(K key,V value);

    //仅当K被映射到oldValue时替换为newValue
    boolean replace(K key,V oldValue,V newValue);

    //仅当K被映射到某个值时才替换为newValue
    V replace(K key,V newValue);
}
