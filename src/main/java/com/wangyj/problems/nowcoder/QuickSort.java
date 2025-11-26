package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class QuickSort {


    public void quick(int[] nums, int low, int high) {
        //递归退出条件
        if (low < high) {
            int partition = partition(nums, low, high);
            //这里递归的是 partition- 1 和 partition + 1
            //因为partition位置的元素已经在其正确位置，不需要再参与排序。
            quick(nums, low, partition -1);
            quick(nums, partition +1, high);
        }

    }

    public int partition(int[] nums, int low, int high) {

        //支点的值
        int pivot = nums[low];
        int left = low, right = high;
        while (left < right) {
            //1.基准选左边，先移动右指针，反过来也成立
            // 因为谁先走，左右相遇时就满足谁的条件,选左边作为基准
            // 就要保证最后交换到左边的是一个小于基准的值，因此要有指针先走

            //2.使用带等号的比较可以避免在遇到重复元素时陷入死循环
            // 同时让重复元素均匀地分布在两边。
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            //左边的数都应该比pivot小，一直找到比它大的等待交换位置
            while (left < right && nums[left] <= pivot) {
                left++;
            }

            if (left < right) {
                swap(nums, left, right);
            }

        }
        //最后，交换支点的位置,此时left=right,使用哪个都一样
        swap(nums, low, right);
        return left;
    }

    public void swap(int nums[], int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    @Test
    public void test() {
        int[] nums = new int[]{7, 10, 9, 9, 8, 7, 5, 6, 4, 3, 4, 2};
        quick(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num+" ");
        }
    }

}
