package com.caox.cyclicBarrier;



import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/11 16:18
 */
public class MyThread extends Thread {
    private CyclicBarrier cyclicBarrier;
    private String name;
    private int ID;

    public MyThread(CyclicBarrier cyclicBarrier, String name, int ID) {
        super();
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
        this.ID = ID;
    }

    @Override
    public void run() {
        System.out.println(name + "开始准备");
        try {
            //不同运动员准备时间不一样，方便模拟不同情况
            Thread.sleep(ID*1000);
            System.out.println(name + "准备完毕！等待发令枪");
            try {
                cyclicBarrier.await();
                System.out.println(name + "跑完了路程！");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                System.out.println(name+"看不见起跑线了");
            }
            System.out.println(name+"退场！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


