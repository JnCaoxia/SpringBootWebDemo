package com.caox.service.impl;

import com.caox.service.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/3 9:42
 */
@Service
public class IRedisServiceImpl implements IRedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String get(String key) {
        return get(key, String.class);
    }

    @Override
    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }

    @Override
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public <T> T get(String key, Class<T> cls) {
        Object val = redisTemplate.opsForValue().get(key);
        if (val==null){
            return null;
        }
        if (val.getClass().isAssignableFrom(cls)) {
            return (T) val;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Boolean setNx(String key, Object val) {
        return redisTemplate.opsForValue().setIfAbsent(key, val);
    }

    @Override
    public Object getSet(String key, Object val) {
        return redisTemplate.opsForValue().getAndSet(key, val);
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
}
