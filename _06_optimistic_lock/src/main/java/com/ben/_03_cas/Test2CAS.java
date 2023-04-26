package com.ben._03_cas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类型cas操作
 */
public class Test2CAS {
    public static void main(String[] args) {
        AccountImpl account = new AccountImpl(new BigDecimal("10000"));
        AccountReference.demo(account);
    }
}

class AccountImpl implements AccountReference {

    private AtomicReference<BigDecimal> balance;

    public AccountImpl(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true) {
            BigDecimal prev = this.balance.get();
            BigDecimal next = prev.subtract(amount);
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

interface AccountReference {

    BigDecimal getBalance();

    void withdraw(BigDecimal amount);

    static void demo(AccountReference reference) {
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                reference.withdraw(new BigDecimal("10.00"));
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

        System.out.println("balance = " + reference.getBalance());
    }

}
