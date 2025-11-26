package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        // write code here
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {

            int mid =left+(right-left)/2;
            //上坡的时候一定能找到波峰，因为题目给出的是nums[-1] = nums[n] = -∞
            if (nums[mid]<nums[mid+1]){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }

    @Test
    public void test(){
        int[] nums=new int[]{2,4,1,2,7,8,4};
        System.out.println(findPeakElement(nums));
    }
}
