package com.wangyj.problems.dynamic_programming;


import org.junit.Test;

/**
 * 最长回文数
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @author W.Y.J
 * @Date 2021/8/5 15:12
 */
public class LongestPalindromicSubstring_05 {

    /**
     * 暴力,超时
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        //特判
        if (s.length() == 1)
            return s;

        String longestSub = "";
        int longest = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub)) {
                    if (sub.length() > longest) {
                        longest = sub.length();
                        longestSub = sub;
                    }
                }
            }
        }
        return longestSub;

    }

    private boolean isPalindrome(String str) {
        int length = str.length();
        int mid = length >> 1;
        for (int i = 0; i < mid; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }


    /*****************************************************动态规划**************************************************/

    /**
     * 回文串有个非常重要的特性：如果一个串是回文串，那么这个串去掉首尾它还是回文串
     * 可以不断的缩短判断一个子串是不是回文串，可利用dp来空间换时间
     * 如果用dp[i][j]表示字符串s从下标i到小标j这个子串是否是回文串
     * 那么动态转移方程可以描述为dp[i][j]=dp[i+1][j-1];
     *
     * @param s
     * @return java.lang.String
     * @author W.Y.J
     * @Date 2021/8/7 4:28 下午
     */
    public String longestPalindrome(String s) {
        //特判
        int length = s.length();
        if (length < 2) {
            return s;
        }
        //假设dp[i][j]表示字符串s从下标i到小标j这个子串是否是回文串
        boolean dp[][] = new boolean[length][length];
        //记录最长回文子串的开始小标，以及长度，而不是直接记录最长回文子串，这样可以避免多次截取子串浪费性能
        int maxLength = 1;//最短也是有一个字符
        int maxIndex = 0;
        char[] sArr = s.toCharArray();
        //单个字符肯定是回文串，先初始化数据
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        //因为i<j,所以只需对矩阵的右上角进行数据处理
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                //首尾字符相等
                if (sArr[i] == sArr[j]) {
                    //如果两个字符相等，且字符距离小于等于2，比如 'aa','aba'，这两种情况都可以断定是回文串
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        //如果[i][j]是回文串，那么去掉首尾[i+1][j-1]也是回文串，故两者是等价的，根据这个特性继续缩小区间
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && (j - i + 1) > maxLength) {
                    maxIndex = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(maxIndex, maxIndex + maxLength);
    }


    @Test
    public void test() {
        String s = "abc";

        System.out.println(longestPalindrome(s));
    }


    /*****************************************************中心扩散法**************************************************/


    /**
     * 这个是从从题意中从容易想到的，以每个字符为中心同时向两边扩散进行比较，算出每个字符为中心的最长回文串，取最大值
     * 比较麻烦的是：字符作为回文串的中心值是有两种形态的，
     * 如果回文串是奇数个字符，这个中心可以是任意字符，它紧邻左右两边对称即可
     * 如果回文串是偶数个字符，这个中心必须和它相邻的左边或者右边相等，因为是遍历所有字符，只考虑一种情况即可
     * AC时间要比dp方法要快
     *
     * @param s
     * @return java.lang.String
     * @author W.Y.J
     * @Date 2021/8/7 5:19 下午
     */
    public String longestPalindrome2(String s) {
        //特判
        int length = s.length();
        if (length < 2) {
            return s;
        }

        int maxLength = 1;//最短也是有一个字符
        int maxIndex = 0;

        //遍历每个元素作为中心进行扩散，首尾元素无法扩散
        for (int i = 0; i < length - 1; i++) {
            //子串是奇数的情况，左右连边的必须相等
            int[] odd = centerSpread(s, length, i - 1, i + 1);
            if (odd[1] > maxLength) {
                maxIndex = odd[0];
                maxLength = odd[1];
            }
            //子串是偶数的情况，与右边必须相等（或者左边，由于是遍历所有字符，左右情况结果一致）
            int[] even = centerSpread(s, length, i, i + 1);
            if (even[1] > maxLength) {
                maxIndex = even[0];
                maxLength = even[1];
            }
        }

        return s.substring(maxIndex, maxIndex + maxLength);
    }


    /**
     * 抽象出来根据左右下标进行扩散比较的方法
     *
     * @param s
     * @param length
     * @param left
     * @param right
     * @return int[]
     * @author W.Y.J
     * @Date 2021/8/7 5:30 下午
     */
    private int[] centerSpread(String s, int length, int left, int right) {

        while (left >= 0 && right < length) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
    }


    @Test
    public void test2() {
        String s = "bn";

        System.out.println(longestPalindrome2(s));
    }
}
