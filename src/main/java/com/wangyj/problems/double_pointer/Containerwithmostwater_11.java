package com.wangyj.problems.double_pointer;

import org.junit.Test;

/**
 * @Author W.Y.J
 * @Date 2021/5/18 20:45
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class Containerwithmostwater_11 {


    /**
     * 暴力双循环就写了
     * 这道题最难想到的是使用双指针，两个指针一个从最左边（left）开始，一个从最右边（right）开始
     * 两个指针对应的值应该就是题目中的矩形中的两个底，高是两个指针的间距。
     * 两个指针该从哪边开始移动呢？应该是看两个指针对应的值，值比较小的朝对方移动，无论两边哪个移动
     * 两个指针的间隔，也就是高会越来越小，当前这两个指针的位置如果不是最大面积，那么只有舍弃较小的值往下移动，
     * 在高减小的情况下，才会有机会出现最大值
     */
    public int maxArea(int[] height) {
        int max=0;
        //左右指针的初始位置
        int left = 0, right = height.length-1;
        //两个指针相遇，遍历结束
        while (left != right) {
            int a = height[left];
            int b = height[right];
            //计算当前面积与之前的最大的面积比较
            max =Math.max(max,Math.min(a,b)*(right-left));
            //值较小的朝向另一个移动
            if (a < b){
                left++;
            }else {
                right--;
            }
        }
        return max;
    }


    @Test
    public void test(){
        int[] height= new int[]{1,2,1};
        System.out.println(maxArea(height));
    }

}
