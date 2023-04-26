package com.ben._02_custom_pool;

import com.log.GlobalLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test1CusPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MICROSECONDS, 10);
        for (int i = 0; i < 15; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(10000000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                GlobalLogger.LOGGER.debug("{}j = " + j);
            });
        }

    }
}

/**
 * 线程池，存放线程对象
 */
class ThreadPool {
    //持有任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //获取任务的超时时间超时时间
    private long timeout;
    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueCapacity);
    }

    //提交任务
    public void execute(Runnable task) {
        //任务数量判断，没有超过coreSize，直接创建并添加
        //如果任务数超过coreSize，把任务提交到任务队列
        synchronized (this) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                GlobalLogger.LOGGER.debug("新增worker{} " + worker + ", task = " + task);
                workers.add(worker);
                worker.start();

            } else {

                //加入任务队列
                taskQueue.put(task);
                //死等
                //带超时等待
                //放弃任务执行
                //抛出异常
                //谁提交，谁执行（让调用者自己执行任务）
            }
        }
    }


    //
    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //1.初始化时，直接执行

            //2.执行完毕后去任务队列取出任务执行
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    GlobalLogger.LOGGER.debug("正在执行...{}" + task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            //3.移除线程
            synchronized (workers) {
                GlobalLogger.LOGGER.debug("worker被移除{} " + this);
                workers.remove(this);
            }
        }
    }
}

/**
 * 自定义阻塞队列
 *
 * @param <T> 队列元素
 */
class BlockingQueue<T> {
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<>();//双向链表实现 LinkedList、ArrayDeque

    //2.获取任务、放置任务<锁>
    private final ReentrantLock lock = new ReentrantLock();//比synchronized灵活，有多个条件变量

    //3.消费者、生产者条件变量
    private Condition fullWaitSet = lock.newCondition();
    private Condition emptyWaitSet = lock.newCondition();

    //4.容量(防止OOM)
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //5.获取任务
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();//获取任务并移除
            fullWaitSet.signalAll();//通知放置任务的线程
            return t;
        } finally {
            lock.unlock();
        }
    }

    //带超时的阻塞获取任务
    public T poll(long time, TimeUnit unit) {
        long nanos = unit.toNanos(time);//统一换算为纳秒
        try {
            lock.lock();
            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    if (emptyWaitSet.awaitNanos(nanos) <= 0) {
                        return null;//时间等够，返回null
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //6.添加任务
    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    GlobalLogger.LOGGER.debug("等待加入任务队列{} ..." + task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GlobalLogger.LOGGER.debug("加入任务队列{} " + task);
            queue.addLast(task);//将任务添加到队列尾部
            emptyWaitSet.signalAll();//通知取任务线程
        } finally {
            lock.unlock();
        }
    }

    //带超时时间的阻塞添加
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();

        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    GlobalLogger.LOGGER.debug("等待加入任务队列{} ..." + task);
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GlobalLogger.LOGGER.debug("加入任务队列{} " + task);
            queue.addLast(task);//将任务添加到队列尾部
            emptyWaitSet.signalAll();//通知取任务线程
            return true;
        } finally {
            lock.unlock();
        }

    }

    //7.获取队列大小，查看任务数量
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

}
