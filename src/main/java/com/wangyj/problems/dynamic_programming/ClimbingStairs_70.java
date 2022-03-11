package com.wangyj.problems.dynamic_programming;

/* *
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * @author wangyj
 * @date 2019/4/3
 */
public class ClimbingStairs_70 {

    public static void main(String[] args) {

        System.out.println(climbStairs(4));
    }

    public static int climbStairs(int n) {
        /**
         * 设opt[i]为到达第i层楼梯的方式数
         * 到达第i层有两种方式：
         * 1.从i-1层走一步上去
         * 2.从i-2层走两步上去
         * 故状态方程应该为：opt[i]=opt[i-1]+opt[i-2]
         */
        int[] opt=new int[n+2];
        //初始化边界值
        opt[1]=1;
        opt[2]=2;
        if (n<=2){
            return opt[n];
        }
        for (int i=3;i<=n;i++){
            opt[i]=opt[i-1]+opt[i-2];
        }
        return opt[n];
    }
}
