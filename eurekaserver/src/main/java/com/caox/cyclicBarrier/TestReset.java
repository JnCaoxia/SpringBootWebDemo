package com.caox.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/11 16:21
 */
public class TestReset {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {

            @Override
            public void run() {
                System.out.println("发令枪响了，跑！");

            }
        });
        for (int i = 0; i < 5; i++) {
            new MyThread(barrier, "运动员" + i + "号", i).start();
        }
        Thread.sleep(1000);
        barrier.reset();

    }
}
