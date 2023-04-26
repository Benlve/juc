package com.ben._01_safe;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 两个线程分别对共享变量自增5000和自减5000
 * @create 2023-03-03 17:51
 */
public class Test1Safe {

    static int num = 0;
    private static final int LOOP_NUM = 5000;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < LOOP_NUM; i++) {
                num++;  //自增5000
            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < LOOP_NUM; i++) {
                num--;  //自减5000
            }
        },"t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        GlobalLogger.LOGGER.debug("num = " + num);
    }
}
