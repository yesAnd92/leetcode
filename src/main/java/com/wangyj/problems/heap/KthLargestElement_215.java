package com.wangyj.problems.heap;


import org.junit.Test;

import java.util.Random;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * @author W.Y.J
 * @Date 2021/7/3 6:10 下午
 */
public class KthLargestElement_215 {

    /*****************************************************快排选择法**************************************************/


    private Random random = new Random();

    public int findKthLargest(int[] nums, int k) {

        int length = nums.length;
        return quickSort(nums, 0, length - 1, length-k);
    }


    private int quickSort(int nums[], int start, int end, int target) {

            int pivotIndex = randomPartition(nums, start, end);
            if (pivotIndex == target) {
                return nums[target];
            } else {
                //判断支点和目标下标的大小，确定对哪一半的区间进行排序，另一半区间没有继续排序的必要
                return pivotIndex < target ? quickSort(nums, pivotIndex + 1, end, target) : quickSort(nums, start, pivotIndex - 1, target);
            }
    }


    private int randomPartition(int nums[], int start, int end) {
        //随机一个位置作为比较的支点
        int pivotIndex = random.nextInt(end - start+1) + start;
        //将支点放到数组头，方便后续使用
        swap(nums, start, pivotIndex);
        return partition(nums, start, end);
    }



    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start ;
        int right = end;
        while (left != right) {
            while (left <right && pivot < nums[right])
                right--;
            while (left <right && pivot >= nums[left])
                left++;
            if (left < right)
                swap(nums, left, right);
        }
        swap(nums, start, right);
        return right;
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


    @Test
    public void test(){
        int[] nums= new int[]{3,2,1,5,6,4};
        System.out.println(findKthLargest(nums,2));
        System.out.println(findKthLargest(nums,1));

        int[] nums2= new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums2,4));
        System.out.println(findKthLargest(nums2,9));
    }


    /*****************************************************以下为私有方法**************************************************/
}
