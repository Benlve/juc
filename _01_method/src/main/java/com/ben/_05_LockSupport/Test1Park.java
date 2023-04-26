package com.ben._05_LockSupport;

import com.log.GlobalLogger;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription juc下的LockSupport
 * @create 2023-03-03 16:28
 */
public class Test1Park {
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{

            GlobalLogger.LOGGER.debug("执行...");

            GlobalLogger.LOGGER.debug("park");

            LockSupport.park();

//            GlobalLogger.LOGGER.debug("unpark");

            GlobalLogger.LOGGER.debug("线程打断状态：" + Thread.currentThread().isInterrupted());

        },"t1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("interrupt");
        t1.interrupt();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("t1线程打断状态:" + t1.isInterrupted());
        //        GlobalLogger.LOGGER.debug("unpark");
//        LockSupport.unpark(t1);

    }
}
