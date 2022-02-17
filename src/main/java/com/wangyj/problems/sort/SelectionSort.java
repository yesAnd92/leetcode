package com.wangyj.problems.sort;

import org.junit.Test;

public class SelectionSort {

    /**
     * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，
     * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     */
    public void selectionSort(int[] arr) {

        int length = arr.length;
        //每次选择最大的元素，最大的元素放在最后边
        for (int i = 0; i < length; i++) {
            int maxIndex=0;
            int max=arr[0];
            for (int j = 1; j < length-i; j++) {
                if (arr[j]>max){
                    max=arr[j];
                    maxIndex=j;
                }
            }
            //交换最大值到后边
            swap(arr,maxIndex,length-i-1);
        }
        print(arr);
    }


    /**
     * 从最小的开始选择，代码比较简洁
     */
    public void selectionSort2(int[] arr) {

        int length = arr.length;
        //每次选择最大的元素，最大的元素放在最后边
        for (int i = 0; i < length; i++) {
            int minIdx= i;
            for (int j = i+1; j <length ; j++) {
                if (arr[i]>arr[j]){
                    minIdx=j;
                }
            }
            //交换最小的元素到i位置
            swap(arr,i,minIdx);
        }
        print(arr);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 4, 3, 5, 8, 7, 7, 6,2};
//        selectionSort(arr);
        selectionSort2(arr);
    }
}
