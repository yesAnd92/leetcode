package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class ReverseString {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 反转字符串
     *
     * @param str string字符串
     * @return string字符串
     */
    public String solve(String str) {
        // write code here
        if (str == null || str == "") {
            return "";
        }
        int le = str.length();
        char[] re = new char[le];
        int j = 0;
        for (int i = le - 1; i >= 0; i--) {
            re[j++] = str.charAt(i);
        }
        return new String(re);
    }

    @Test
    public void test(){
        System.out.println(solve("abcd"));
        System.out.println(solve(""));
        System.out.println(solve("a"));
        System.out.println(solve("aa"));
    }
}
