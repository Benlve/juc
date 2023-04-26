package com.ben_01_reentrantlock;

import com.log.GlobalLogger;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 特性一：可重入性
 * @create 2023-03-03 13:39
 */
public class Test2LockWaitTime {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try{
                lock.lock();

                GlobalLogger.LOGGER.debug("start");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                GlobalLogger.LOGGER.debug("end");



            }finally {
                lock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try{
                lock.lock();

                GlobalLogger.LOGGER.debug("start");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                GlobalLogger.LOGGER.debug("end");

            }finally {
                lock.unlock();
            }
        }, "t2");

        t1.start();

        t2.start();


    }


}
