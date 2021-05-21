package com.wangyj.problems.double_pointer;


import org.junit.Test;

/**
 * @author W.Y.J
 * @Date 2021/5/20 15:01
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray_26 {


    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        //特判
        if(length==0) return 0;
        //定义两个指针 left指向处理过的最后一个元素，right不断右移寻找不重复的元素
        int left = 0, right = 0;
        while (true) {
            while (right<length&&nums[left] == nums[right]) {
                //前后两个数相等，right右移
                right++;
            }
            if (right==length) break;
            //将下一个不重复的值，赋值给left指针的下一个位置
            nums[left+1]=nums[right];
            //left右移,right下一次要寻找的就是和left对应值不同的
            left++;
        }
        return left+1;
    }


    @Test
    public void test() {
        /**
         * test case:
         *  0,0,1,1,1,2,2,3,3,4 -->5 [0,1,2,3,4]
         */

//        int[] nums=new int[]{0,0,1,1,1,2,2,3,3,4,9,9,10};
        int[] nums=new int[]{};
        int ans = removeDuplicates(nums);
        for (int i = 0; i < ans; i++) {
            System.out.println(nums[i]);
        }
    }
}


