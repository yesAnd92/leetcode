package com.wangyj.problems.nonleetcode;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 两个线程交替输出
 * 线程1输出 1、2、3、4、5、
 * 线程2输出 a、b、c、d、e、
 * 最终结果a1b2c3d4...
 */
public class AlternateOutput {

    char[] zimu = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'j', 'k'};
    char[] num = new char[]{'1', '2', '3', '4'};


    /**
     * 使用自旋锁
     *
     * @author W.Y.J
     * @Date 2022/3/9 16:11
     */
    @Test
    public void test() {
        AtomicInteger change = new AtomicInteger(0);
        int min = Math.min(zimu.length, num.length);
        new Thread(() -> {
            for (int i = 0; i < zimu.length; i++) {
                while (true) {
                    if (change.get() == 0) {
                        System.out.print(zimu[i]);
                        if (i < min) {
                            change.set(1);//这里不需要使用compareAndSwap,不存在并发写
                        }
                        break;
                    }
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < num.length; i++) {
                while (true) {
                    if (change.get() == 1) {
                        System.out.print(num[i]);
                        if (i < min) {
                            change.set(0);
                        }
                        break;
                    }
                }
            }
        }).start();
    }


    /**
     * 使用ReentrantLock配合2个Condition使用
     *
     * @param
     * @return void
     * @author W.Y.J
     * @Date 2022/3/9 16:11
     */
    @Test
    public void test1() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition zimuCondition = lock.newCondition();
        Condition numCondition = lock.newCondition();

        Thread numThread = new Thread(() -> {
            for (int i = 0; i < num.length; i++) {
                try {
                    lock.lock();
                    numCondition.await();
                    System.out.print(num[i]);
                    zimuCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread zimuThread = new Thread(() -> {
            for (int i = 0; i < zimu.length; i++) {
                try {
                    lock.lock();
                    System.out.print(zimu[i]);
                    numCondition.signal();
                    zimuCondition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        //注意两个线程的启动顺序
        numThread.start();
        zimuThread.start();
    }


    /**
     * 使用synchronized
     *
     * @param
     * @return void
     * @author W.Y.J
     * @Date 2022/3/9 16:16
     */
    @Test
    public void test3() {
        Object lock = new Object();
        Thread numThread = new Thread(() -> {
            for (int i = 0; i < num.length; i++) {
                try {
                    synchronized (lock) {
                        lock.wait();
                        System.out.print(num[i]);
                        lock.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread zimuThread = new Thread(() -> {

            for (int i = 0; i < zimu.length; i++) {
                try {
                    synchronized (lock) {
                        System.out.print(zimu[i]);
                        lock.notify();
                        lock.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        numThread.start();
        zimuThread.start();
    }

    static Thread numThread, zimuThread;


    /**
     * 使用LockSupport,这种语法比较简单
     *
     * @param
     * @return void
     * @author W.Y.J
     * @Date 2022/3/9 16:17
     */
    @Test
    public void test4() {

        numThread = new Thread(() -> {

            for (int i = 0; i < num.length; i++) {
                try {
                    LockSupport.park(numThread);
                    System.out.print(num[i]);
                    LockSupport.unpark(zimuThread);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        zimuThread = new Thread(() -> {
            for (int i = 0; i < zimu.length; i++) {
                try {
                    System.out.print(zimu[i]);
                    LockSupport.unpark(numThread);
                    LockSupport.park(zimuThread);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        numThread.start();
        zimuThread.start();
    }
}
