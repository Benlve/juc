package com.ben._02_thread_state_tools;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription jps jstack 查看线程状态
 * @create 2023-03-01 21:26
 */
public class Test1JPS {
    private static final Object lock = new Object();
    static boolean isFinish = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!isFinish) {
                    GlobalLogger.LOGGER.debug("条件不满足...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                GlobalLogger.LOGGER.debug("条件满足，退出线程~");

            }


        }, "t1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (lock) {
            isFinish = true;
            GlobalLogger.LOGGER.debug("唤醒t1，让他结束！");
            lock.notify();
        }

    }
}
