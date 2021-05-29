package com.wangyj.problems.string;

import org.junit.Test;

public class StringToIntegerAtoi_8 {

    public int myAtoi(String s) {
        int length = s.length();
        Long ans = 0L;
        int negative = 1;//处理正负数
        int i = 0;

        while (i < length &&' ' == s.charAt(i)) {
            i++;
        }
        int start = i;//去空格后的第一个
        for (; i < length; i++) {

            char c = s.charAt(i);
            if (i == start && c == '-') {
                //第一个数为负号时
                negative = -1;
            } else if (i == start && c == '+') {
                negative=1;
            } else if (Character.isDigit(c)) {
                ans = 10 * ans + c - '0';
                if (ans>2147483648L){
                    break;
                }
            } else {
                break;
            }
        }
        return result(ans, negative);
    }

    private int result(Long l, int negative) {
        if (negative < 0 && -l <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (l > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return l.intValue() * negative;
    }

    @Test
    public void test() {

        /**
         * test case
         * +-12
         * 3.123
         */
//        System.out.println(myAtoi("+3.14sss"));
//        System.out.println(myAtoi("+-3.14sss"));
//        System.out.println(myAtoi("www3w3rwe"));
//        System.out.println(myAtoi("123uuu"));
//        System.out.println(myAtoi("--123uuu"));
//        System.out.println(myAtoi("-123uuu"));
//        System.out.println(myAtoi(" -42"));
//        System.out.println(myAtoi("-91283472332"));
//        System.out.println(myAtoi("00000-42a1234"));
//        System.out.println(myAtoi(" "));
//        System.out.println(myAtoi("-2147483648"));
        System.out.println(myAtoi("9223372036854775808"));
    }
}
