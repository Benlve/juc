package com.ben._06_thread_state;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-03 16:42
 */
public class Test1Daemon {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    break;
                }
                GlobalLogger.LOGGER.debug("running...");
            }
            GlobalLogger.LOGGER.debug("结束");
        },"t1");
        t1.start();

        GlobalLogger.LOGGER.debug("结束");
    }//主线程结束，t1线程不会结束
}
