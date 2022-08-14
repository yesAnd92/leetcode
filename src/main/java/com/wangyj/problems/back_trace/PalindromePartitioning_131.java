package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * 分隔回文串
 * https://leetcode.cn/problems/palindrome-partitioning/
 * @author W.Y.J
 * @Date 2022/8/14 17:35
 */
public class PalindromePartitioning_131 {


    @Test
    public void test() {
        List<List<String>> ans = partition("a");

        ans.stream().forEach(list -> {
            list.stream().forEach(System.out::println);
            System.out.println("----------");
        });
    }


    public List<List<String>> partition(String s) {

        List<List<String>> ans = new ArrayList<>();
        int length = s.length();
        //特判
        if (length == 0) {
            return ans;
        }
        Deque stack = new ArrayDeque();
        dfs(ans, s, stack, 0, length);

        return ans;
    }

    public void dfs(List<List<String>> ans, String s, Deque stack, int index, int length) {
        //递归退出条件:当索引index遍历到最后一位，说明这一趟满足条件，排列结束
        if (index == length) {
            ans.add(new ArrayList<>(stack));
        }

        for (int i = index; i < length; i++) {
            //需要判断截出来的字符串是否是回文串,不是回文串当前分隔也就无需后续操作
            if (!isHuiwen(s, index, i)) {
                continue;
            }
            stack.addLast(s.substring(index, i+1 ));
            dfs(ans, s, stack, i+1, length);
            //回退一步
            stack.removeLast();

        }
    }

    private boolean isHuiwen(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
