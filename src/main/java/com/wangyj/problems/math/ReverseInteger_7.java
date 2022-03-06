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
     *
     * @author W.Y.J
     * @Date 2021/5/29 16:21
     */
    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }


    public int reverse3(int x) {
        long result = 0L;
        //注意这里要考虑负数问题，不能写成x>0
        while (x != 0) {
            //求末尾一个数
            int mod = x % 10;
            //不用记录先求出的数字顺序，字节累加到最后的结果
            result = result * 10 + mod;
            //整除10，舍去最后一位
            x /= 10;
            if (result>Integer.MAX_VALUE||result<Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int) result;
    }

    @Test
    public void test() {

        System.out.println(reverse2(2108888889));
    }
}
