package com.ben._01_safe;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 用面向对象的思想解决线程安全问题
 * @create 2023-03-03 17:57
 */
public class Test3OOPSync {
    static final int LOOP_NUM = 5000;
    public static void main(String[] args) throws InterruptedException {

        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < LOOP_NUM; i++) {
                room.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < LOOP_NUM; i++) {
                room.decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        room.getCount();
        GlobalLogger.LOGGER.debug("count = " + room.getCount());
    }
}

//保护共享变量的类
class Room {
    //共享变量
    private int count;

    public int getCount() {
        return count;
    }

    //当前对象当锁，自增
    public synchronized void increment() {
        count++;
    }

    //当前对象当锁，自减
    public synchronized void decrement() {
        count--;
    }
}
