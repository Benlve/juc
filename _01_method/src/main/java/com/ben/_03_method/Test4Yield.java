package com.ben._03_method;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription yield方法
 * @create 2023-03-01 22:38
 */
public class Test4Yield {

    static final Object lock1 = new Object();
    static int num1 = 0;
    static final Object lock2 = new Object();
    static int num2 = 0;
    static volatile boolean isNeedFinish = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                while (!isNeedFinish) {
                    Thread.yield();
                    num1++;
                }
            }

        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                while (!isNeedFinish) {
                    num2++;
                }
            }

        }, "t2");

        t1.start();
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        isNeedFinish = true;


        GlobalLogger.LOGGER.debug("num1 = " + num1);
        GlobalLogger.LOGGER.debug("num2 = " + num2);
    }
}
