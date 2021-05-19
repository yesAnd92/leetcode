package com.wangyj.problems.double_pointer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author W.Y.J
 * @Date 2021/5/19 22:18
 * https://leetcode-cn.com/problems/3sum-closest/
 */
public class ThreeSumClosest_16 {


    public int threeSumClosest(int[] nums, int target) {

        /**
         * 怎么才能找到三个数之和最接近目标值呢？
         * 最接近的值一定是出现在不超过target的最大值和不小于target的最小值这两者其一
         * 为了减少遍历我们使用双指针遍历，需要对数组进行排序
         */
        Arrays.sort(nums);
        int length = nums.length;
        int min=nums[length-1]+nums[length-2]+nums[length-3],max=nums[0]+nums[1]+nums[2];
        for (int i = 0; i <length ; i++) {
            int left=i+1;
            int right=length-1;
            while (left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum <target){
                    //三个数和比目标数小，left需要右移
                    left++;
                    max=target-max>target-sum?sum:max;
                }else if (sum>target){
                    //三个数和比目标数小，left需要右移
                    right--;
                    min=min-target>sum-target?sum:min;
                }else {
                    return sum;
                }
            }
        }
        return target-max>min-target?min:max;
    }


    /**
     * 算法思想同上，可以在写法上进行优化
     */
    public int threeSumClosest2(int[] nums, int target) {

        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        //{1,2,4,8,16,32,64,128}  82  -->82
        //{0,1,2} 0 ->3
        //{1,1,1,0} -100 ->2
        //{-1,2,1,-4} 1 ->2
//        int nums[] = new int[]{1,2,4,8,16,32,64,128};
//        int result = threeSumClosest(nums, 82);

        int nums[] = new int[]{1,1,1,0};
        int result = threeSumClosest(nums, -100);

//        int nums[] = new int[]{-1,2,1,-4};
//        int result = threeSumClosest(nums, 1);

//        int nums[] = new int[]{0,1,2};
//        int result = threeSumClosest(nums, 0);
        System.out.println(result);
    }
}
