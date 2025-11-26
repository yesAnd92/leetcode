package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class MinNumberInRotateArray {

    public int minNumberInRotateArray(int[] nums) {
        // write code here

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                //中间值大于右边值，说明旋转发生在右半边,最小值在mid右边
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                //最小值要么是mid，要么在他左边
                right = mid;
            } else {
                //相等说明不了什么，左边可能还有相等的，右端点左移，进一步缩小范围
                right--;
            }

        }
        return nums[left];
    }

    @Test
    public void test(){
        int[] nums=new int[]{3,4,5,0,1,2};
        System.out.println(minNumberInRotateArray(nums));
    }
}
