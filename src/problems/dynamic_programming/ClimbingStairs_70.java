package problems.dynamic_programming;

/* *
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
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
        opt[0]=0;
        opt[1]=1;
        opt[2]=2;
        for (int i=3;i<=n;i++){
            opt[i]=opt[i-1]+opt[i-2];
        }
        return opt[n];

    }
}
