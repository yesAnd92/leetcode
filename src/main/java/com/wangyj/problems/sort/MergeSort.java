package com.wangyj.problems.sort;


import org.junit.Test;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author W.Y.J
 * @Date 2021/7/21 9:50 下午
 */
public class MergeSort {


    /**
     * 归并排序的主要思想就是分治，
     * 通过递归把数组分成N个小数组，每个数组只包含一个元素，这样每个部分都是有序的
     * 之后问题就转换为合并两个有序数组了，挨个元素比较，小的放到前边
     * @param arr
     */
    public void mergeSort(int[] arr) {
        divide(arr, 0, arr.length - 1);
    }

    private void divide(int[] arr, int left, int right) {

        if (left < right) {
            int mid = (left + right) >> 1;
            //左右两边的进行分别排序
            divide(arr, left, mid);
            divide(arr, mid + 1, right);
            //进行合并
            merge(arr, left, mid, right);
        }
    }


    //将两个有序数列a1[left...mid]和a2[mid+1...right]合并
    private void merge(int[] arr, int left, int mid, int right) {

        //存储合并的临时结果
        int[] tmp = new int[right - left + 1];
        //i是数组a1的左端点，j是a2的左端点
        int i = left, j = mid + 1;
        //m是数组a1的右端点，n是a2的右端点
        int m = mid, n = right;
        int k = 0;
        while (i <= m && j <= n) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        //上边循环结束后，i还小于m说明，a2数组已经遍历完了，a1中剩下的都比a2的大
        while (i<=m){
            tmp[k++]=arr[i++];
        }

        //道理同上
        while (j<=n){
            tmp[k++]=arr[j++];
        }

        for (int l = 0; l < k; l++) {
            //将合并后的有序数组复制到arr对应位置
            arr[left+l]=tmp[l];
        }
    }


    @Test
    public void testMergeSort(){
        int[] arr= {3,2,3,1,2,4,5,5,6};
//        int[] arr= {4,7,6,5,3,2,8,1};
        mergeSort(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }


}
