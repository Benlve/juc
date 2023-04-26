package com.ben._03_singleton;

import com.log.GlobalLogger;

/**
 * 饿汉式
 */
public enum EnumSingleton {
    INSTANCE;

    private int info;
    private String msg;

    EnumSingleton(int info, String msg) {
        this.info = info;
        this.msg = msg;
        GlobalLogger.LOGGER.debug("Single01(args~)");
    }

    EnumSingleton() {
        GlobalLogger.LOGGER.debug("Single01()");
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Single01{" +
                "info=" + info +
                ", msg='" + msg + '\'' +
                '}';
    }
}
