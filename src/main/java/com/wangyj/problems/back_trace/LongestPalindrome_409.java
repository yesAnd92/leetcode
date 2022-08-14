package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.*;
/**
 * 构造回文
 * https://leetcode.cn/problems/longest-palindrome/
 * @author W.Y.J
 * @Date 2022/8/14 19:48
 */
public class LongestPalindrome_409 {


    @Test
    public void test() {

        System.out.println(longestPalindrome2("abccccdd"));
    }


    /**
     * 「回文串」是指倒序后和自身完全相同的字符串，即具有关于中心轴对称的性质。观察发现，
     *
     * 当回文串长度为偶数时，则所有字符都出现了偶数次；
     * 当回文串长度为奇数时，则位于中心的字符出现了奇数次，其余所有字符出现偶数次；
     * 链接：https://leetcode.cn/problems/longest-palindrome/solution/409-zui-chang-hui-wen-chuan-by-jyd-ne80/
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {

         Integer  ans = 0;
        Integer odd=0;//标记是否出现了奇数个

        Map<Character,Integer> statMap=new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            statMap.put(curr,statMap.getOrDefault(curr,0)+1);
        }

        for (Map.Entry<Character, Integer> characterIntegerEntry : statMap.entrySet()) {

            Integer value = characterIntegerEntry.getValue();
            if (value %2==0){
                //这个字符共有偶数个，说明这些字符都可以参与回文数的构建
                ans+=value;
            }else {
                //字符有奇数个，比如x个，那么有x-1个可以参与构建回文数，同时剩余的亦可以构建一个也就是odd置为1
                ans=ans+value-1;
                odd=1;
            }

        }
        return ans+odd;
    }


    /**
     * 在第一版的基础上进行优化
     * @param s
     * @return
     */
    public int longestPalindrome2(String s) {

        Integer  ans = 0;
        Integer odd=0;//标记是否出现了奇数个

        Map<Character,Integer> statMap=new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            //优化统计字符量的写法
            statMap.merge(s.charAt(i),1, Integer::sum);

        }

        for (Map.Entry<Character, Integer> characterIntegerEntry : statMap.entrySet()) {

            Integer value = characterIntegerEntry.getValue();
            //向下取偶数
            int rem  = value % 2;
            ans+=value-rem;
            if (rem==1)
                odd=1;

        }
        return ans+odd;
    }

}
