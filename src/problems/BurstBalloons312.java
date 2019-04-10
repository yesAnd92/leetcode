package problems;

import java.util.Arrays;

/* *
 * https://leetcode.com/problems/burst-balloons/
 * 题目详情见链接
 * @author wangyj
 * @date 2019/4/10
 */
public class BurstBalloons312 {

    public static void main(String[] args) {

        int[] nums= new int[]{3,1,5,8};
        System.out.println(maxCoins(nums));
        System.out.println(maxCoins2(nums));
    }

    public static int maxCoins(int[] nums) {

        int length = nums.length;

        //opt[i][j]表示从i气球到j气球中能够获取的最大收益
        int[][] opt = new int[length+1][length+1];
        for (int count =0; count < length; count++) {
            for (int i =0;  i+count< length; i++) {
                int j=i+count;
                for (int k = i; k <=j; k++) {
                    //当前位置左边的元素，k=-1 置为1
                    int left=k-1<0?1:nums[k-1];
                    //当前位置左边的元素，k=length 置为1
                    int right=k+1==length?1:nums[k+1];
                    opt[i][j]=Math.max(opt[i][j],opt[i][k]+opt[k+1][j]+left*nums[k]*right);
                    System.out.println("opt["+i+"]["+j+"]="+opt[i][j]);
                }
            }
//            System.out.println ( Arrays.toString (opt[i]));
        }
//        for (int i=0;i<3;i++)
        return opt[0][length-1];
    }


    public static int maxCoins2(int[] nums) {
        int n = nums.length + 2;
        int []a = new int[n];
        a[0] = 1;
        a[n - 1] = 1;
        for (int i = 0; i < n - 2; i ++) {
            a[i + 1] = nums[i];
        }
        int [][]dp = new int[n][n];
        for (int l = 2; l < n; l ++) {
            for (int i = 0; i + l < n; i ++) {
                int j = i + l;
                for (int k = i + 1; k < j; k ++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + a[i] * a[j] * a[k]);
                    System.out.println("dp["+i+"]["+j+"]="+dp[i][j]);
                }
            }
        }
        for (int l=0;l<n;l++)
            System.out.println ( Arrays.toString (dp[l]));
        return dp[0][n - 1];
    }
}
