package com.ben._04_interrupt;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 两阶段终止模式，优雅地终止线程
 *      stop() 暴力停止  System.exit() 暴力停止进程
 * @create 2023-03-02 10:07
 */
public class Test3Termination {
    public static void main(String[] args) {

//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        GlobalLogger.LOGGER.debug("before 执行");
//
//        System.exit(0);//暴力停止进程
//
//        GlobalLogger.LOGGER.debug("after 执行");

        Thread t1 =new Thread(() -> {
            while(true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted) {
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        },"t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("stop~");
        t1.stop();

    }
}
