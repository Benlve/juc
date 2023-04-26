package com.ben._03_singleton;

import com.log.GlobalLogger;

/**
 * 类加载是懒惰的，总是在使用到该类时，才进行加载
 */
public class InnerHolderSingleton {

    private InnerHolderSingleton() {
        GlobalLogger.LOGGER.debug("InnerHolderSingleton()");
    }

    public static InnerHolderSingleton getInstance() {
        return Holder.INSTANCE;//调用到Holder类时，出发类的加载
    }

    private static class Holder {
        static final InnerHolderSingleton INSTANCE = new InnerHolderSingleton();//静态内部类，首次类加载的时候创建
    }
}
