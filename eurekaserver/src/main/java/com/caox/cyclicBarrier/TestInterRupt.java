package com.caox.cyclicBarrier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/11 16:41
 */
public class TestInterRupt {
    static Map<Integer,Thread> threads=new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("发令枪响了，跑！");

            }
        });

        for (int i = 0; i < 5; i++) {
            MyThread t = new MyThread(barrier, "运动员" + i + "号", i);
            threads.put(i, t);
            t.start();
        }
        Thread.sleep(3000);
        threads.get(1).interrupt();
    }
}
