package com.wangyj.problems.sort;


import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author W.Y.J
 * @Date 2021/7/4 10:09 上午
 */
public class BubbleSort {



    /**
     * 冒泡排序
     * 这里需要理解下两层循环的，目的
     * 第一层：每个数字都要遍历一遍
     * 第二层：已经排序过的无需再排，所以要length-i-1
     * @author W.Y.J
     * @Date 2021/7/2 11:27 下午
     * @param arr
     * @return void
     */
    public void bubbleSort(int[] arr){
        int length = arr.length;
        for (int i =0;i<length;i++){
            //先找出最大的放到最后
            for (int j =0;j<length-i-1;j++)
                if(arr[j]>arr[j+1]){
                    int swap = arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=swap;
                }
        }
    }



    @Test
    public void demo(){
        int[] arr= {5, 9, 4, 6, 5, 3,7};
//        partition(arr,0,arr.length-1);
        bubbleSort(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }

}
