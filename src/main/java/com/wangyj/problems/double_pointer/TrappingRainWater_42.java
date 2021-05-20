package com.wangyj.problems.double_pointer;

import org.junit.Test;

/**
 * @Author W.Y.J
 * @Date 2021/5/20 22:52
 * <p>
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class TrappingRainWater_42 {

    public int trap(int[] height) {

        /**
         * 最重要的是找到一个通用的规律，一个数字对应的位置能否接到水是由这个位置的左右两边，
         * 是否存在比他大的值，所以问题就转化为找每个数字左右两边超过这个数的值，
         * 即可计算当前位置的存雨量。我们用两个指针分别这两个位置试一试
         *
         * 好吧，做完题发现这其实就是暴力破解，每个位置都要左右两边遍历，时间复杂度 O(n^2)
         */
        int length = height.length;
        int ans = 0;
        int curr = 0;
        while (curr < length) {
            int leftMax = 0;
            int rightMax = 0;
            int left = curr;
            int right = curr;
            while (true) {
                //右移找到最大值
                if (--left < 0) break;
                leftMax = Math.max(height[left], leftMax);
            }
            //如果该数左边不存在比他大的数字，这个位置肯定就无法接到水，无需判断右边
            if (leftMax <= height[curr]) {
                curr++;
                continue;
            }
            while (right < length) {
                if (++right == length) break;
                rightMax = Math.max(height[right], rightMax);
            }
            if (rightMax <= height[curr]) {
                curr++;
                continue;
            }
            //取左右两边最小的数，减去自身的数就是能够接到的雨量数
            ans += (Math.min(leftMax, rightMax) - height[curr]);
            curr++;
        }
        return ans;
    }


    public int trap2(int[] height) {

        /**
         * 在解法一的基础上，很容易想到去优化，因为在寻找左右两边最大值时，有大量的重复操作
         * 可以使用动态规划的思想，将某个区间范围内的最大值记录下来，用空间换时间
         */
        int ans = 0;
        return ans;
    }


    @Test
    public void test() {
        /**
         * test case
         * {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1} --> 6
         * {4,2,0,3,2,5} -->9
         */
        int[] height = new int[]{10, 2, 3};
        System.out.println(trap(height));
    }
}
