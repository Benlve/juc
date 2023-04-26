package com.ben._03_method;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TIMEDWAITING状态
 * @create 2023-03-01 22:35
 */
public class Test3StateTimedWaiting {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("t1 线程的状态为：" + t1.getState());//TIMED_WAITING
    }
}
