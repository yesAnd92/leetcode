package problems;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * Example:
 *  Input: [-2,1,-3,4,-1,2,1,-5,4],
 *  Output: 6
 *  Explanation: [4,-1,2,1] has the largest sum = 6
 */
public class MaximumSubarray53 {


    public static void main(String[] args) {
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = new int[]{1,2};
        System.out.println(maxSubArray(nums));
    }


    /**
     * 三种情况：
     * 1. 最长子串中包含第个i元素，且仅有第i个元素时和最大
     * 2. 最长子串中包含第个i元素，且第i个元素是子串的右边界
     * 3. 最长子串中不包含第一个元素，则转化为i-1个元素中寻找子串和最大的问题
     *
     * 设定
     * bound[i]表示以第i个元素为结尾的i的子数组最大和
     * opt[i]表示i个元素中最大子数组和
     * 则状态方程：
     * bound[i]=max{bound[i-1]+nums[i],nums[i]}
     * opt[i]=max{opt[i-1],bound[i]}
     */
    public static int maxSubArray(int[] nums) {

        //bound[i]表示以第i个元素为结尾的i的子数组最大和
        int[] bound = new int[nums.length];

        //opt[i]表示i个元素中最大子数组和
        int[] opt = new int[nums.length];


        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];

        //元素个数大于2时初始化端点数据
        bound[0] =opt[0]=nums[0];
        bound[1]=Math.max(bound[0] + nums[1], nums[1]);
        opt[1]=Math.max(bound[1],opt[0]);
        for (int i = 2; i < nums.length; i++) {
            int a = Math.max(bound[i - 1] + nums[i], nums[i]);
            int b = opt[i - 1];
            bound[i]=a;
            opt[i] = a > b ? a : b;
        }

        return opt[nums.length - 1];
    }
}
