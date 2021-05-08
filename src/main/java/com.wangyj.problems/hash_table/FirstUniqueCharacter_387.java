package com.wangyj.problems.hash_table;

import java.util.Hashtable;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 * @Author W.Y.J
 * @Date 2021/5/7 23:01
 */
public class FirstUniqueCharacter_387 {

    /**
     * 涉及到次数的题目都可以用hashmap?
     * @param str
     * @return
     */
    public static int firstUniqChar(String str) {
        Map<Character, Integer> map = new Hashtable<>();
        //第一次遍历统计统计出各个字符出现的次数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        //第二次遍历，对应的字符如果只出现一次即为所求结果
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == 1)
                return i;
        }
        return -1;
    }


    public static int firstUniqChar2(String str) {
        char[] ch= new char[26];
        //第一次遍历统计统计出各个字符出现的次数,但是不使用map，提高性能
        for (int i = 0; i < str.length(); i++) {
            //-‘a’是为了计算字符 a、b、c在ch中的下标
            ch[str.charAt(i)-'a']++;
        }

        //第二次遍历，对应的字符如果只出现一次即为所求结果
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (ch[str.charAt(i)-'a'] == 1)
                return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        String str ="loveleetcode";
        System.out.println(firstUniqChar(str));
    }
}
