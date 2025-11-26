package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class FindKth {


    public int findKth(int[] a, int n, int K) {
        // write code here

        quick(a,0,n-1,n,K);
        return a[n-K];
    }

    public void quick(int[] a, int low, int high, int n, int k) {
        //按照快排的思路，第k大的对应的坐标就是n-k

        if (low < high) {
            int index = partition(a, low, high);

            if (index < n - k) {
                //本次支点小于n-k,目标值在右侧
                quick(a, index + 1, high, n, k);
            } else if (index > n - k) {
                quick(a, low, index - 1, n, k);
            } else {
                //index=n-k  刚好是结果
            }
        }

    }

    public int partition(int[] a, int low, int high) {
        int pivot = a[low];
        int left = low, right = high;

        while (left < right) {
            while (left < right && a[right]>= pivot) {
                right--;
            }
            while (left < right && a[left] <= pivot) {
                left++;
            }

            if (left < right) {
                swap(a, left, right);
            }
        }

        //最后交换
        swap(a, low, right);
        return left;
    }


    public void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    @Test
    public void test(){
        int[] nums=new int[]{6,10,9,9,8,7,5,6,4,3,4,2};
        System.out.println(findKth(nums,12,12));
    }
}
