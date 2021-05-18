package com.wangyj.problems.double_pointer;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters_3 {


    /**
     * 使用滑动窗口
     *错误的思想： 遇到重复的字符，只是将左边界left更新为 重复字符的下一个位置。以字符串"google"为例，当right移动到索引2的时候，字符'o'重复，
     * 将left更新为map.get('o') + 1 = 2，当right移动到索引3的时候，字符'g'重复，将left更新为map.get('g') + 1 = 1。
     * 此时出现left向左移动的情况，[left,right]窗口字符串为"oog"显然是错误的，所以应将left更新为max(left, map.get('g') + 1) = 2。
     *
     * @param str
     * @return
     */
    public int lengthOfLongestSubstring(String str) {
        //特判
        if ("".equals(str)) {
            return 0;
        }
        //记录最大值
        int max = 0;
        //滑动窗口的开始、结束指针
        int start = 0, end = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        while (end < str.length()) {
            //不断右移将字符加入窗口
            char c = str.charAt(end);
            if (map.containsKey(c)){
                //如果遇到重复的字符，将窗口左边界更新为
                // max(left, 重复字符的下一个位置)，取两者之中的较大值是防止左边界left向左移动。
                start=Math.max(start,map.get(c)+1);
            }
            map.put(c,end);
            max = Math.max(max, end - start+1 );
            end++;
        }
        return max;
    }

    @Test
    public void test() {
        String str = "pwwkew";
        int i = lengthOfLongestSubstring(str);
        System.out.println(i);
    }
}
