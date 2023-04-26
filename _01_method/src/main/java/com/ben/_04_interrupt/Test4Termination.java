package com.ben._04_interrupt;

import com.log.GlobalLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 两阶段终止模式应用：监控线程实现
 * @create 2023-03-02 10:13
 */
public class Test4Termination {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        GlobalLogger.LOGGER.debug("启动监控线程");
        monitor.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GlobalLogger.LOGGER.debug("停止监控线程");
        monitor.stop();
    }
}

class Monitor {
    private Thread monitor;


    //启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    GlobalLogger.LOGGER.debug("料理后事...");
                    break;
                }

                GlobalLogger.LOGGER.debug("执行监控...");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        });

        monitor.start();
    }

    //停止监控线程
    public void stop() {
        if (monitor != null && monitor.isAlive()) {
            monitor.interrupt();
        } else {
            GlobalLogger.LOGGER.error("monitor has not be init!");
        }
    }

}


