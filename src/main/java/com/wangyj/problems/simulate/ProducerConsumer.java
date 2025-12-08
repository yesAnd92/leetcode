package com.wangyj.problems.simulate;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {


    private static Queue<Integer> queue = new ArrayDeque();
    private static final Integer maxSize = 5;


    public synchronized void producer() {
        while (queue.size() == maxSize) {
            try {
                System.out.println(Thread.currentThread().getName() + "等待...");
                wait();
            } catch (InterruptedException e) {
            }
        }
        Random random = new Random();
        int value = random.nextInt();
        queue.offer(value);
        System.out.println(Thread.currentThread().getName()+"-"+value);
        notifyAll();

        //模拟工作
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized void consumer() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + "等待...");
                wait();
            } catch (InterruptedException e) {
            }
        }
        Integer value = queue.poll();
        System.out.println(Thread.currentThread().getName() +"-" +value);
        notifyAll();
    }


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread p1 = new Thread(() -> {
            for (int i=0;i<10;i++) {
                producerConsumer.producer();
            }
        }, "p1");

        Thread p2= new Thread(() -> {
            for (int i=0;i<20;i++) {
                producerConsumer.producer();
            }
        }, "p2");


        Thread c1 = new Thread(() -> {
             while (true){
                producerConsumer.consumer();
            }
        }, "c1");
        Thread c2 = new Thread(() -> {
            producerConsumer.consumer();
        }, "c2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        Thread.sleep(100000);
    }
}
