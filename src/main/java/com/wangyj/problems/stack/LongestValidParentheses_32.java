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
     *
     * 对于这种括号匹配问题，一般都是使用栈
     * 我们先找到所有可以匹配的索引号，然后找出最长连续数列！
     * 例如：s = )(()())，我们用栈可以找到，
     * 位置 2 和位置 3 匹配，
     * 位置 4 和位置 5 匹配，
     * 位置 1 和位置 6 匹配，
     * 这个数组为：2,3,4,5,1,6 这是通过栈找到的，我们按递增排序！1,2,3,4,5,6
     * 找出该数组的最长连续数列的长度就是最长有效括号长度！
     * 为了减少最后再遍历一次，可以将每个元素的下标入栈，计算最大距离
     */
    public int stack(String s){

        int length = s.length();
        //特判
        if(length==0)return 0;
        Stack<Integer> stack  = new Stack<>();
        //首先在栈中添加-1，来规避掉下表0的边界元素
        stack.push(-1);
        int result =0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c=='('){
                stack.push(i);
            }else {
                // 如果是’)‘，弹出栈顶
                stack.pop();
                //每次弹出栈顶时都要判断栈内是否还有元素
                if (stack.empty()){
                    //没有元素了，说明括号都是成对出现，将当前下标入栈，作为起始标记
                    stack.push(i);
                }else {
                    //有元素，就计算当前位置与成符合字串的前一个位置距离，得到最大值
                    result =Math.max(result,i-stack.peek());
                }
            }
        }
        return result;
    }

    @Test
    public void testStack(){
        System.out.println(stack("(()"));//2
        System.out.println(stack(")()())"));//4
        System.out.println(stack("()((()())"));//6
        System.out.println(stack("()(()())"));//8
    }
}
