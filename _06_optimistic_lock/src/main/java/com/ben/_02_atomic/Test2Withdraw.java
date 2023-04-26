package com.ben._02_atomic;

import com.log.GlobalLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test2Withdraw {

    public static void main(String[] args) {
        AccountAtomic accountAtomic = new AccountAtomic(10000);
        Account.demo(accountAtomic);
    }

}

class AccountAtomic implements Account {

    private AtomicInteger balance;

    public AccountAtomic(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        this.balance.addAndGet(-amount);
    }
}

interface Account {

    Integer getBalance();

    void withdraw(Integer amount);


    static void demo(Account account) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }

        list.forEach(Thread::start);

        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        GlobalLogger.LOGGER.debug("balance = " + account.getBalance());
    }

}
