package com.wangyj.problems.dichotomy;

import org.junit.Test;

/**
 * 搜索插入位置
 * https://leetcode-cn.com/problems/search-insert-position/
 */
public class SearchInsertPosition_35 {


    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right=nums.length-1;

        //考虑特殊情况，target小于第一个元素
        if (nums[0]>target)
            return 0;

        while (left<right){

            int mid =left + (right-left+1)/2;
            if (nums[mid]<=target){
                left=mid;
            }else {
                right=mid-1;
            }
        }

        return nums[left]==target?left:left+1;
    }


    @Test
    public void test(){
        System.out.println(searchInsert(new int[]{1,2,3,4}, 0));
    }
}
