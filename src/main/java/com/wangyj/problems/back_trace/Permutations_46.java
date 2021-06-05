package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @author W.Y.J
 * @Date 2021/6/2 16:27
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permutations_46 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {


        //记录已经使用过的数字
        List<Integer> visited = new ArrayList<>();
        backTrace(visited, nums);
        return ans;
    }

    /**
     * 每次回溯的结束条件就是，visited中使用过的数字全部出现在track中
     * 选择列表 nums中还没有被使用过的数字
     * 路径  已经使用过的数字就是已经走过的路径
     */
    private void backTrace(List<Integer> visited, int[] nums) {
        if (visited.size() == nums.length) {
            // ans.add(visited);不能用这种方法添加，visit的值后续会被覆盖，需要新new
            ans.add(new ArrayList<>(visited));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // TODO: 2021/6/2 这块可以优化下，减少时间复杂度
            if (visited.contains(nums[i])){
                continue;
            }
            //做选择
            visited.add(nums[i]);
            //进入到下一行做抉择
            backTrace(visited,nums);
            //撤销，返回上一层重新选择
            visited.remove(visited.size()-1);
        }
    }

    @Test
    public void test(){
//        int[] nums = new int[]{1,2,3};
        int[] nums = new int[]{1,3,4};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> ints : permute) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
