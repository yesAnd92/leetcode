package com.wangyj.problems.sort;


import org.junit.Test;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author W.Y.J
 * @Date 2021/7/4 10:19 上午
 */
public class HeapSort {


    /**
     * 堆化
     * 将数组的数据构建为大顶堆(升序通常构建大顶堆，降序也可以构建小顶堆)
     * 如果用数组存储堆结构，设一个元素在数组中的下标为i，则有以下性质：
     * 它的左孩子下标为2*i+1
     * 它的右孩子下标为2*i+2
     * 它的父节点下标为(i-1)/2
     *
     * @author W.Y.J
     * @Date 2021/7/4 10:25 上午
     * 参考  https://www.cnblogs.com/jingmoxukong/p/4303826.html
     */
    void heapify(int arr[], int length, int i) {

        int largest = i;//将最大值设置为堆顶元素
        int left = 2 * i + 1;//i的左孩子
        int right = 2 * i + 2;//i的右孩子

        //如果左孩子比父节点大，则令largest下标为左孩子下标
        if (left < length && arr[left] > arr[largest])
            largest = left;

        //如果右孩子比父节点大，则令largest下标为右孩子下标
        if (right < length && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            //父节点比左或右孩子小，需要交换位置
            swap(arr, i, largest);

            // 因为i,largest的位置互换了，左右孩子与父节点交换之后，原父节点的值可能比原左右孩子的孩子值还小，
            // 需要重新堆化其下的树，递归子堆
            heapify(arr, length, largest);
        }
    }


    void heapSort(int arr[], int length) {
        /**
         * 建立堆，i=n/2-1 是因为没有子叶的节点是不需要堆化的,
         * 这里为啥不直接从堆顶元素开始堆化呢？根据堆化的代码可以看到，
         * 递归过程并不是对所有的子树都堆化，这样就会漏掉部分子树堆化影响排序
         */
        for (int i = length / 2 - 1; i >= 0; i--)
            heapify(arr, length, i);

        /**
         * 堆化之后的数组已经是按照大顶堆的特性来组织的了，数组中的第一个元素就是堆顶元素，也是最大元素
         * 我们把它跟最后一个元素交换一下，那么最大的元素就放到了最后一个位置，接下来把前边剩下的length-1个元素重新堆化
         * 接着再取堆顶元素放到倒数第二个位置，一直重复这个过程，直到最后堆就剩下一个下标为0的元素，排序就完成了
         */
        for (int i = length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }


    void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    @Test
    public void test() {
        int[] arr = new int[]{1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
        heapSort(arr,arr.length);
        Arrays.stream(arr).forEach(System.out::print);
    }
}
