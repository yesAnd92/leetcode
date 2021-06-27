package com.wangyj.problems.stack;


import org.junit.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 * @author W.Y.J
 * @Date 2021/6/19 11:15 下午
 */
public class LongestValidParentheses_32 {


    public int longestValidParentheses(String s) {

        return 0;
    }


    /*********************************************burstForce*******************************************/
    /**
     * 先写一个暴力,肯定会超时
     *
     * @param s
     * @return
     */
    public int burstForce(String s) {
        int length = s.length();
        //特判
        if (length == 0) return 0;

        //奇数个肯定不满足条件，直接从result-1开始
        int result = length % 2 == 0 ? length : length - 1;
        while (result > 0) {
            //依次长度每次减一进行验证子串是否满足条件，满足条件就直接返回长度
            for (int i = 0; i + result <=length; i++) {
                if (subStringIsValid(s, i, i + result-1)) {
                    return result;
                }
            }
            result--;
        }

        return result;
    }

    /**
     * 判断一个子串是否是有效括号
     *
     * @param s
     * @param start
     * @param end
     * @return boolean
     * @author W.Y.J
     * @Date 2021/6/27 11:24 下午
     */
    private boolean subStringIsValid(String s, int start, int end) {
        Stack<Character> stack = new Stack<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }



    @Test
    public void testBurstForce(){
        System.out.println(burstForce("(()"));//2
        System.out.println(burstForce(")()())"));//4
        System.out.println(burstForce("()((()())"));//6
        System.out.println(burstForce("()(()())"));//6
    }


    /***********************************************stack***************************************/

    /**
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-powcai/
     * @author W.Y.J
     * @Date 2021/6/27 11:45 下午
     * @param s
     * @return int
     */
    public int stack(String s){
        return 0;
    }
}
