package com.wangyj.problems.number;


import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class PalindromeNumber_9 {


    /**
     * 转换成字符串的就不写了，用数学方法吧
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int y = x;
        int ans = 0;
        while (x != 0) {
            //这里不用考虑溢出问题，因为如果是回文数，反转也不会溢出，如果不是溢出后于原值不能，也满足题意
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans==y;
    }

    @Test
    public void test(){
//        System.out.println(isPalindrome(112311));
//        System.out.println(isPalindrome(121));
//        System.out.println(isPalindrome(1));
//        System.out.println(isPalindrome(-121));
//        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(Integer.MAX_VALUE-1));
    }
}
