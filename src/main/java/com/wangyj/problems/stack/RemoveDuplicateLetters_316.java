package com.wangyj.problems.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 *
 * @author W.Y.J
 * @Date 2021/6/30 10:14 下午
 */
public class RemoveDuplicateLetters_316 {


    /**
     * 解题方法 https://leetcode-cn.com/problems/remove-duplicate-letters/solution/you-qian-ru-shen-dan-diao-zhan-si-lu-qu-chu-zhong-/
     * @author W.Y.J
     * @Date 2021/6/30 10:41 下午
     * @param s
     * @return java.lang.String
     */
    public String removeDuplicateLetters(String s) {

        Stack<Character> stack = new Stack<>();
        //因为输入为 ASCII 字符，大小 256 够用了
        //统计每个字母出现的次数,为了帮助判断当前字符是否后续还会出现
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        //已经入栈的字符，做好标识
        boolean[] inStack = new boolean[256];

        for (char c : s.toCharArray()) {
            //如果已经在栈中，直接跳过
            count[c]--;
            if (inStack[c]) continue;

            while (!stack.isEmpty() && stack.peek() > c) {
                //如果当前字符比前边的已有元素小，替换掉（为了保证字典序最小）
                if (count[stack.peek()]==0){
                    //但是如果栈顶字符后边没有了，就不能换了
                    break;
                }
                //如果被替换了，将已经入栈的标记清除，后续还可以入栈
                inStack[stack.pop()]=false;
            }
            stack.push(c);
            //标记为c已经使用过
            inStack[c]=true;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        //出栈的顺序和结果相反，取反
        return sb.reverse().toString();
    }


    @Test
    public void test(){
        String s = "bcabc";
//        System.out.println(removeDuplicateLetters(s));
        String s1 = "bbcaac";
        System.out.println(removeDuplicateLetters(s1));
    }
}
