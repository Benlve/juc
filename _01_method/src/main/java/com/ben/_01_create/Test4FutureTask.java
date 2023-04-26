package com.ben._01_create;

import com.log.GlobalLogger;

import java.util.concurrent.*;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription FutureTask实现线程通信，
 * @create 2023-03-01 21:12
 */
public class Test4FutureTask {

    static String name = "Michal";

    public static void main(String[] args) {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return name.toUpperCase();
            }
        };
        FutureTask<Object> task = new FutureTask<>(callable);

        Thread t1 = new Thread(task, "t1");

        t1.start();

        try {
            GlobalLogger.LOGGER.debug("exe get()");
            Object result = task.get(2,TimeUnit.SECONDS);
            GlobalLogger.LOGGER.debug("result = " + result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            GlobalLogger.LOGGER.debug("超时啦！");
        }
    }
}
