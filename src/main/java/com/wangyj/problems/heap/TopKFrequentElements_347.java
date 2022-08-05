package com.wangyj.problems.heap;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @author W.Y.J
 * @Date 2021/7/4 5:54 下午
 */
public class TopKFrequentElements_347 {


    /**
     * 直接借助优先接队列，尤其是在面试的时候没必要自己进行堆排实现
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> statMap = new HashMap<>();
        for (int num : nums) {
            statMap.put(num, statMap.getOrDefault(num, 0) + 1);
        }
        //小顶堆里直接存放Map.Entry<Integer, Integer>，重写compare接口
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));


        //使用小顶堆，小于队头元素的都不符合条件
        for (Map.Entry<Integer, Integer> entry : statMap.entrySet()) {
            if (queue.size() < k || queue.peek().getValue() < entry.getValue()) {
                queue.add(entry);
            }
            //超过k个，丢弃队头元素
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] ans = queue.stream().mapToInt(Map.Entry::getKey).toArray();

        return ans;
    }

    public int[] topKFrequent2(int[] nums, int k) {

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
