package com.wangyj.problems.nowcoder;

import org.junit.Test;


//与1相比，第一家和最后一家房子是连着的了，整体的处理流程类似，但需要分类讨论了。
public class Rob2 {

    public int rob(int[] nums) {
        // write code here

        //dp[i]为到第i为止能偷到的最大金额
        int[] dp = new int[nums.length];
        if (nums.length==1){
            return nums[0];
        }


        //第一种场景：偷第一家，不偷最后一家
        dp[0] = nums[0];
        dp[1] = 0;//第二家只能不偷

        //遍历时不考虑最后一家
        for (int i = 2; i < nums.length-1; i++) {
            //对于第i家来说，有两种情况会有最大利益，偷i家+dp[i-2],或者不偷i家 dp[i-1]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }


        //第二种场景，不偷第一家，偷最后一家
        int[] dp2 = new int[nums.length];
        dp2[0]=0;//不偷所以为0
        dp2[1]=nums[1];
        for (int i = 2; i < nums.length; i++) {
            //对于第i家来说，有两种情况会有最大利益，偷i家+dp[i-2],或者不偷i家 dp[i-1]
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }


        return Math.max(dp[nums.length - 2],dp2[nums.length - 1]);
    }


    @Test
    public void test(){
//        int[] nums=new int[]{1,3,6};
//        int[] nums=new int[]{1,2,3,4};
        int[] nums=new int[]{43,4,4,1,26,29,24,44,52,12};
        System.out.println(rob(nums));
    }
}
