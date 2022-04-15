package com.wangyj.problems.dynamic_programming;


import org.junit.Test;

import java.util.Arrays;

/**
 * 最长递增子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * @author W.Y.J
 * @Date 2021/8/12 9:19
 */
public class LongestIncreasingSubseq_300 {

    /**
     * 可以参考这篇文章 https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        int ans = 1;
        int length = nums.length;

        //dp[i]表示以第i个元素为结尾的最长递增子序列长度,结尾一定是要包含第i个元素的
        int[] dp = new int[length];
        //对dp进行初始化，每个字符作为结尾长度至少都是1
        Arrays.fill(dp, 1);

        for (int i = 0; i < length; i++) {
            /**
             * 如果第i个元素是最长递增子序列的最后一个值，那么i元素前边所有比第i个元素小的元素
             * 都有可能是第i个元素为结尾的最长递增子序列中的倒数第二个元素，因此需要遍历寻找比当前元素小的并且序列最长的那个值
             * 思维误区：以为从后往前遍历，遇到的第一个比i元素小的值就是寻找的那个结果
             */
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            if (ans < dp[i]) {
                ans = dp[i];
            }
        }
        return ans;
    }


    @Test
    public void test() {
        int[] nums = new int[]{0,1,0,3,2,3};
        System.out.println(lengthOfLIS(nums));
    }
}