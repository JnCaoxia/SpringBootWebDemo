package com.caox.service;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/3 9:38
 */
public interface IRedisService {

    String get(String key);

    void set(String key, Object val);

    <T> T get(String key, Class<T> cls);

    Boolean setNx(String key, Object val);

    Object getSet(String key, Object val);

    Boolean del(String key);
}
