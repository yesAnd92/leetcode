package com.wangyj.problems.dichotomy;

/**
 * 有序数组中的单一元素
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 */
public class SingleNonDuplicate_540 {


    public static void main(String[] args) {
        int[] arr=new int[]{1,1,2,2,3,4,4};
        int left = 0;
        int right = arr.length-1;
        while(true){
            int mid = left+(right-left)>>1;
            if (arr[mid]==arr[mid-1]){
                //说明单一元素在右边
                left=mid+1;
            }{
                right=mid;
            }

        }
    }
}
