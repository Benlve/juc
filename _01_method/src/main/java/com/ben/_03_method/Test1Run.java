package com.ben._03_method;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 调用线程对象的run方法和start方法的比较
 * @create 2023-03-01 22:29
 */
public class Test1Run {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            GlobalLogger.LOGGER.debug("执行...");
        }, "t1");

        t1.run();//在main线程执行

        t1.start();//在t1线程中执行

    }
}
