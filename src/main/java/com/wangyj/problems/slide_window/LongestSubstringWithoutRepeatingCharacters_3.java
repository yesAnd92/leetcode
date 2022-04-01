package com.wangyj.problems.slide_window;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters_3 {


    /**
     * 使用滑动窗口解决这个问题
     * 这里我经常有个思维误区，试图想把窗口大小固定，窗口只允许扩大，不允许缩小
     * 这就会没办法找到一个合适的算法，应当分分两步走，
     * 1、根据不重复的条件调整窗口大小，并记录每次窗口大小
     * 2、比较这些窗口大小，找到一个最大的窗口
     */
    public int lengthOfLongestSubstring(String s) {

        int length = s.length();
        Map<Character,Integer> map  = new HashMap<>(length);
        //记录最大值
        int ans=0;

        for (int start=0,end=0; end < length; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)){
                /**
                 * 如果字符已经出现重复，需要将窗口的左边界右移
                 * 这里为什么要map.get(c)+1,start中取最大值，因为避免start左移，
                 * 某些场景下,比如"abba"，由于a添加的早，对应的下标值小，可能发生左移现象
                 */
                start=Math.max(map.get(c)+1,start);
            }
            map.put(c,end);
            ans=Math.max(ans,end-start+1);
        }
        return ans;
    }

    @Test
    public void test() {
        String str = "pwwkew";
        int i = lengthOfLongestSubstring(str);
        System.out.println(i);
    }
}
