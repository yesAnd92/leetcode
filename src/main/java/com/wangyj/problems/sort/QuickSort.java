package com.wangyj.problems.sort;

import org.junit.Test;

import java.util.Arrays;



/**
 * 快排
 * @author W.Y.J
 * @Date 2021/7/4 10:10 上午
 */
public class QuickSort {

    private void swap(int[] arr,int a,int b){
        int tmp = arr[a];
        arr[a]=arr[b];
        arr[b]=tmp;
    }

    /**************************************************单向快排**************************************************/

    @Test
    public void test(){
        int[] arr= {3,2,3,1,2,4,5,5,6};
        quickSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(System.out::print);
    }

    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int pivotIndex = partition(arr, begin, end);
            quickSort(arr,begin,pivotIndex-1);
            quickSort(arr,pivotIndex+1,end);
        }
    }



    /**
     * 采用单向遍历的方式
     * @author W.Y.J
     * @Date 2021/7/2 11:26 下午
     * @param arr
     * @param begin
     * @param end
     * @return int
     */
    private int partition(int arr[], int begin, int end) {
        int i = begin-1;//i记录一趟遍历下来比pivot小的最后一个元素的位置
        int pivot = arr[end];
        for (int j = begin; j <end ; j++) {
            if (arr[j]<=pivot){
                //只要比pivot小，就记录到a[i]中
                i++;
                swap(arr,i,j);
            }
        }
        //最后将pivot插入到比它小的后边
        int pivotIndex=i+1;
        swap(arr,pivotIndex,end);
        return pivotIndex;
    }
    /*******************************************双向快排**************************************************/

    //参考  https://zhuanlan.zhihu.com/p/102290441

    @Test
    public void testDoubleQuickSort(){
        int[] arr= {3,2,3,1,2,4,5,5,6};
//        int[] arr= {4,7,6,5,3,2,8,1};
        doubleQuickSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(System.out::print);
    }


    /**
     * 使用两个指针left 、right向中间遍历
     * left对应的值比pivot小，则left++继续向右遍历，否则暂停遍历
     * right
     * @author W.Y.J
     * @Date 2021/7/3 5:30 下午
     * @param arr
     * @param begin
     * @param end
     * @return void
     */
    public void doubleQuickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int pivotIndex = doublePartitions(arr, begin, end);
            doubleQuickSort(arr,begin,pivotIndex-1);
            doubleQuickSort(arr,pivotIndex+1,end);
        }
    }

    /**
     * 这个方法的作用就是将传入的区间分为两个子区间，并返回分隔点下标
     */
    private int doublePartitions(int[] arr, int begin, int end) {
        int left = begin;
        int right =end;
        int pivot=arr[begin];
        while (left!=right){
            /**
             * 先从右遍历和先从左遍历不同
             * 假设先从右遍历，left\right最终相等的位置对应的值一定是比pivot值小，因为如果arr[right]>=pivot，
             * right会继续左移（因为相等就不再移动，故谁先移动，谁就能多走一步）。因为最后arr[right]要和支点交换值，而arr[right]是比pivot小的值,
             * 这就决定了arr[right]只能交换到左边，也就是必须用左端的元素作为支点。
             *
             * 反之也是同样的道理：选用右端点作为支点，必须先遍历左边
             *
             * 还有一个要点是  如果选用左端元素作为支点，等值条件应该归属到左边（arr[left]<=pivot），也即自身不需要交换，跳过
             */
            while (left<right&&arr[right]>pivot)
                right--;
            while (left<right&&arr[left]<=pivot) {
                left++;
            }
            if (left<right){
                swap(arr,left,right);
            }
        }
        //交换支点和最后一个小于支点的元素，交换后支点所在位置应当就是排完序之后所在的位置
        swap(arr,begin,left);

        return left;
    }




}
