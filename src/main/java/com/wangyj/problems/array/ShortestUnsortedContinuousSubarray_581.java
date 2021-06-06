package com.wangyj.problems.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author W.Y.J
 * @Date 2021/6/5 17:06
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray_581 {


    /**
     * 取巧方法 ，用原数组和排序好的数组比较获取调整的区间
     * 时间  排序
     * 空间  一个数组存放原数组
     */
    public int findUnsortedSubarray(int[] nums) {

        //排序后两头分别比较，第一个不同的下标，即为所求
        int origin[] = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);

        int min = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length) {
                return 0;
            }
            if (nums[i] != origin[i]) {
                min = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == 0) {
                return 0;
            }
            if (nums[i] != origin[i]) {
                max = i;
                break;
            }
        }

        //特判
        if (min == max) return 0;
        return max - min + 1;
    }


    /**
     * 更好的方法，不需要排序，也无需额外空间
     * 算法：
     * 数组其实可以抽象成为【有序部分1】+【无序部分】+【有序部分2】，我们要找到无序部分的上下界
     * 无序部分右端点记为right，从左至右遍历数组，记max是以遍历过部分的最大值
     * 显然有序部分2中的所有元素比都应该>=max,所以一旦出现nums[i]<=max,说明i也在无序部分中，即令右界限更新为i
     * 左边界使用同样的方法得到
     */
    public int findUnsortedSubarray2(int[] nums) {

        int length = nums.length;
        //特判
        if (length == 1) return 0;

        int right = 0, max = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] < max) {
                //继续变更右边界
                right = i;
            } else {
                //更换最大值
                max = nums[i];
            }
        }

        int left = length - 1, min = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            } else {
                min = nums[i];
            }
        }

        return right - left > 0 ? right - left + 1 : 0;
    }

    @Test
    public void test() {
//        int nums[]=new int[]{2,6,4,8,10,9,15};
//        int nums[]=new int[]{2,4,3,5};
//        int nums[]=new int[]{2,3,4,5};
//        int nums[]=new int[]{2,2,4,5};
//        int nums[]=new int[]{2};
        int nums[] = new int[]{2, 2};
//        int nums[]=new int[]{2,2,4,5,7,7,6};

        System.out.println(findUnsortedSubarray2(nums));
    }
}
