package com.wangyj.problems.array;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 合并两个有序的数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class MergeAortedArray_88 {


    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //特判
        int length = nums1.length;
        if (n == 0) return;

        Queue<Integer> queue = new ArrayBlockingQueue<>(m + 1);

        int n1 = 0, n2 = 0;

        for (int i = 0; i < length; i++) {
            //每次循环都将nums1中的元素丢到queue中，比较时从queue中取
            if (n1 < m) {
                queue.offer(nums1[n1++]);
            }

            //nums2中数字已遍历结束后者比nums1中的小
            if (n2 >= n || (!queue.isEmpty() && queue.peek() < nums2[n2])) {
                //说明本次比较nums1中的数字比nums2中的小
                nums1[i] = queue.poll();
            } else {
                nums1[i] = nums2[n2++];
            }
        }
    }


    /**
     * 上边的方式借助一个队列，腾出空间存放比较后的结果，但还有更好的方式
     * nums1数组的后边部分对应的都是0，没有意义，我们能不能把这个利用起来？
     * 于是想到将比较后的结果从nums1后边插入，也就是每次找最大的，m和n的数据倒序遍历
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {

        //特判
        if (n == 0) return;

        //表示当前该在哪个位置插入比较后的数据
        int curr = m + n - 1;
        //分别表示指向m和n个数据的最后一位
        int m1 = m - 1, n1 = n - 1;
        while (m1 >= 0 || n1 >= 0) {

            if (m1 >= 0 && n1 < 0) {
                //nums2遍历完了，只需遍历nums1
                nums1[curr--] = nums1[m1--];
            } else if (n1 >= 0 && m1 < 0) {
                //nums1遍历完了，只需遍历nums2
                nums1[curr--] = nums2[n1--];
            }
            //都未遍历完的情况下，需要比较大小
            else if (nums1[m1] >= nums2[n1]) {
                nums1[curr--] = nums1[m1--];
            } else {
                nums1[curr--] = nums2[n1--];
            }
        }

    }


    @Test
    public void test() {

//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
//        int[] nums2 = new int[]{2, 5, 6};
//        merge2(nums1, 3, nums2, 3);

//        [0]
//        0
//        [1]
//        1
        int[] nums1=new int[]{0};
        int[] nums2=new int[]{2};
        merge(nums1,0,nums2,1);
        for (int i : nums1) {
            System.out.print(i + " ");
        }
    }
}
