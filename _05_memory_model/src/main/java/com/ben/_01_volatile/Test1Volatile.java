package com.ben._01_volatile;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 并发过程的特性之一：可见性
 *      一个线程对共享变量的修改，对其他线程不可见
 * @create 2023-03-02 09:16
 */
public class Test1Volatile {

    static boolean run = true;//主存数据
    static double num = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while(run) {//编译器优化，将主存的run值读取到共存内存，缓存起来，所以一直读取的是true
                num++;
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        run = false;//即使1秒后，主线程修改了run的值，对t1线程依旧不可见

        while(true) {
            GlobalLogger.LOGGER.debug("num = " + num);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
