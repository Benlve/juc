package com.ben._03_method;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TERMINATED状态
 * @create 2023-03-01 22:33
 */
public class Test2StateTerminated {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            GlobalLogger.LOGGER.debug("执行...");
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("t1 线程状态为：" + t1.getState());//TERMINATED
    }
}
