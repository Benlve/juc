package com.ben._04_interrupt;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-03 16:20
 */
public class Test5Interrupted {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //两个判断线程是否被打断的方法
            Thread.currentThread().isInterrupted();//不清除打断标记
            Thread.interrupted();//清除打断标记，志伟false
        }, "t1");
        t1.start();

    }
}
