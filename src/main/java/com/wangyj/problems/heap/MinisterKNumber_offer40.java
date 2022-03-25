package com.wangyj.problems.heap;


import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小的k个数
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * @author W.Y.J
 * @Date 2022/3/25 10:36
 */
public class MinisterKNumber_offer40 {


    /**
     * 借助PriorityQueue使用堆的特性实现
     */
    public int[] getLeastNumbers(int[] arr, int k) {

        if (k==0)return new int[]{};

        //创建一个大顶堆，大的元素在上边
        Queue<Integer> heap= new PriorityQueue<>(k, (n1, n2) -> n2 - n1);

        for (int i = 0; i <arr.length ; i++) {

            if(heap.isEmpty()||heap.size()<k||arr[i]<heap.peek()){
                /**
                 * 这里有个优化，不是所有新遍历的元素都往heap里方，如果heap总已经有k个元素了，
                 * 这时遍历到的当前元素如果比堆顶元素还要大，那么它必然不属于答案的集合
                 */
                heap.offer(arr[i]);
            }
            //如果heap元素超过k个，说明堆顶元素可以被淘汰了
            if (heap.size()>k){
                heap.poll();
            }
        }
        return heap.stream().mapToInt(o-> o.intValue()).toArray();
    }


    @Test
    public void test(){

        int[] leastNumbers = getLeastNumbers(new int[]{1, 2,8,2, 3, 4, 5, 6, 7}, 0);
        Arrays.stream(leastNumbers).forEach(System.out::print);
    }
}
