package com.ben._01_create;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription Lambda表达式
 * @create 2023-03-01 21:09
 */
public class Test3Lambda {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            GlobalLogger.LOGGER.debug("执行...");
        },"t1");
        t1.start();
    }
}
