package com.wangyj.problems.dynamic_programming;

import org.junit.Test;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6
 */
public class MaximumSubarray_53 {


    public static void main(String[] args) {
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = new int[]{1, 2};
        System.out.println(maxSubArray(nums));
    }


    /**
     * 这种解法复杂了算法，下面的解法更好一些
     * 三种情况：
     * 1. 最长子串中包含第个i元素，且仅有第i个元素时和最大
     * 2. 最长子串中包含第个i元素，且第i个元素是子串的右边界
     * 3. 最长子串中不包含第一个元素，则转化为i-1个元素中寻找子串和最大的问题
     * <p>
     * 设定
     * bound[i]表示以第i个元素为结尾的i的子数组最大和
     * opt[i]表示i个元素中最大子数组和
     * 则状态方程：
     * bound[i]=max{bound[i-1]+nums[i],nums[i]}
     * opt[i]=max{opt[i-1],bound[i]}
     */
    public static int maxSubArray(int[] nums) {

        //bound[i]表示以第i个元素为结尾的i的子数组最大和
        int[] bound = new int[nums.length];

        //opt[i]表示i个元素中最大子数组和
        int[] opt = new int[nums.length];


        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        //元素个数大于2时初始化端点数据
        bound[0] = opt[0] = nums[0];
        bound[1] = Math.max(bound[0] + nums[1], nums[1]);
        opt[1] = Math.max(bound[1], opt[0]);
        for (int i = 2; i < nums.length; i++) {
            int a = Math.max(bound[i - 1] + nums[i], nums[i]);
            int b = opt[i - 1];
            bound[i] = a;
            opt[i] = a > b ? a : b;
        }

        return opt[nums.length - 1];
    }



    /**
     * 这道题没有要求返回子序列，是返回最大值
     * 用dp记录每个位置作为结尾时的最大和，对于任意一个元素来说，最大值要么是当前元素加上前边元素的最大值，要么就是当前元素自己是最大值
     *
     * 另外下面这个解题思路对dp的题找思路很有帮助
     * https://leetcode-cn.com/problems/maximum-subarray/solution/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
     * @author W.Y.J
     * @Date 2021/8/8 11:59 上午
     * @param nums
     * @return int
     */
    public static int maxSubArray2(int[] nums) {

        //dp[i]表示以第i个元素为结尾时子数组最大和
        int length = nums.length;
        int[] dp = new int[length];
        int max = nums[0];
        dp[0]=max;
        for (int i = 1; i < length; i++) {
            //要么是当前元素加上前边元素的最大值，要么就是当前元素自己是最大值
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    @Test
    public void test() {


//        int[] nums=new int[]{-2,1,-3,4,-1,2,1,-5,4 };
        int[] nums=new int[]{5,4,-1,7,8};
        System.out.println(maxSubArray(nums));



        System.out.println(maxSubArray2(nums));

    }


}
