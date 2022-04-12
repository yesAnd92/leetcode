package com.wangyj.problems.string;


import org.junit.Test;

/**
 * 反转字符串，要求空间复杂度是O(1)
 * https://leetcode-cn.com/problems/reverse-string/
 * @author W.Y.J
 * @Date 2022/4/12 16:28
 */
public class ReverseString_344 {

    public void reverseString(char[] s) {
        int swapCount= s.length>>1;
        for (int i = 0; i <swapCount ; i++) {
            int target = s.length - i - 1;
            char tmp=s[i];
            s[i]=s[target];
            s[target]=tmp;
        }
    }

    @Test
    public void test(){
        char[] s= new char[]{'a','b','c','d','e','o'};
//        char[] s= new char[]{'a','b','c','d','e'};
//        char[] s= new char[]{'a'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c+">>");
        }
    }
}
