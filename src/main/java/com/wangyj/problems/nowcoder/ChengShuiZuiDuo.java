package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class ChengShuiZuiDuo {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param height int整型一维数组
     * @return int整型
     */
    public int maxArea(int[] height) {
        // write code here
        int len = height.length;
        if (len < 2) {
            return 0;
        }

        int  left=0,right=len-1,re = 0;

        while(left<right){

            int water = Math.min(height[left], height[right]) * (right - left);
            if (water > re) {
                re = water;
            }

            //无论我们移动 left 还是 right，宽度 (right - left) 都一定会减小。
            //面积要想比当前更大，高度 min(height[left], height[right]) 必须增大，以弥补宽度减小带来的损失。
            //当前的高度是由 height[left] 和 height[right] 中较小的那个决定的。我们称它为 h_min。
            //如果我们移动高度较大的那个指针，新的高度仍然是 min(h_min, 新指针的高度)。这个值不可能超过 h_min。
            if (height[left]<height[right]){
                left++;
            }else {
                right--;
            }

        }

        return re;
    }


    /**
     * 这是暴力双循环，即便考虑到只查找一半，也超时了
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        // write code here

        int len = height.length;
        if (len < 2) {
            return 0;
        }

        int  re = 0;

        for (int left = 0; left <=len/2+1; left++) {
            int right = left + 1;
            while (right < len) {
                int water = Math.min(height[left], height[right]) * (right - left);
                if (water > re) {
                    re = water;
                }
                right++;
            }
        }

        return re;
    }


    @Test
    public void test(){
        int[] height=new int[]{6,4,3,1,4,6,99,62,1,2,6};
        System.out.println(maxArea(height));
    }
}
