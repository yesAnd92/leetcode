package com.wangyj.problems.double_pointer;

import org.junit.Test;

/**
 * @author W.Y.J
 * @Date 2021/5/21 21:51
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum_209 {


    /**
     * 暴力法，对每个元素机型遍历查找最小的子数组
     */
    public int minSubArrayLen(int target, int[] nums) {

        int length = nums.length;
        int ans = length + 1;
        for (int i = 0; i < length; i++) {
            int right = i + 1;
            int sum = nums[i];
            //如果单个元素直接>=target,直接返回1
            if (sum >= target) return 1;
            while (right < length) {
                sum += nums[right];
                if (sum >= target) {
                    ans = Math.min(ans, right - i + 1);
                    break;
                }
                right++;
            }
        }
        if (ans == length + 1) return 0;
        return ans;
    }


    /**
     * 使用滑动窗口的思路解决
     * 维护一个弹性大小的窗口，当窗口内元素和小于目标值时，窗口右边界向右移动直至和大于目标值
     * 但是这个窗口内并不一定是最小的字串，比如目标值是100，之前窗口内是50个1，最后一次右移加入的元素是
     * 99，那么之前窗口加入的都是无意义的，需要缩小窗口的左边界，直至刚好大于100为止。
     */
    public int minSubArrayLen2(int target, int[] nums) {

        int length = nums.length;
        int ans = length;
        //窗口的左右边界
        int left = 0, right = 0;
        int sum = 0;
        boolean hasResult=false;
        while (right < length) {
            //右移，直到sum>=target
            sum += nums[right++];
            if (sum >= target) {
                hasResult=true;
                ans = Math.min(ans, right - left);
                while (left <= right) {
                    //从左边缩减窗口大小，直到找到最小的值
                    if (sum - nums[left] >= target) {
                        sum -= nums[left++];
                        ans = Math.min(ans, right - left);
                    } else {
                        break;
                    }
                }
            }
        }
        if (!hasResult) return 0;
        return ans;
    }

    @Test
    public void test() {
        /**
         * test case
         * 输入：target = 7, nums = [2,3,1,2,4,3]  输出：2
         * 输入：target = 4, nums = [1,4,4]  输出：1
         * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]  输出：0
         * 输入：target = 15, nums = [1,2,3,4,5]  输出：5
         *
         */
//        int nums[] = new int[]{2, 3, 1, 2, 4, 3};
//        System.out.println(minSubArrayLen2(7, nums));
//        int nums[] = new int[]{1,4,4};
//        System.out.println(minSubArrayLen2(4, nums));
        int nums[] = new int[]{12};
        System.out.println(minSubArrayLen2(11, nums));
    }
}
