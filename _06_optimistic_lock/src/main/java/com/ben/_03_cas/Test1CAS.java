package com.ben._03_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 整数类型原子操作
 */
public class Test1CAS {
    public static void main(String[] args) {

        AtomicInteger num = new AtomicInteger(10);

        while (true) {
            //原子乘法操作，乘以五的操作
            int prev = num.get();
            int next = prev * 5;//具体操作

            if (num.compareAndSet(prev, next)) {
                break;
            }
        }

        System.out.println(num);

    }
}
