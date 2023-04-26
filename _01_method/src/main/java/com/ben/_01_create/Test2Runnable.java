package com.ben._01_create;

import com.log.GlobalLogger;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription 创建线程对象，构造方法传参Runnable实现类对象
 * @create 2023-03-01 21:04
 */
public class Test2Runnable {
    public static void main(String[] args) {
        //1.创建任务对象，具体需要执行的业务逻辑
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                GlobalLogger.LOGGER.debug("执行...");
            }
        };
        //2.创建Thread对象，构造方法传参Runnable实现类
        Thread t1 = new Thread(runnable,"t1");//第二个参数是线程名字
        //3.启动线程
        t1.start();
    }
}
