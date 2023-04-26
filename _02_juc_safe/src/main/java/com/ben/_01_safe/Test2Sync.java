package com.ben._01_safe;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 加锁，防止线程安全问题
 * @create 2023-03-03 17:55
 */
public class Test2Sync {

    static int num = 0;
    private static final int LOOP_NUM = 5000;

    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < LOOP_NUM; i++) {
                synchronized (lock){
                    num++;  //自增5000
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < LOOP_NUM; i++) {
                synchronized (lock){
                    num--;  //自减5000
                }
            }
        },"t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        GlobalLogger.LOGGER.debug("num = " + num);
    }
}
