package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author W.Y.J
 * @Date 2021/6/5 15:04
 * https://leetcode-cn.com/problems/subsets/
 */
public class Subsets_78 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        List<Integer> visited = new ArrayList<>();
        backTrace(visited, nums,0,nums.length);
        return ans;


    }


    /**
     * 这题和全排列有两点不一样：
     * 1是回溯的范围是不一样的，全排列是要求顺序的，所以要回溯所有的情况，本题要求不考虑顺序，为避免重复，不能重复遍历
     * 2是添加到结果的条件是不一样的，全排列要求是满足每个元素都要用到，子集这个是每个都要添加
     *
     * 所以本题回溯方法增加了开始、结束两个参数，用来控制回溯的起始位置
     */
    void backTrace(List<Integer> visited, int[] nums,int start,int end ) {

        ans.add(new ArrayList<>(visited));
        if (nums.length == visited.size()) {
            return;
        }

        for (int i = start; i < end; i++) {
            //做选择
            visited.add(nums[i]);
            //进入下一次抉择
            backTrace(visited,nums,i+1,end);
            //退回上一个
            visited.remove(visited.size()-1);
        }
    }


    @Test
    public void test(){
        int[] nums = new int[]{0};
        List<List<Integer>> permute = subsets(nums);
        for (List<Integer> ints : permute) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
