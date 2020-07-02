package com.caox.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/11/25 16:23
 */
public class AtomicStampedReferenceExample {
    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    private static AtomicStampedReference atomicStampedReference =
            new AtomicStampedReference(100,1);

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {

        //AtomicInteger
        Thread at1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(100,110);
                atomicInteger.compareAndSet(110,100);
            }
        });

        Thread at2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);      // at1,执行完
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("AtomicInteger:" + atomicInteger.compareAndSet(100,120));

            }
        });

        at1.start();
        at2.start();

        at1.join();
        at2.join();

        Thread tsf1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                try {
                    //让 tsf2先获取stamp，导致预期时间戳不一致
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 预期引用：100，更新后的引用：110，预期标识getStamp() 更新后的标识getStamp() + 1
                atomicStampedReference.compareAndSet(100, 110,
                        atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);  // 100-1  ,110-2
                System.out.println("第一次reference : " +  atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(110,100,
                        atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1); // 110-2, 100-3
//                atomicStampedReference.compareAndSet(100,110,
//                        stamp,stamp + 1);
//                atomicStampedReference.compareAndSet(110,100,
//                        stamp,stamp + 1);
                System.out.println("第二次reference : " +  atomicStampedReference.getStamp());

            }
        });

        Thread tsf2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();

                try {
                    TimeUnit.SECONDS.sleep(2);      //线程tsf1执行完
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("AtomicStampedReference:"
                        +atomicStampedReference.compareAndSet(100,120,stamp, stamp + 1)); // 100-1, 120-2
                System.out.println("第三次reference : " +  stamp);
            }
        });

        tsf1.start();
        tsf2.start();


    }

}