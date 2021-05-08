package com.wangyj.problems.hash_table;


import java.util.Hashtable;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * @author W.Y.J
 * @Date 2021/5/7 17:17
 */

public class TwoSum_1 {


    /**
     * 暴力 O(n^2)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                //保证i和j不能重复使用
                if (nums[i]+nums[j]==target&&i<j){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }


    /**
     * one-pass hashtable
     *
     * 假设值ele是数组中符合要求的第一个，则另一个值必然是surplus=target-ele
     * 当遍历到ele元素时，surplus必然不在map中（因为还没遍历到），但是
     * 我们可以把所有遍历过的元素都放到map中，当遍历到surplus元素时，ele必然在map中，取出下表即可
     * @param nums
     * @param target
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new Hashtable<>();
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            int surplus = target - ele;
            //判断当前元素的另一半是否在map中，是则找到结果
            if (map.containsKey(surplus)){
                //注意map中存放的元素，才是左边的元素
                return new int[]{map.get(surplus), i};
            }
            //当前元素不符合条件，则将当前数据放入到map中，数值最为key，下标作为value
            map.put(ele,i);
        }
        return null;
    }
}
