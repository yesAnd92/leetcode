package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // write code here
        //定义dp[i][j]表示到第m行n列共有多少种到达方案
        int[][] dp = new int[m + 1][n + 1];
        //初始化边界值，边界值都只有一种路径
        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= n; j++) {
            dp[1][j] = 1;
        }

        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    @Test
    public void test(){
        System.out.println(uniquePaths(1,2));
    }
}
