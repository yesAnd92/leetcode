package com.wangyj.problems.math;

import org.junit.Test;

import java.util.Stack;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1:
 * Input: 123, -123, 210
 * Output: 321, -321, 12
 * <p>
 * Assume we are dealing with an environment which could only store integers within
 * the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem,
 * assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger_7 {


    public int reverse(int x) {

        if (x == 0) {
            return 0;
        } else if (x > 0) {
            return reverseAbs(x);
        } else {
            return -reverseAbs(x);
        }
    }

    /*
     * 将数字转换成字符串，配合栈的先进后出的特点进行翻转
     */
    public int reverseAbs(int x) {
        Stack<String> stack = new Stack<String>();
        //为了操作方便，这里不考虑正负，全部按照正数处理
        String xStr = String.valueOf(Math.abs(x));
        for (int i = 1; i <= xStr.length(); i++) {
            //压栈
            stack.push(xStr.substring(i - 1, i));
        }
        String reverStr = "";
        //是否开头“0”的标志
        boolean isZeroStart = true;
        while (!stack.empty()) {
            //出栈
            String s = stack.pop();
            //去除前边的无意义“0”
            if ("0".equals(s) && isZeroStart) {
                continue;
            }
            isZeroStart = false;
            reverStr += s;
        }

        //考虑最后一位数字过大溢出问题
        long re = Long.valueOf(reverStr);
        return re > Integer.MAX_VALUE ? 0 : Integer.valueOf(reverStr);
    }



    /**
     * 对上边的堆栈优化一下，即不用存放每个数字的大小,每次都计算好结果值
     * @author W.Y.J
     * @Date 2021/5/29 16:21
     */
    public  int reverse2(int x) {
        //特判
        if (x==-2147483648){
            return 0;
        }
        int isNegative=1;
        if (x<0){
            isNegative=-1;
            x=-x;
        }

        Long ans=0L;

        while (x!=0){
            ans=ans*10+x%10;
            x=x/10;
        }
        return ans>Integer.MAX_VALUE?0:(ans.intValue()*isNegative);
    }


    @Test
    public void test() {

        System.out.println(reverse2(2108888889));
    }
}
