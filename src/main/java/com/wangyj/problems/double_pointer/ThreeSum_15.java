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

    //a+b+c=0,也就是-c如果也在数组中，即满足条件
    //由于数组已经是有序的了，我们能不能利用双指针呢？从首尾分别开始，如果两个数和小于-c,左指针右移，才有可能使a+b=-c
    //反之，右指针左移
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //特判
        int length = nums.length;
        if (length < 3) return ans;
        //题目要求，不可包含重复的三元组，这是难点，但这也是提示，只能先排序（不可能查完所有的再去重吧？）
        //以保证数据不重复
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            //由于是排过序的，右边的数都不比这个小，因此后边的不可能相加和为0
            if (nums[i] > 0)
                return ans;
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = length - 1;

            //不断从两端向中间遍历
            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if (tmp == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //也要避免移动过程中有重复的元素，也会导致重复数据
                    while (left < right && nums[left + 1] == nums[left]) left++;
                    while (left < right && nums[right - 1] == nums[right]) right--;
                    left++;
                    right--;
                } else if (tmp > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }




    @Test
    public void test() {
        int nums[] = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);
        lists.forEach(l -> {
            l.forEach(e -> System.out.print(e + "  "));
            System.out.println();
        });
    }
}
