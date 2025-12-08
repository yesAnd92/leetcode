package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class Rob {

    public int rob(int[] nums) {
        // write code here

        //dp[i]为到第i为止能偷到的最大金额
        int[] dp = new int[nums.length];
        if (nums.length==1){
            return nums[0];
        }

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);


        for (int i = 2; i < nums.length; i++) {
            //对于第i家来说，有两种情况会有最大利益，偷i家+dp[i-2],或者不偷i家 dp[i-1]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }


    @Test
    public void test(){
        int[] nums=new int[]{1,3,6};
//        int[] nums=new int[]{1,2,3,4};
        System.out.println(rob(nums));
    }
}
