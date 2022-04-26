package com.wangyj.problems.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/majority-element/
 */
public class MajorityElement_169 {


    /**
     * 投票法
     * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
     * 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
     * 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
     */
    public int majorityElement(int[] nums) {
        int candidate=nums[0];
        int count=1;

        for (int i = 1; i < nums.length; i++) {
            if (candidate==nums[i]){
                count++;
            }else {
                count--;
            }
            if (count==0){
                candidate=nums[++i];
                count=1;
            }
        }

        return candidate;
    }


    /**
     * 排序法
     * 数组排好序后，由于众数数量大于全部数量的1/2,因此中间位置一定就是众数
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length>>1];
    }


    @Test
    public void test(){
        System.out.println(majorityElement(new int[]{2, 2, 4, 5, 6, 2, 2}));
        System.out.println(majorityElement2(new int[]{2, 2, 4, 5, 6, 2, 2}));
    }
}
