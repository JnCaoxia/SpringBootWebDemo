package com.caox.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/11/27 16:07
 */
public class ReentrantReadWriteLockCacheSystem {
    //这里为了实现简单，将缓存大小设置为4。
    Map<String, String> cacheMap = new HashMap<>(4);
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();



}
