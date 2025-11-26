package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class MultithreadedAlternatingOutput {


    @Test
    public void test() throws InterruptedException {
        String nums = "123456";
        String zimus = "abcdef";


        //false nums输出  ；ture zimu输出
        AtomicBoolean flag = new AtomicBoolean(false);

        CountDownLatch count = new CountDownLatch(2);

        new Thread(() -> {
            for (char c : nums.toCharArray()) {
                while (true) {

                    if (!flag.get()) {
                        System.out.println(c);
                        flag.set(true);
                        break;
                    }
                }
            }


            count.countDown();
        }).start();


        new Thread(() -> {
            for (char c : zimus.toCharArray()) {
                while (true) {
                    if (flag.get()) {
                        System.out.println(c);
                        flag.set(false);
                        break;
                    }
                }
            }
            count.countDown();
        }).start();

        count.await();
    }


    private static Thread oddThead;
    private static Thread oushuThead;
    public static void main(String[] args) throws InterruptedException {


        CountDownLatch cdl = new CountDownLatch(2);

        oddThead = new Thread(() -> {
            for (int i = 1; i < 100; i = i + 2) {
                System.out.println("奇数线程：" + i);
                LockSupport.unpark(oushuThead);
                LockSupport.park();
            }
            cdl.countDown();
        });

        oushuThead = new Thread(() -> {
            for (int i = 2; i < 100; i = i + 2) {
                LockSupport.park();
                System.out.println("偶数线程：" + i);
                LockSupport.unpark(oddThead);
            }
            cdl.countDown();
        });

        oushuThead.start();
        oddThead.start();


        cdl.await();
    }


}
