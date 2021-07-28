package com.wangyj.problems.divide_conquer;

import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @author W.Y.J
 * @Date 2021/7/27 11:23
 */
public class FindFirstAndLastPositionOfElement_34 {


    /**
     * 这个问题刚开始一直纠结一次二分搜索下来，同时获得左右索引，但是代码十分不好控制
     * 左右分别处理，就容易多了
     * @author W.Y.J
     * @Date 2021/7/28 15:27
     */
    public int[] searchRange(int[] nums, int target) {
        //特判
        if (nums.length == 0)
            return new int[]{-1, -1};
        //先搜索左索引
        int leftIndex = findLeftIndex(nums, 0, nums.length - 1, target);
        if (leftIndex == -1)
            return new int[]{-1, -1};//如果搜索一次，不存在目标值，直接返回结果
        //这里有个优化点，直接把左索引传入，没必要再次从0开始，减少搜索范围
        int rightIndex = findRightIndex(nums, leftIndex, nums.length - 1, target);
        return new int[]{leftIndex, rightIndex};
    }


    private int findLeftIndex(int[] nums, int left, int right, int target) {
        int l = left, r = right;
        int lMin = r;
        while (l <= r) {
            int mid = l + (r - l >> 1);
            if (nums[mid] == target) {
                lMin = lMin > mid ? mid : lMin; //如果有更小的则替换
                //注意这里不能直接返回，有可能不是最左边元素,继续搜索左边
                r = mid - 1;
            } else if (nums[mid] < target) {
                //说明目标如果存在的话一定在右半区，继续搜索右边
                l = mid + 1;
            } else {
                //nums[mid]>target,说名如果结果存在的话一定在左半区，继续搜索左边
                r = mid - 1;
            }
        }
        //如果 lMin还是等于r，说明，1不存在target元素，2r刚好是target元素
        return lMin != right ? lMin : nums[lMin] == target ? lMin : -1;
    }


    private int findRightIndex(int[] nums, int left, int right, int target) {
        int l = left, r = right;
        int rMax = l;
        while (l <= r) {
            int mid = l + (r - l >> 1);
            if (nums[mid] == target) {
                rMax = rMax < mid ? mid : rMax; //如果有更大的则替换
                //注意这里不能直接返回，有可能不是最右边,继续搜索右边
                l = mid + 1;
            } else if (nums[mid] < target) {
                //说明目标如果存在的话一定在右半区，继续搜索右边
                l = mid + 1;
            } else {
                //nums[mid]>target,说名如果结果存在的话一定在左半区，继续搜索左边
                r = mid - 1;
            }
        }
        //如果 lMin还是等于r，说明，1不存在target元素，2r刚好是target元素
        return rMax != left ? rMax : nums[rMax] == target ? rMax : -1;
    }


    @Test
    public void test() {

//        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
//        int target=8;

//        int[] nums = new int[]{1, 10, 10, 10, 10, 10};
//        int target=8;

        //预期结果[10,13]
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6, 6, 8, 10, 10};
        int target = 4;
        int[] ans = searchRange(nums, target);
        System.out.println(Arrays.toString(ans));
    }
}
