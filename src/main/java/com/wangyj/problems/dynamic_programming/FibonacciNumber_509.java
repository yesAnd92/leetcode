package com.wangyj.problems.dynamic_programming;

import org.junit.Test;

/**
 * 斐波那契数
 * https://leetcode-cn.com/problems/fibonacci-number/
 * @author W.Y.J
 * @Date 2022/4/13 10:56
 */
public class FibonacciNumber_509 {


    /**
     *F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     */
    public int fib(int n) {
        if (n==0)
            return 0;

        int dp[]=new int [n+1];
        dp[1]=1;
        for (int i = 2; i <=n ; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }

        return dp[n];
    }

    @Test
    public void test(){
        System.out.println(fib(3));
    }

}
