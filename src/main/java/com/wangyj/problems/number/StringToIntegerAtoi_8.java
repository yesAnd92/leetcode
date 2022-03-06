package com.wangyj.problems.number;

import org.junit.Test;

public class StringToIntegerAtoi_8 {

    public int myAtoi(String s) {
        int length = s.length(), flag = 1, idx = 0;
        long result = 0;
        while (idx < length && s.charAt(idx) == ' ') {
            idx++;
        }

        //排除全是‘ ’情况
        if (idx == length) return 0;

        //除去空格后的第一个或许是‘+’，‘-’，‘num’
        char first = s.charAt(idx);
        if (first == '-') {
            flag = -1;//标记为负数
        } else if (first == '+') {
            flag = 1;
        } else if (first >= '0' && first <= '9') {
            result = first - '0';
        } else {
            return 0;
        }

        idx++;//正式开始接受字符串
        for (; idx < length; idx++) {
            //判断是否已经溢出int
            if (result > Integer.MAX_VALUE + 1L)
                return result(flag, result);
            char num = s.charAt(idx);
            if (num < '0' || num > '9') {
                return result(flag, result);
            } else {
                result = result * 10 + num - '0';
            }
        }
        return result(flag, result);
    }

    private int result(int flag, long result) {
        if (flag == 1) {
            return result >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
        } else {
            result = -result;
            return result <= Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) result;
        }
    }

    @Test
    public void test() {

        /**
         * test case
         * +-12
         * 3.123
         */
        System.out.println(myAtoi("+3.14sss"));
        System.out.println(myAtoi("+-3.14sss"));
        System.out.println(myAtoi("www3w3rwe"));
        System.out.println(myAtoi("123uuu"));
        System.out.println(myAtoi("--123uuu"));
        System.out.println(myAtoi("-123uuu"));
        System.out.println(myAtoi(" -42"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("00000-42a1234"));
        System.out.println(myAtoi(" "));
        System.out.println(myAtoi("-2147483648"));
        System.out.println(myAtoi("9223372036854775808"));
    }
}
