package com.wangyj.problems.double_pointer;

import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays_4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int length1 = nums1.length;
        int length2 = nums2.length;
        if(length1==0&&length2==0)return 0;
        int n1 = 0;
        int n2 = 0;
        int middleIndex = (length1 + length2) / 2;
        double middle = Double.MIN_VALUE;
        double middleBefore = Double.MIN_VALUE;
        for (int i = 0; i < middleIndex + 1; i++) {
            int e1 = length1==0?Integer.MAX_VALUE:nums1[n1];//当nums1为空时，令e1最大，强制从nums2中取
            int e2 = length2==0?Integer.MAX_VALUE:nums2[n2];//同上
            if (e1 > e2) {
                //优先从nums2中取，nums2取完了再从nums1中取
                middleBefore=middle;
                middle = nums2[n2];
                if (n2 == length2 - 1) {
                    nums2[n2] = Integer.MAX_VALUE;
                } else {
                    n2++;
                }
            } else {
                //优先从nums1中取，nums1取完了再从nums2中取
                middleBefore=middle;
                middle = nums1[n1];
                if (n1 == length1 - 1) {
                    nums1[n1] = Integer.MAX_VALUE;
                } else {
                    n1++;
                }
            }
        }
        if ((length1 + length2) % 2==0) {
            //偶数个
            return (middle+middleBefore)/2;
        }
        return middle;
    }

    @Test
    public void test() {
        int[] num1 = new int[]{1,2,3,9,10};
        int[] num2 = new int[]{2,2,10};
        System.out.println(findMedianSortedArrays(num1, num2));
    }
}
