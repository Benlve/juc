package com.ben._01_withdraw;

import com.log.GlobalLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test1Withdraw {
    public static void main(String[] args) {
//        Account account = new AccountUnsafe(10000);
//        Account.demo(account);

        Account account1 = new AccountSafe(10000);
        Account.demo(account1);

    }
}

class AccountSafe implements Account {

    private AtomicInteger balance;

    public AccountSafe(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            //获取余额最新值
            int prev = balance.get();
            //修改后的余额
            int next = prev - amount;
            //同步到主存
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

class AccountUnsafe implements Account {
    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized Integer getBalance() {
        return this.balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        this.balance -= amount;
    }
}

interface Account {
    Integer getBalance();

    void withdraw(Integer amount);

    static void demo(Account account) {
        List<Thread> list = new ArrayList<>();//1.将线程对象添加到list中
        for (int i = 0; i < 1; i++) {
            list.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        long start = System.nanoTime();

        list.forEach(Thread::start);//2.启动1000个线程

        list.forEach(t -> {
            try {
                t.join();//3.主线程等待1000个线程执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();

        GlobalLogger.LOGGER.debug("time = " + (end - start) / 1000000 + ", balance = " + account.getBalance());

    }
}