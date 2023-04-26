package com.ben._04_interrupt;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription interrupt打断阻塞状态的而线程
 * @create 2023-03-02 09:51
 */
public class Test1Interrupt {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
                GlobalLogger.LOGGER.debug("打印异常前的操作...");
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("interrupt");
        t1.interrupt();
        GlobalLogger.LOGGER.debug("t1打断标记：" + t1.isInterrupted());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("t1打断标记：" + t1.isInterrupted());
    }
}
