package com.wangyj.problems.dynamic_programming;


import org.junit.Test;

/**
 * 求两个字符串的公共子串，好像不是leetecode上的题目
 *
 * @author W.Y.J
 * @Date 2021/8/8 9:38 上午
 */
public class LongestCommonSubString {


    
    /**
     * 如果s1和s2存在公共子串，若s1中公共子串结束位置为i,s2中公共子串的结束位置为j，要想找到最长公共子串，
     * 需要往前移动考察s1[i-1]=s2[j-1]...s1[i-length]=s2[j-length],length就是最长公共长度，
     * 这应该是直接想到的思路，也就是暴力解法，但是这俩变有大量的重复计算，想办法减少计算
     * 能不能说我知道i-1,j-1位置对应的最大公共长度，那那么i、j对应的最大公共长度不就在前边的基础上+1么？
     * 定义dp[i][j]表示字符串str1中第i个字符和str2中第j个字符为最后一个元素所构成的最长公共子串
     * 那么状态转移方程：
     * 如果不相等，不能构成子串 dp[i][j]=0
     * 如果相等 推给前边去计算 dp[i][j]=dp[i-1][j-1]+1
     * @author W.Y.J
     * @Date 2021/8/8 10:32 上午
     * @param s1	
     * @param s2	
     * @return java.lang.String
     */
    public String longestCommonSubString(String s1, String s2) {

        int l1 = s1.length();
        int l2 = s2.length();
        int maxLength=0;
        int maxIndex=0;
        int[][] dp = new int[l1][l2];
        printArr(dp);
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    //状态转移方程，考虑边界问题
                    dp[i][j] = dp[i - 1 < 0 ? 0 : i - 1][j - 1 < 0 ? 0 : j - 1] + 1;
                }else {
                    dp[i][j]=0;
                }

                if (dp[i][j]>maxLength){
                    maxLength=dp[i][j];
                    maxIndex=i-maxLength+1;
                }
            }
        }

        printArr(dp);

        return s1.substring(maxIndex,maxIndex+maxLength);

    }

    private void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int[] col = arr[i];
            for (int j = 0; j < col.length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }


    @Test
    public void test() {
        System.out.println(longestCommonSubString("abck", "sckkkk"));
    }
}
