package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class GenerateParentheses_22 {

    private List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        //dfs("", 0, 0, n);
        return ans;
    }


    /**
     * 回溯
     * @param trace 遍历过的路径
     * @param leftCount  左括号的个数
     * @param rightCount  右括号的个数
     * @param n  给定的括号的对数
     */
    private void dfs(String trace, int leftCount, int rightCount, int n) {

        //退出条件，括号的个数等于给定括号对数的2倍
        if (trace.length() == 2 * n) {
            ans.add(trace);
            return;
        }


        if(leftCount>=n){
            //左括号的个数已经大于等于最多的左括号数了，下一个括号只能是右括号
            dfs(trace + ')', leftCount, rightCount+1, n);
        }else if (rightCount < leftCount){
            //如果左括号的个数小于N，并且右括号的个数少于左括号个数时，一下个括号左或者右括号都可以
            dfs(trace + '(', leftCount+1, rightCount, n);
            dfs(trace + ')', leftCount, rightCount+1, n);
        }else {
            //如果左括号的个数小于N，并且右括号和左括号相等时，下一个括号只能是左括号
            dfs(trace + '(', leftCount+1, rightCount, n);
        }

    }


    @Test
    public void test() {
        System.out.println(generateParenthesis(3));
    }

}
