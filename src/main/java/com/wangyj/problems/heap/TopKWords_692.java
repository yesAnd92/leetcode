package com.wangyj.problems.heap;

import org.junit.Test;

import java.util.*;


/**
 * topK个单词
 * https://leetcode-cn.com/problems/top-k-frequent-words/
 */
public class TopKWords_692 {

    public List<String> topKFrequent(String[] words, int k) {

        int length = words.length;
        Map<String, Integer> stat = new HashMap<>();

        //先遍历一遍统计单词出现的频率
        for (int i = 0; i < length; i++) {
            stat.compute(words[i], (key, value) -> (value == null) ? 1 : ++value);
        }

        //这里用小顶堆解决topK的问题
        PriorityQueue<Ele> topK = new PriorityQueue<Ele>(k,(e1,e2)->{
            //频次优先，频次相同，按照字典序
            if (e1.times<e2.times||e1.times==e2.times&&e1.key.compareTo(e2.key)>0){
                return -1;
            }else {
                return 1;
            }
        });
        stat.forEach((key, value) -> {

            if (topK.size() < k || value>= topK.peek().times) {
                topK.add(new Ele(key,value));
                if (topK.size() > k) {
                    topK.poll();
                }
            }
        });

        List<String> ans = new ArrayList<>(k);
        //遍历优先级队列，放到结果容器中
        while (!topK.isEmpty()) {
            ans.add(topK.poll().key);
        }
        Collections.reverse(ans);
        return ans;
    }

    //为了在优先级队列里存放，频次和单词，构造了一个对象
    class Ele{
        private String key;
        private Integer times;


        public Ele(String key, Integer times) {
            this.key = key;
            this.times = times;
        }
    }




    /*****************************************************简单优化**************************************************/


    /**
     * 上边在优先级队列里同时存放频次和单词两个值，构造了一个对象。
     * 其实多余了，因为我们可以直接利用Map.Entity更方便
     */
    public List<String> topKFrequent2(String[] words, int k) {

        int length = words.length;
        Map<String, Integer> stat = new HashMap<>();

        //先遍历一遍统计单词出现的频率
        for (int i = 0; i < length; i++) {
            stat.compute(words[i], (key, value) -> (value == null) ? 1 : ++value);
        }

        PriorityQueue<Map.Entry<String,Integer>> topK = new PriorityQueue<>(k,(e1,e2)->{
            //频次优先，频次相同，按照字典序
            if (e1.getValue()<e2.getValue()||e1.getValue()==e2.getValue()&&e1.getKey().compareTo(e2.getKey())>0){
                return -1;
            }else {
                return 1;
            }
        });

        stat.entrySet().forEach(e -> {

            if (topK.size() < k || e.getValue()>= topK.peek().getValue()) {
                topK.add(e);
                if (topK.size() > k) {
                    topK.poll();
                }
            }
        });

        List<String> ans = new ArrayList<>(k);

        while (!topK.isEmpty()) {
            ans.add(topK.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }

    @Test
    public void test() {
        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequent(new String[]{"the", "day", "is","is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 10));

        System.out.println(topKFrequent2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequent2(new String[]{"the", "day", "is","is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 10));

    }
}
