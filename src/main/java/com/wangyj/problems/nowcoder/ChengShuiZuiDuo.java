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

            //由于短板决定容量，假设当前left为短板，我想找更大的水量，理论上只能left右移才有可能找到更大的水量，因为如果right左移
            //短板还是left，但是两个指针的距离更小了，肯定比当前水量更小。
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
