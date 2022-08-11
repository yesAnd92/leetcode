package com.wangyj.problems.dynamic_programming;

import org.junit.Test;

/**
 * 回文子串
 * https://leetcode.cn/problems/palindromic-substrings
 */
public class PalindromicSubstrings_647 {


    /**
     * 在暴力破解的基础上进行优化，为了避免重复验证可以利用"如果一个串是回文串，那么这个串去掉首尾它还是回文串"
     * 这个特性，我们可以记录下来已经判断过的子串。也就是dp
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        char[] array = s.toCharArray();

        boolean dp[][]=new boolean[length][length];
        Integer ans=0;

        for (int i = 0; i < length; i++) {
            dp[i][i]=true;
            ans++;
        }

        //这里j由于是单项递增，所以不存在重复统计结果的情况
        for (int j = 1; j <length ; j++) {
            for (int i = 0; i < j; i++) {
                if (array[i]==array[j]){
                    if (j-i<=2){
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if (dp[i][j]){
                    ans++;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {

        System.out.println(countSubstrings("aaa"));
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aba"));
    }
}
