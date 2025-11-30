package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class MergeSort {

    public void mergeSort(int[] nums) {

        divide(nums, 0, nums.length - 1);

    }

    private void divide(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        divide(nums, left, mid);
        divide(nums, mid + 1, right);
        merge(nums, left, mid, right);

    }

    private void merge(int[] nums, int left, int mid, int right) {

        int i = left;
        int j = mid + 1;

        //临时数组存储结果
        int[] temp = new int[right - left + 1];
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                temp[k++] = nums[j++];
            } else {
                temp[k++] = nums[i++];
            }
        }


        while (i <= mid) {
            temp[k++] = nums[i++];
        }


        while (j <= right) {
            temp[k++] = nums[j++];
        }

        //最后将临时数组覆盖
        for (int l = 0; l < k; l++) {
            nums[left + l] = temp[l];
        }

    }

    @Test
    public void test(){
        int[] nums=new int[]{1,3,2,5,7,68,88,5,44};
        mergeSort(nums);

        for (int num : nums) {
            System.out.print(num+" ");
        }
    }
}
