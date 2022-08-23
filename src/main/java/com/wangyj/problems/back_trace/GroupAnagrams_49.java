package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode.cn/problems/group-anagrams/
 * 字母异位词分组
 */
public class GroupAnagrams_49 {


    @Test
    public void test() {

        String[] strings = {"eat","tea","tan","ate","nat","tab","tab"};
        List<List<String>> ans = groupAnagrams2(strings);
        System.out.println(ans);
    }


    /**
     * 本方法的思路是取出一个字符串，对这个字符串的字符进行全排列，拿到这个字符串对应的所有字母异位词
     * 再去剩下的比较
     * 缺点是：超时了。。。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> ans = new LinkedList<>();


        Map<String,Integer> statMap = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        for (String str : strs) {
            statMap.put(str,statMap.getOrDefault(str,0)+1);
            queue.add(str);
        }


        while (!queue.isEmpty()){
            List<String> tmp = new ArrayList<>();

            String key = queue.poll();
            Set<String> permuteSet = permuteStr(key);
            for (String s : permuteSet) {
                Integer count = statMap.remove(s);
                for (int i = 0; count!=null&&i <count; i++) {
                    tmp.add(s);
                }
            }
            if (tmp.size()>0){
                ans.add(tmp);
            }
        }

        return ans;
    }


    private Set<String> permuteStr(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        StringBuilder trace = new StringBuilder();
        Set<String> ans = new HashSet<>();
        dfs(ans, chars, trace,  length);

        return ans;
    }

    private void dfs(Set<String> ans, char[] chars, StringBuilder trace, int length) {

        if (trace.length() == length) {
            ans.add(trace.toString());
            return;
        }

        for (int i = 0; i < length; i++) {
            char target = chars[i];
            for (int j = 0; j < trace.length(); j++) {
                if (trace.charAt(j)==target){
                    continue;
                }
            }
            trace.append(target);
            dfs(ans, chars, trace,  length);
            //回退
            trace.deleteCharAt(trace.length() - 1);
        }

    }

    /*****************************************************以下为更适合本题的解法**************************************************/


    /**
     * 对于这个题来说，还有更好的解题思路
     * 如果两个串是字母异构词，那么把这两个串分别按字符拆开排序后，结果是相同的，利用这个特点可以快速解决这个问题。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {

        //map里存放的结构是，<字符串中字符排序后结果，List<原字符串>>
        Map<String,List<String>> ans= new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = ans.getOrDefault(key, new ArrayList<>());
            list.add(str);
            ans.put(key,list);
        }

        return new ArrayList<>(ans.values());
    }

}
