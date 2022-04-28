package com.wangyj.problems.recursion;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础计算器2
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII_227 {

    public int calculate(String s) {

        //设置优先级
        Map<Character, Integer> priorityMap = new HashMap<>(8);
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);

        //存放数字,这里用long是为了规避中间结果可能溢出的问题
        Deque<Long> numStack = new ArrayDeque<>();

        //粗放操作数
        Deque<Character> optStack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == ' ') {
                //跳过空格
                continue;
            } else if (Character.isDigit(c)) {
                //数字
                long num = 0;
                int j = i ;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    num = num * 10 + s.charAt(j) - '0';
                    j++;
                }
                i = j-1;//多加了一次，补偿回来
                numStack.push(num);

            } else {
                //操作符
                while (!optStack.isEmpty() && priorityMap.get(optStack.peek()) >= priorityMap.get(c)) {
                    //如果栈中的优先级比当前的高，先计算这里边的值
                    cal(numStack, optStack);
                }
                optStack.push(c);
            }
        }

        while (numStack.size()!=1){
            cal(numStack,optStack);
        }
        return numStack.pop().intValue();
    }

    private void cal(Deque<Long> numStack, Deque<Character> optStack) {

        long tmp;
        long b =numStack.poll();//后边的数
        long a=numStack.poll();//前边的数
        Character opt = optStack.poll();
        if (opt=='+'){
            tmp=a+b;
        }else if(opt=='-'){
            tmp=a-b;
        }else if (opt=='*'){
            tmp=a*b;
        }else {
            tmp=a/b;
        }
        //计算后的结果在塞回去
        numStack.push(tmp);
    }


    @Test
    public void test(){
//        String s = "-1+2*22/3-2";
//        String s = " 3/2 ";
        String s = "1*2-3/4+5*6-7*8+9/10";
        System.out.println(calculate(s));
    }
}
