package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        // write code here

        //dp[i]表示，上到第i台阶的最小花费
        int length = cost.length;
        int[] dp = new int[length + 2];

        if (length == 1) {
            return 0;
        }

        dp[1] = 0;
        dp[2] = Math.min(cost[0], cost[1]);
        for (int i = 3; i <= length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[length];
    }


    @Test
    public void test(){
//        int[] cost=new int[]{2,5,20};
        int[] cost=new int[]{1,100,1,1,1,90,1,1,80,1};
        System.out.println(minCostClimbingStairs(cost));
    }
}
