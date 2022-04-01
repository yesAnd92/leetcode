package com.wangyj.problems.array;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class MergeAortedArray_88 {


    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //特判
        int length = nums1.length;
        if (n==0) return;

        Queue<Integer> queue = new ArrayBlockingQueue<>(m+1);

        int n1 = 0, n2 = 0;

        for (int i = 0; i<length ; i++) {
            //每次循环都将nums1中的元素丢到queue中，比较时从queue中取
            if(n1<m){
                queue.offer(nums1[n1++]);
            }

            //nums2中数字已遍历结束后者比nums1中的小
            if (n2>=n||(!queue.isEmpty()&&queue.peek()<nums2[n2])){
                //说明本次比较nums1中的数字比nums2中的小
                nums1[i]=queue.poll();
            }else {
                nums1[i]=nums2[n2++];
            }
        }
    }



    @Test
    public void test(){

//        int[] nums1=new int[]{1,2,3,0,0,0};
//        int[] nums2=new int[]{2,5,6};
//        merge(nums1,3,nums2,3);

//        [0]
//        0
//        [1]
//        1
        int[] nums1=new int[]{0};
        int[] nums2=new int[]{2};
        merge(nums1,0,nums2,1);
        for (int i : nums1) {
            System.out.print(i+" ");
        }
    }
}
