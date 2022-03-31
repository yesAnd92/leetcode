package com.wangyj.problems.heap;


import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * @author W.Y.J
 * @Date 2021/7/3 6:10 下午
 */
public class KthLargestElement_215 {


    /***********************************************借助PriorityQueue利用堆思想**************************************************/

    public int findKthLargestPriority(int[] nums, int k) {


        /**
         *  使用最小堆，这样队头元素是最小的，新进入的元素比队头的大，就进入队里，
         *  所有元素遍历后，队头的就是第k个大的元素
         */
        PriorityQueue<Integer> heap= new PriorityQueue<>(k);

        for (int num : nums) {

            if (heap.size()<k||heap.peek()<num){
                heap.offer(num);
                if (heap.size()>k)
                    heap.poll();
            }
        }
        return heap.poll();
    }





    /*****************************************************快排选择法**************************************************/


    private Random random = new Random();

    public int findKthLargest(int[] nums, int k) {

        int length = nums.length;
        return quickSort(nums, 0, length - 1, length - k);
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
        int pivotIndex = random.nextInt(end - start + 1) + start;
        //将支点放到数组头，方便后续使用
        swap(nums, start, pivotIndex);
        return partition(nums, start, end);
    }


    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start;
        int right = end;
        while (left != right) {
            while (left < right && pivot < nums[right])
                right--;
            while (left < right && pivot >= nums[left])
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
    public void test() {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
        System.out.println(findKthLargest(nums, 1));

        int[] nums2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(nums2, 4));
        System.out.println(findKthLargest(nums2, 9));
    }


    /*****************************************************使用大顶堆**************************************************/


    public int largestTopHeap(int[] nums, int k) {

        //堆化
        int length = nums.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(nums, length, i);
        }

        //堆化后，第一个元素就是堆顶元素，也是最大元素,每次将堆顶元素和最后一个交换，交换k次后
        //第k大的元素就位于倒数第k的位置

        for (int i = 0; i < k; i++) {
            swap(nums,0, length -i-1);
            heapify(nums,length-i-1,0);
        }
        return nums[length-k];
    }

    private void heapify(int[] nums, int length, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = left + 1;

        if (left < length && nums[largest] < nums[left])
            largest = left;

        if (right < length && nums[largest] < nums[right])
            largest = right;

        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, length, largest);
        }

    }


    @Test
    public void testHeap() {
        int[] nums2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(largestTopHeap(nums2, 4));
        System.out.println(largestTopHeap(nums2, 9));
        System.out.println(findKthLargestPriority(nums2, 4));
        System.out.println(findKthLargestPriority(nums2, 9));
    }

}
