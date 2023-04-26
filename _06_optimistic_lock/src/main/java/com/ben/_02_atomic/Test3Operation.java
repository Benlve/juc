package com.ben._02_atomic;

import com.log.GlobalLogger;

import java.util.concurrent.atomic.AtomicInteger;

public class Test3Operation {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.updateAndGet(value -> value * 5);
        GlobalLogger.LOGGER.debug("atomicInteger = " + atomicInteger.get());
        GlobalLogger.LOGGER.debug("atomicInteger = " + atomicInteger.toString());
    }
}
