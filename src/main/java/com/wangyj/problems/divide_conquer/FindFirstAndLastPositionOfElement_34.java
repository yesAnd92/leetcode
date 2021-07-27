package com.wangyj.problems.divide_conquer;

import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @author W.Y.J
 * @Date 2021/7/27 11:23
 */
public class FindFirstAndLastPositionOfElement_34 {

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        findBound(ans,nums,0, nums.length,target);
        return ans;
    }


    private void  findBound(int[] ans,int[] nums, int left, int right,int target) {
        if (left>right){
            return;
        }
        if (left==right){
            if(nums[left]!=target){
                return;
            }
            int min=ans[0];
            if (left<min){
                ans[0]=left;
            }
            int max=ans[1];
            if (right>max){
                ans[1]=max;
            }
        }
        int mid = (right - left) >> 1;
        if (mid==0)
            return;
        findBound(ans,nums,left,mid,target);
        findBound(ans,nums,mid+1,right,target);

    }


    @Test
    public void test(){
        int[] nums = new int[]{5,7,7,8,8,10};
        int[] ans = searchRange(nums, 8);
        System.out.println(ans);
    }
}
