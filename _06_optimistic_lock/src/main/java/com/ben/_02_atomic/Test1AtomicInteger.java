package com.ben._02_atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Test1AtomicInteger {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.incrementAndGet());//++i
        System.out.println(atomicInteger.getAndIncrement());//i++
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.getAndAdd(10));

        System.out.println(atomicInteger.addAndGet(10));

        System.out.println(atomicInteger.get());


    }
}
