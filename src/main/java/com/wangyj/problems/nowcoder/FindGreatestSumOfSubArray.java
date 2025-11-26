package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class FindGreatestSumOfSubArray {

    public int FindGreatestSumOfSubArray(int[] array) {
        // write code here

        //dp[i]表示从下标为i为结尾的，连续序列的最大值
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int max = dp[0];
        for (int i = 1; i < array.length; i++) {
            //比较前边的大还是加上当前的大
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    @Test
    public void test() {

        int[] arr = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(FindGreatestSumOfSubArray(arr));

    }
}
