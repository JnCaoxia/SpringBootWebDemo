package com.caox.aop;

import com.caox.annotions.RedisSync;
import com.caox.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Objects;


/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/3 10:53
 */
@Slf4j
@Aspect
@Component
public class RedisSyncAop {
    private static final Logger logger = LoggerFactory.getLogger(RedisSyncAop.class);
    @Resource
    private IRedisService iRedisService;

    @Pointcut("@annotation(com.caox.annotions.RedisSync)")
    private void anyMethod(){
    }

    @Around("anyMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        //获得锁
        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        String key = method.toString();
        RedisSync redisSync = method.getAnnotation(RedisSync.class);
        long waitTime = redisSync.waitTime();
        long currTime = System.currentTimeMillis();
        Boolean state = iRedisService.setNx(key, currTime);
        long saveTime = 0L;
        while (!state) {
            // 之前存在key 并发开始
            Long tempSaveTime = iRedisService.get(key, Long.class);
            // 若锁被释放
            if (tempSaveTime == null) {
                // 重新加锁
                state = iRedisService.setNx(key, currTime);
                continue;
            }
            // 锁被重新获取
            if (!tempSaveTime.equals(saveTime)) {
                currTime = System.currentTimeMillis();
                saveTime = tempSaveTime;
            }
            // 判断是否超时
            if (saveTime + redisSync.timeout() < currTime) {
                // 超时，直接获得锁  获取上一个锁的时间value
                Object tempTime = iRedisService.getSet(key, currTime);
                if(tempTime == null){
                    state = iRedisService.setNx(key, currTime);
                    continue;
                }
                // 判断锁是否被释放 或 未被抢先获取  saveTime = tempSaveTime; tempTime(获取上一个锁时间value)
                if (Objects.equals(saveTime, tempTime)) {
                    logger.warn("方法：{}，执行超时，已被强制解锁！", key);
                    break;
                }
            }
            // 等待
            if(waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            state = iRedisService.setNx(key, currTime);
        }
        // 执行方法
        result = pjp.proceed();
        Long currSaveTime = iRedisService.get(key, Long.class);
        // 判断锁未被判定为超时
        if (currSaveTime != null && Objects.equals(currSaveTime, currTime)) {
            // 释放锁
            iRedisService.del(key);
        }
        return result;
    }
}
