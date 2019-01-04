package com.caox.annotions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/3 10:48
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisSync {
    //方法执行超时时间（默认2秒）
    long timeout() default 2000;

    //等待执行时间(默认0.05秒)
    int waitTime() default 50;
}
