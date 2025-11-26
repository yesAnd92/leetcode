package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class BinarySearch {


    public int search(int[] nums, int target) {
        int len = nums.length;

        if (len == 0)
            return -1;

        return binary(nums, 0, len - 1, target);

    }

    public int binary(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if (nums[mid] > target) {
            //中间值比目标值大，目标在左边
            return binary(nums, left, mid - 1, target);
        } else if (nums[mid] < target) {
            return binary(nums, mid + 1, right, target);
        } else {
            return mid;
        }

    }


    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                //目标值在左侧
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


    @Test
    public void test() {
        int[] nums = new int[]{-1, 0, 3, 4, 6, 10, 13, 14};
        System.out.println(search(nums, 13));
        System.out.println(search2(nums, 13));
    }
}
