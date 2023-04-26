package com.ben._06_thread_state;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-03 16:42
 */
public class Test2Daemon {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (true) {
                GlobalLogger.LOGGER.debug("running...");
                if(Thread.currentThread().isInterrupted()) {
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            GlobalLogger.LOGGER.debug("结束");
        },"t1");
        t1.setDaemon(true);//将t1设置为守护进程
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("结束");
    }//主线程结束，t1线程不会结束
}
