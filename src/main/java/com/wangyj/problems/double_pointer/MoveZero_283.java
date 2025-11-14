package com.wangyj.problems.double_pointer;

import org.junit.Test;

public class MoveZero_283 {

    public void moveZeroes(int[] nums) {

        int i=0;//覆盖位置
        int j=0;//右移指针

        while(j<nums.length){
            if (nums[j]!=0){
                nums[i++]=nums[j];
            }
            j++;
        }

        //[i,nums.length]区间用零补充
        while (i<nums.length){
            nums[i++]=0;
        }
        for (int num : nums) {
            System.out.println(num+" ");
        }
    }

    @Test
    public void test(){
        int[] nums=new int[]{1,2,0,3,4,0,0,1};

        moveZeroes(nums);
    }
}
