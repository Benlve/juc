package com.ben._06_thread_state;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-03 16:49
 */
public class Test3ThreadState {
    public static void main(String[] args) {
        //1.NEW
        Thread t1 = new Thread(() -> {

        }, "t1");

        GlobalLogger.LOGGER.debug("t1 state = " + t1.getState());

        //2.RUNNABLE
        Thread t2 = new Thread(() -> {
            for (; ; ) {
            }
        }, "t2");
        t2.start();

        try {
            t2.join(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("t2 state = " + t2.getState());

        //3.TERMINATED
        Thread t3 = new Thread(() -> {
            GlobalLogger.LOGGER.debug("结束~");
        }, "t3");
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("t3 state = " + t3.getState());


        //4.TIMED_WAITING
        Object lock = new Object();
        Thread t4 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(100 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "t4");
        t4.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("t4 state = " + t4.getState());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //5.BLOCKED
        Thread t5 = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t5");
        t5.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GlobalLogger.LOGGER.debug("t5 state = " + t5.getState());

        //6.WAITING
        Thread t6 = new Thread(() -> {
            try {
                t5.join(10 * 1000);//TIMED_WAITING
                t5.join();//WAITING
//                synchronized (Test3ThreadState.class) {
//                    Test3ThreadState.class.wait();    //WAITING
//                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t6");
        t6.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("t6 state = " + t6.getState());


        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);

    }
}
