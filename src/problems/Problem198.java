package problems;

/* *
 * https://leetcode.com/problems/house-robber/
 *Given a list of non-negative integers representing the amount of money of each house,
 *determine the maximum amount of money you can rob tonight without alerting the police.
 *  Example 2:
 *  Input: [2,7,9,3,1]
 *  Output: 12
 *  Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *  Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * @author wangyj
 * @date 2019/3/25 17:06
 */
public class Problem198 {

    public static void main(String[] args) {

        int[] nums = {2,7,8,6,1,9,11,5};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {

        return recOpt(nums.length-1,nums);

    }


    /**
     * 递归实现
     */
    private static int recOpt(int i ,int[] nums){

        //i==0和i==1为递归的出口
        if(i==0){
            return nums[0];
        }else if (i==1){
            return Math.max(nums[0],nums[1]);
        }else {
            return Math.max(recOpt(i-1,nums),nums[i]+recOpt(i-2,nums));
        }
    }
}
