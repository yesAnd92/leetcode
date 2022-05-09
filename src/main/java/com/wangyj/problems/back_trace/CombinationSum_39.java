package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class CombinationSum_39 {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        //这里用双端队列的目的是可以直接对队尾进行添加和删除，便于回溯
        Deque<Integer> trace = new ArrayDeque<>();
        dfs(trace, candidates, 0, 0,target);
        return ans;
    }

    /**
     * 精彩的解答 https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * @param trace
     * @param candidates
     * @param begin   标记当前层从哪个元素开始遍历：为了去重，跳过前边已经遍历过的元素
     * @param sum   trace中元素的和   注意这里是使用了累加和与target作比较，为了减少每次计算trace元素和，用一个变量
     *              保存sum的和。其实更好的方式trace中每记录一个值，target对应减少，最终和0比较。这样可以只用一个target变量
     * @param target
     */
    private void dfs(Deque<Integer> trace, int[] candidates,int begin, int sum, int target) {

        //如果和已经大于target，这个分支就不用再往下继续了，再累加sum只会更大
        if (sum > target)
            return;
        //递归的结束条件 找到符合条件的trace，记录到结果中
        if (sum == target) {
            ans.add(new ArrayList<>(trace));
            return;
        }

        //从begin开始遍历，防止重复
        for (int i = begin; i < candidates.length; i++) {
            trace.addLast(candidates[i]);
            dfs(trace, candidates,i, sum+candidates[i], target);
            trace.removeLast();
        }
    }

    @Test
    public void test() {
//        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[]{2, 3,5}, 8));
//        System.out.println(combinationSum(new int[]{2}, 7));
    }
}
