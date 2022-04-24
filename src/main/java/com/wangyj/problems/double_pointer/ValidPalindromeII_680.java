package com.wangyj.problems.double_pointer;

import org.junit.Test;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * https://leetcode-cn.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII_680 {


    //是否已经删除一个字符的标记
    boolean hasRemoved = false;

    /**
     * 左右两个指针分别指向首尾元素，比较是否相等
     * 如果相等，同时向后和向前移动一位，继续对比字符是否相等
     * 因为可以删除一个后继续对比，如果字符不相等，这种有两种可能，移除左边的一位字符或者移除右边一个字符
     * 移除后，如果是回文串，也是符合条件的
     */
    public boolean validPalindrome(String s) {

        char[] chars = s.toCharArray();

        return valid(chars, 0, chars.length - 1);

    }



    public boolean valid(char[] chars, int left, int right) {

        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            } else if (!hasRemoved) {
                hasRemoved = true;
                return valid(chars, left + 1, right) || valid(chars, left, right - 1);
            } else {
                return false;
            }

        }
        return true;
    }


    @Test
    public void test() {
//        String s = "baguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekougab";
        String s = "abbca";
        System.out.println(validPalindrome(s));
    }
}
