package problems.dynamic_programming;

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
public class HouseRobber198 {

    public static void main(String[] args) {

        int[] nums = {};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {

//        return recOpt(nums.length-1,nums);

        return dpOpt(nums);
    }


    /**
     * 递归实现 ，leetcode执行会超时 java.lang.StackOverflowError
     */
    private static int recOpt(int i, int[] nums) {

        //i==0和i==1为递归的出口
        if (i == 0) {
            return nums[0];
        } else if (i == 1) {
            return Math.max(nums[0], nums[1]);
        } else {
            return Math.max(recOpt(i - 1, nums), nums[i] + recOpt(i - 2, nums));
        }
    }


    /* *
     * 动态规划
     * @author wangyj
     * @date 2019/3/26 16:51
     * @param [nums]
     * @return int
     */
    private static int dpOpt(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        if (length == 0) {
            return 0;
        } else if (length == 1) {
            //只有1个元素
            return result[0] = nums[0];
        } else if (length == 2) {
            //只有2个元素
            result[0] = nums[0]; //首先计算出起始值
            result[1] = Math.max(nums[0], nums[1]);
            return result[1];
        }
        result[0] = nums[0]; //首先计算出起始值
        result[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int a = nums[i] + result[i - 2];
            int b = result[i - 1];
            result[i] = Math.max(a, b);
        }
        return result[length - 1];
    }
}
