package com.wangyj.problems.math;


import org.junit.Test;

/**
 * 数字转换成十六进制
 * https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/
 */
public class ConvertNumberToHex_405 {


    //通过下标直接获取对应的字符串，也可以使用'0'+偏移量计算对应的字符
    char[] hexChar = new char[]{'0','1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public String toHex(int num) {
        if (num==0)
            return "0";
        long longNum=num;
        if(num < 0){
            longNum = (long)(Math.pow(2,32)+longNum);
        }

        StringBuilder sb = new StringBuilder();
        while (longNum > 0) {
            sb.append(hexChar[(int)(longNum % 16)]);
            longNum /= 16;
        }
        return sb.reverse().toString();
    }


    @Test
    public void test() {
        System.out.println((long)Math.pow(2, 32));
//        System.out.println(toHex(12));
        System.out.println(toHex(-1));

    }
}
