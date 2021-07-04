package com.wangyj.problems.heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @author W.Y.J
 * @Date 2021/7/4 5:54 下午
 */
public class TopKFrequentElements_347 {

    public int[] topKFrequent(int[] nums, int k) {

        int[] re= new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //将map中的元素转换到数组上，因为最后的结果要的是元素，而不是对应的次数，故两个都要记录下来，
        // 使用数组存放元素和元素出现的次数
        int[][] ele = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ele[i][0] = entry.getKey();//第一个放出现的元素
            ele[i][1] = entry.getValue();//第二个放对应元素出现的次数
            i++;
        }

        //堆化
        int length = ele.length;
        for (int j = length /2-1; j >=0 ; j--) {
            heapify(ele, length, j);
        }

        //堆化后，第一个元素就是堆顶元素，也是最大元素,每次将堆顶元素和最后一个交换，交换k次后
        //第k大的元素就位于倒数第k的位置
        for (int j = 0; j < k; j++) {
            swap(ele,0, length -j-1);
            re[j]=ele[length-j-1][0];
            heapify(ele, length -j-1,0 );
        }

        return re;
    }

    void heapify(int[][] ele, int length, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < length && ele[left][1] > ele[largest][1])
            largest = left;
        if (right < length && ele[right][1] > ele[largest][1])
            largest = right;

        if (largest != i) {
            swap(ele,i,largest);
            heapify(ele,length,largest);
        }
    }

    void swap(int[][] e, int a, int b) {
        int[] tmp = e[a];
        e[a]=e[b];
        e[b]=tmp;
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,1,1,2,2,3,9,8,8,0};
        Arrays.stream(topKFrequent(nums,2)).forEach(System.out::print);
    }
}
