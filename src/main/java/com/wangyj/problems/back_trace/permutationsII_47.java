package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class permutationsII_47 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

        LinkedList<Integer> trace = new LinkedList<>();

        Arrays.sort(nums);

        int length = nums.length;
        boolean[] used = new boolean[length];
        dfs(nums, trace, used, length);
        return ans;
    }


    public void dfs(int[] nums, LinkedList trace, boolean[] used, int length) {

        //退出条件
        if (length == trace.size()) {
            //这里添加结果时，需要new一个新数组，否则ans集合里添加的都是同一个trace
            ans.add(new ArrayList<>(trace));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (used[i])
                continue;
            //剪枝
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }

            used[i] = true;
            trace.add(nums[i]);

            dfs(nums,trace,used,length);

            used[i] = false;
            trace.removeLast();

        }

    }


    @Test
    public void test(){
        System.out.println(permuteUnique(new int[]{1, 2, 3}));
    }
}
