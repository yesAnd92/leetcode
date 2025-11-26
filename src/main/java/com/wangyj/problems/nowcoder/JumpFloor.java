package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class JumpFloor {

    public int jumpFloor(int number) {
        // write code here

        //记dp[n] 表示 到n级台阶总共的跳法
        int[] dp = new int[number + 2];
        dp[1] = 1;
        dp[2] = 2;
        if (number < 3) {
            return dp[number];
        }
        for (int i = 3; i <= number; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[number];

    }


    @Test
    public void test() {
        System.out.println(jumpFloor(7));
    }
}
