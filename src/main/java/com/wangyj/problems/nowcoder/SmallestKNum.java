package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SmallestKNum {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        // write code here

        ArrayList<Integer> result = new ArrayList<>(k);

        if (k == 0 || input.length == 0)
            return result;

        //利用优先级队列的堆功能,大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, ((o1, o2) -> o2 - o1));

        queue.add(Integer.MAX_VALUE);

        for (int i : input) {
            if (i < queue.peek()) {
                //比队首大，入队
                queue.offer(i);
                //维护队列的大小为k
                while (queue.size() > k) {
                    queue.poll();
                }
            }

        }


        for (int i = 0; i < k; i++) {
            result.add(0);
        }

        //queue中即为最小的k个数，逆序出来
        for (int i = k - 1; i >= 0; i--) {
            result.set(i, queue.poll());

        }
        return result;
    }


    public int[] smallestK(int[] arr, int k) {



        if (k == 0 || arr.length == 0)
            return new int[]{};

        //利用优先级队列的堆功能,大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, ((o1, o2) -> o2 - o1));

        //前K个数据先入队列,规避peek空队列的问题
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()) {
                //比队首大，入队
                queue.offer(arr[i]);
                //维护队列的大小为k
                while (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        int[] re = new int[k];
        for (int i = 0; i < k; i++) {
            re[i] = queue.poll();
        }

        return re;
    }


    @Test
    public void test() {
        int[] input = new int[]{4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> integers = GetLeastNumbers_Solution(input, 1);
        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }


    }

    @Test
    public void test2() {
        int[] input = new int[]{4, 5, 1, 6, 2, 7, 3, 8};
        int[] ints = smallestK(input, 5);
        for (Integer integer : ints) {
            System.out.print(integer + " ");
        }


    }
}
