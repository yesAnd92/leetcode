package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.Arrays;

public class CoinChange {

    public int minMoney(int[] arr, int aim) {
        // write code here

        if (arr.length == 0) {
            return -1;
        }

        if (aim == 0) {
            return 0;
        }

        Arrays.sort(arr);

        //dp[i]为凑齐i金额，所需的最小币种
        int[] dp = new int[aim + 1];


        //初始化dp为无法凑齐
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= aim; i++) {
            for (int coin : arr) {
                if (coin > i) {
                    //比丢给面值小，由于升序，后续无需遍历
                    break;
                }

                //dp[i - coin]的值有可能为-1，导致dp[i - coin] + 1=0，影响递归方程
                //其实没必要提前写入-1，这样会引入麻烦，看第二种实现
                if (dp[i - coin] != -1) {
                    if (dp[i] == -1 || dp[i - coin] + 1 < dp[i]) {
                        dp[i] = dp[i - coin] + 1;
                    }
                }

            }

        }
        return dp[aim];

    }


    /**
     * 这种从工程实现角度更加简便，核心思路是一样的
     * @param arr
     * @param aim
     * @return
     */
    public int minMoney2 (int[] arr, int aim) {
        //小于1的都返回0
        if(aim < 1)
            return 0;
        int[] dp = new int[aim + 1];
        //dp[i]表示凑齐i元最少需要多少货币数
        //因为面值最小是1，dp[aim]的最大值是aim，设置为aim+1方便后续校验
        Arrays.fill(dp, aim + 1);
        dp[0] = 0;
        //遍历1-aim元
        for(int i = 1; i <= aim; i++){
            //每种面值的货币都要枚举
            for(int j = 0; j < arr.length; j++){
                //如果面值不超过要凑的钱才能用
                if(arr[j] <= i)
                    //维护最小值
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
            }
        }
        //如果最终答案大于aim代表无解
        return dp[aim] > aim ? -1 : dp[aim];
    }

    @Test
    public void test() {
        int[] nums = {3,2,5};
        int i = minMoney(nums, 20);
        System.out.println(i);
    }
}
