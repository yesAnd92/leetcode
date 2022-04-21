package com.wangyj.problems.double_pointer;

import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class ValidPalindrome_125 {


    /**
     * 需要注意 '0'=48，'P'=80  'P'-'0'=32
     */
    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        char[] chars = s.toCharArray();
        while (left <= right) {
            if (!isNumberOrLetter(chars, left)) {
                left++;
                continue;
            }
            if (!isNumberOrLetter(chars,right)) {
                right--;
                continue;
            }
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;

        }

        return true;
    }

    public boolean isNumberOrLetter(char[] chars, int index) {
        char c = chars[index];
        if (c >= '0' && c <= '9')
            return true;

        //如果是字母，直接将大写转换成小写，避免'P'-'0'=32这种问题
        if (c >= 'A' && c <= 'Z') {
            chars[index] = (char) (c + 32);
            return true;
        }
        return (c >= 'a' && c <= 'z');
    }


    @Test
    public void test() {
//        System.out.println(isPalindrome(","));
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("0P"));

    }
}
