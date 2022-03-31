package com.wangyj.problems.heap;


import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小的k个数
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 *
 * @author W.Y.J
 * @Date 2022/3/25 10:36
 */
public class MinisterKNumber_offer40 {


    /**
     * 借助PriorityQueue使用堆的特性实现
     */
    public int[] getLeastNumbers(int[] arr, int k) {

        if (k == 0) return new int[]{};

        //创建一个大顶堆，大的元素在上边
        Queue<Integer> heap = new PriorityQueue<>(k, (n1, n2) -> n2 - n1);

        for (int i = 0; i < arr.length; i++) {

            if (heap.isEmpty() || heap.size() < k || arr[i] < heap.peek()) {
                /**
                 * 这里有个优化，不是所有新遍历的元素都往heap里方，如果heap总已经有k个元素了，
                 * 这时遍历到的当前元素如果比堆顶元素还要大，那么它必然不属于答案的集合
                 */
                heap.offer(arr[i]);
            }
            //如果heap元素超过k个，说明堆顶元素可以被淘汰了
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.stream().mapToInt(o -> o.intValue()).toArray();
    }


    public int[] getLeastNumbers2(int[] arr, int k) {

        if (k == 0) return new int[]{};
        quicksort(arr,k,0,arr.length-1);

        int[] ans=new int[k];
        for (int i = 0; i < k; i++) {
            ans[i]=arr[i];
        }
        return ans;
    }



    //双指针快排
    private void quicksort(int[] arr, int k, int left, int right) {

        if (left>right)
            return;
        int pivotIndex = partition(arr, left, right);
        if (pivotIndex==k){
            return;
        }else if (k<pivotIndex){
            //最小的k个数一定在[left,pivotIndex-1]中
            quicksort(arr,k,left,pivotIndex-1);
        }else{
            /**
             * [left,pivotIndex]中左右的数一定都是k个最小数中，这部分不用再进行筛选，
             * [pivotIndex+1，right]中有一部分在k个最小数中，还需要 继续进行筛选
             */
            quicksort(arr,k,pivotIndex+1,right);
        }
    }



    private int partition(int[] arr, int start, int end) {
        int left=start;
        int right=end;
        int pivot = arr[end];
        while (left < right) {
            while (arr[left]<pivot&&left<right){
                left++;
            }
            while (arr[right]>=pivot&&left<right){
                right--;
            }
            if (left<right){

            swap(arr,left,right);
            }
        }
        swap(arr,right,end);
        return right;
    }


    private void swap(int[] arr,int left,int right){
        int tmp=arr[left];
        arr[left]=arr[right];
        arr[right]=tmp;
    }

    @Test
    public void test() {
        int[] leastNumbers = getLeastNumbers(new int[]{4, 2, 8, 2, 3, 4, 5, 6, 7}, 3);
        Arrays.stream(leastNumbers).forEach(System.out::print);

        int[] leastNumbers2 = getLeastNumbers2(new int[]{4, 2, 8, 2, 3, 4, 5, 6, 7}, 3);
        Arrays.stream(leastNumbers2).forEach(System.out::print);

    }
}
