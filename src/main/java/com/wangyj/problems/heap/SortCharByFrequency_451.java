package com.wangyj.problems.heap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 根据字符出现频率排序
 * https://leetcode.cn/problems/sort-characters-by-frequency/
 */
public class SortCharByFrequency_451 {


    @Test
    public void test() {

//        System.out.println(frequencySort("Aabb"));
//        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("tretergdgdfhe"));
    }


    public String frequencySort(String s) {

        //统计字符出现的频率
        Map<Character, Integer> statMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            statMap.put(c, statMap.getOrDefault(c, 0) + 1);
        }

        int cCount = statMap.size();
        //构建一个大顶堆，保存有序的结果
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(cCount,(o1, o2) -> o2.getValue() - o1.getValue());
        //统计多少个不同的字符，也即是queue的有效长度
        for (Map.Entry<Character, Integer> entry : statMap.entrySet()) {
            queue.add(entry);
        }
        //遍历拼凑结果
        StringBuilder sBuilder = new StringBuilder();
//        这种是错误的遍历方式，queue队列内并不保证排好序，只保证队头元素是最大的或者最小的。
//        也就是出队是有序的，因此这种方式遍历时不对的
//        queue.stream().forEach(e -> {
//            Character key = e.getKey();
//            for (int i = 0; i < e.getValue(); i++) {
//                sBuilder.append(key);
//            }
//        });

        while (!queue.isEmpty()){
            Map.Entry<Character, Integer> e = queue.poll();
            Character key = e.getKey();
            for (int i = 0; i < e.getValue(); i++) {
                sBuilder.append(key);
            }
        }

        return sBuilder.toString();
    }
}
