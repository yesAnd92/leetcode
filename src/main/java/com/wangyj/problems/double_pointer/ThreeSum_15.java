package com.wangyj.problems.double_pointer;

/**
 * @Author W.Y.J
 * @Date 2021/5/18 22:43
 * <p>
 * https://leetcode-cn.com/problems/3sum/
 */

import org.junit.Test;

import java.util.*;

public class ThreeSum_15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //特判
        int length = nums.length;
        if (length <3) return result;
        //题目要求，不可包含重复的三元组，这是难点，但这也是提示，只能先排序（不可能查完所有的再去重吧？）
        //以保证数据不重复
        Arrays.sort(nums);
        //a+b+c=0,也就是-c如果也在数组中，即满足条件
        //由于数组已经是有序的了，我们能不能利用双指针呢？从首尾分别开始，如果两个数和小于-c,左指针右移，才有可能使a+b=-c
        //反之，右指针左移
        for (int e = 0; e < length; e++) {
            int left = e+1, right = length - 1;
            int target = -1 * nums[e];
            while (left <right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> r = new ArrayList<>();
                    Collections.addAll(r, nums[e], nums[left], nums[right]);
                    result.add(r);
                    left++;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }


    @Test
    public void test() {
        int nums[] = new int[]{0,0,0,0};
        List<List<Integer>> lists = threeSum(nums);
        lists.forEach(l -> {
            l.forEach(e -> System.out.print(e + "  "));
            System.out.println();
        });
    }
}
