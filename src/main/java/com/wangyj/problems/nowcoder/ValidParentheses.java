package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayDeque;

public class ValidParentheses {

    public boolean isValid(String s) {
        // write code her

        if (s == null)
            return false;

        if (s.length() == 0)
            return true;

        ArrayDeque<Character> stack = new ArrayDeque();

        for (char c : s.toCharArray()) {
            //左括号都进栈，为了出栈好比较，直接存相反的字符
            if (c == '{') {
                stack.offerLast('}');
            } else if (c == '(') {
                stack.offerLast(')');
            } else if (c == '[') {
                stack.offerLast(']');
            } else if (stack.isEmpty()||stack.pollLast() != c) {
                //右括号，要出栈比较是否相等
                return false;
            }

        }

        //遍历完，栈中还有元素，说明无序
        if (!stack.isEmpty())
            return false;
        return true;
    }


    @Test
    public void test(){
        String s1="[";
        System.out.println(isValid(s1));

        String s2="[]";
        System.out.println(isValid(s2));

        String s3="[{}]";
        System.out.println(isValid(s3));


        String s4="[{}](())";
        System.out.println(isValid(s4));

        String s5="]";
        System.out.println(isValid(s5));

    }
}
