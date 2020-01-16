package com.caox.cyclicBarrier;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable{

    public ReentrantLock lock = new ReentrantLock();
    public static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                //通过重入锁，防止并发增加Count的值
                lock.lock();
                //重复进入锁
//                lock.lock();
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //使用重入锁，一定要解锁，如果没有解锁会导致其他线程不可以访问。
                lock.unlock();
//                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread t1 = new Thread(reentrantLockDemo);
        Thread t2 = new Thread(reentrantLockDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Over Count="+count);
    }
}