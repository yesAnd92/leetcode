package com.wangyj.problems.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author W.Y.J
 * @Date 2021/6/19 8:53 下午
 */
public class ValidParentheses_20 {


    public boolean isValid(String s) {
        String stack = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack += c;
            } else {
                char r = getLastChar(stack);
                if (r == ' ') return false;
                if (r == '(' && c != ')') return false;
                if (r == '[' && c != ']') return false;
                if (r == '{' && c != '}') return false;
                stack = stack.substring(0, stack.length() - 1);
            }
        }
        return stack.isEmpty();

    }

    private char getLastChar(String stack) {
        if (stack.isEmpty()) return ' ';
        return stack.charAt(stack.length() - 1);
    }


    /**
     * 这道题可以在上边的代码进行优化，提高性能
     * 1、根据题意，如果s是奇数个 肯定是false
     * 2、在比较括号左右是否相等时，判断较多，可以用map优化下map->(k,v),或者放入时就反向放入
     */
    public boolean isValid1(String s) {
        int length = s.length();
        if (length % 2 != 0) return false;
        Stack<Character> stack =new Stack<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '('  ) {
                //直接放入相反的括号，便于后边比较
                stack.push(')');
            } else if (c=='['){
                stack.push(']');
            }else if (c == '{'){
                stack.push('}');
            }else if(stack.isEmpty()||stack.pop()!=c){
                return false;
            }
        }
        return stack.isEmpty();

    }


    @Test
    public void test() {
        String s = "()[]{}";
        System.out.println(isValid(s));
        String s1 = "(]";
        System.out.println(isValid(s1));
        String s2 = "([)]";
        System.out.println(isValid(s2));
        String s3 = "(";
        System.out.println(isValid(s3));
    }
}
