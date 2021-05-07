package problems.dynamic_programming;

import java.util.Arrays;

/* *
 * https://leetcode.com/problems/burst-balloons/
 * 问题分析：https://www.youtube.com/watch?v=z3hu2Be92UA
 * 题目详情见链接
 * @author wangyj
 * @date 2019/4/10
 */
public class BurstBalloons_312 {

    public static void main(String[] args) {

        int[] nums= new int[]{};
        System.out.println(maxCoins(nums));
//        System.out.println(maxCoins2(nums));
    }

    public static int maxCoins(int[] nums) {

        int length = nums.length;

        if (length==0)return 0;
        //opt[i][j]表示从i气球到j气球中能够获取的最大收益
        int[][] opt = new int[length+1][length+1];
        for (int n =0; n < length; n++) {
            //外层循环作用是先计算出最小的子问题，再计算包含子问题的问题，
            // 即先计算一个球、然后两个气球...
            for (int i =0;  i< length-n; i++) {
                int j=i+n;
                for (int k = i; k <=j; k++) {
                    /**
                     * 假设只保留k位置的气球不打破，i到k-1和k+1到j的气球打破，
                     * 则最后打破k的总收益为opt[i][k+1]+opt[k+1][j]+nums[i-1]*nums[k]*nums[j+1];
                     * 状态转移方程为 opt[i][j]=max{opt[i][j],opt[i][k+1]+opt[k+1][j]+nums[i-1]*nums[k]*nums[j+1]}
                     */
                    int left=i-1<0?1:nums[i-1];  //当前位置左端的元素，k=-1 置为1
                    int right=j+1==length?1:nums[j+1];//当前位置左端的元素，k=length 置为1
                    opt[i][j]=Math.max(opt[i][j],getOpt(opt,i,k-1)+getOpt(opt,k+1,j)+left*nums[k]*right);
                    System.out.println("opt["+i+"]["+j+"]="+opt[i][j]);
                }
            }
        }
        return opt[0][length-1];
    }

    private static int getOpt(int[][] opt ,int start,int end ){
        if (start>end){
            return 0;
        }
        return opt[start][end];
    }
}
