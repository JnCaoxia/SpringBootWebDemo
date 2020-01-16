package com.caox.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/11/29 8:49
 */
public class ChikenStore {
    ReentrantLock reentrantLock = new ReentrantLock();

    Condition productCondition = reentrantLock.newCondition();

    Condition consumeCondition = reentrantLock.newCondition();

    private int count = 0;

    private volatile boolean isHaveChicken = false;

    //生产
    public void ProductChicken() {
        reentrantLock.lock();
        while (isHaveChicken) {
            try {
                System.out.println("有烤鸡了" + Thread.currentThread().getName() + "不生产了");
                productCondition.await();
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "产生了第" + count + "个烤鸡，赶紧开始卖");
        isHaveChicken = true;
        consumeCondition.signal();
        reentrantLock.unlock();
    }

    public void SellChicken() {
        reentrantLock.lock();
        while (!isHaveChicken) {
            try {
                System.out.println("没有烤鸡了" + Thread.currentThread().getName() + "不卖了");
                consumeCondition.await();
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
            }
        }
        count--;
        isHaveChicken = false;
        System.out.println(Thread.currentThread().getName() + "卖掉了第" + count + 1 + "个烤鸡，赶紧开始生产");
        productCondition.signal();
        reentrantLock.unlock();
    }

    public static void main(String[] args) {
        ChikenStore chikenStore = new ChikenStore();
        new Thread(() -> {
            Thread.currentThread().setName("生产者1号");
            while (true) {
                chikenStore.ProductChicken();
            }
        }).start();
        new Thread(() -> {
            Thread.currentThread().setName("生产者2号");
            for (; ; ) {
                chikenStore.ProductChicken();
            }
        }).start();
        new Thread(() -> {
            Thread.currentThread().setName("消费者1号");
            while (true) {
                chikenStore.SellChicken();
            }
        }).start();
        new Thread(() -> {
            Thread.currentThread().setName("消费者2号");
            while (true) {
                chikenStore.SellChicken();
            }
        }).start();

    }
}
