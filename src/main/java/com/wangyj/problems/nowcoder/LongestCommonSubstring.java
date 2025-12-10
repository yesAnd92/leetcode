package com.wangyj.problems.nowcoder;

import org.junit.Test;

/**
 * 最长公共子串
 */
public class LongestCommonSubstring {

    public String LCS(String str1, String str2) {

        // write code here

        //dp[i][j]表示以str的第i个字符为结尾，str2第j个字符为结尾的最长公共子串
        int l1 = str1.length();
        int l2 = str2.length();
        if (l1 < 1 || l2 < 1) {
            return "";
        }

        int[][] dp = new int[l1 + 1][l2 + 1];

        int max = 0;
        int maxIndex = 0;


        //为了防止下标越界，从1开始遍历
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {

                //这里i从1开始，字符串实际是0开始
                if (str1.charAt(i-1) != str2.charAt(j-1)) {
                    dp[i][j] = 0;
                } else {
                    //如果相等，要看两者都往前移一位的情况
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                if (dp[i][j] > max) {
                    maxIndex = i;
                    max = dp[i][j];
                }
            }

        }

        if(max==0){
            return "";
        }

        return str1.substring(maxIndex - max , maxIndex );

    }


    @Test
    public void test() {
        String lcs = LCS("1AB2345CD", "12345EF");
        System.out.println(lcs);

        String lcs2 = LCS("zzzzz", "zzzzz");
        System.out.println(lcs2);
    }
}
