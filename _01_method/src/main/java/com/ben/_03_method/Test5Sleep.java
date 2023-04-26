package com.ben._03_method;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 调用sleep方法，防止cpu占用率过高
 * @create 2023-03-01 23:02
 */
public class Test5Sleep {
    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                GlobalLogger.LOGGER.debug("执行...");
            }
        }).start();
    }
}
