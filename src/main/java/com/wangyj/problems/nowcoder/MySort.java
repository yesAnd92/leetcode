package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class MySort {

    public void bubble(int[] nums) {

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int t = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = t;
                }
            }
        }
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }


    public void select(int[] nums) {
        //写一个倒序的选择排序
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int maxIndex = i;
            for (int j = i; j < length; j++) {
                if (nums[i] < nums[j]) {
                    maxIndex = j;
                }
            }

            int t = nums[i];
            nums[i] = nums[maxIndex];
            nums[maxIndex] = t;
        }

        for (int i : nums) {
            System.out.print(i + " ");
        }
    }


    public void  quick(int[] nums){

    }

    @Test
    public void test() {
        int[] nums = new int[]{10, 10, 9, 9, 8, 7, 5, 6, 4, 3, 4, 2};
        bubble(nums);
        System.out.println();
        select(nums);
    }
}
