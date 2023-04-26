package com.ben._01_create;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 继承Thread类，重写run方法
 * @create 2023-03-01 20:56
 */
public class Test1Extends {
    public static void main(String[] args) {
        //1.创建线程子类对象，重写run方法
        Thread t1 = new Thread() {//此时线程还只是Java层的对象，还未关联到操作系统的线程
            @Override
            public void run() {
                GlobalLogger.LOGGER.debug("print");
            }
        };
        //2.给线程起名字
        t1.setName("t1");
        //3.启动线程
        t1.start();//关联操作系统的线程，由操作系统调度


    }
}
