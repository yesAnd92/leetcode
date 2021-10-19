package com.wangyj.problems.dynamic_programming;

import org.junit.Test;

/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * @author W.Y.J
 * @Date 2021/8/12 14:29
 */
public class LongestCommonSubsequence_1143 {

    public int longestCommonSubsequence(String text1, String text2) {


        int l1 = text1.length();
        int l2 = text2.length();

        int[][] dp = new int[l1+1][l2+1];

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    //如果相等，问题就等价于text1[0,i]和text2[0,j]去除这个末尾的公共字符+1
                    dp[i][j] = dp[i - 1 ][j - 1] + 1;
                } else {
                    /**
                     * 不相等的状态转移不太好想到，text1[0,i]和text2[0,j]两个字符串的最长公共子序列
                     * 它等价于这两个字符串分别去掉这个当前字符后与另一个字符串的最长公共子序列，两个子序列中最长的那个
                     * 也就是max{text1[0,i]&text2[0,j-1],text1[0,i-1]&text2[0,j]}
                     */
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }

        }
        printArr(dp);
        //所求结果就是我们定义的状态，无需再定义一个最大值存储结果
        return dp[l1][l2];
    }



    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        printArr(dp);
        return dp[m][n];
    }


    private void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int[] col = arr[i];
            for (int j = 0; j < col.length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void test() {
        String text1 = "aa";
        String text2 = "aaaaaaa";
        System.out.println(longestCommonSubsequence(text1, text2));
    }


}
