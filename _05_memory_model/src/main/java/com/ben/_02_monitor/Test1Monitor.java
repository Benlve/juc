package com.ben._02_monitor;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 实现监控线程类
 * @create 2023-03-02 09:25
 */
public class Test1Monitor {

}

class Monitor {

    private Thread monitorT;

    public void start() {

        monitorT = new Thread(()->{
            while(true) {
                GlobalLogger.LOGGER.debug("执行监控...");
                try {
                    Thread.sleep(2000);//每隔2秒执行一次监控
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(Thread.currentThread().isInterrupted()) {

                }
            }
        });

        monitorT.start();


    }

    public void stop() {

    }


}
