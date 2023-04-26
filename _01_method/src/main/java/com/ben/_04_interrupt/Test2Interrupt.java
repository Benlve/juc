package com.ben._04_interrupt;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription interrupt打断RUNNING状态的而线程
 * @create 2023-03-02 10:01
 */
public class Test2Interrupt {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true){
                boolean isInterrupted = Thread.currentThread().isInterrupted();
                if(isInterrupted) {
                    GlobalLogger.LOGGER.debug("被打断...");
                    break;
                }
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("interrupt");
        t1.interrupt();

        GlobalLogger.LOGGER.debug("t1.isInterrupted() = " + t1.isInterrupted());

    }
}
