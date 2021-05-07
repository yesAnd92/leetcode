package problems.math;

import java.util.Stack;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1:
 * Input: 123, -123, 210
 * Output: 321, -321, 12
 *
 * Assume we are dealing with an environment which could only store integers within
 * the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem,
 * assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger_7 {


    public static void main(String[] args) {

        System.out.println(reverse(2108888889));
    }

    public static int reverse(int x) {

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
    public static int reverseAbs(int x) {
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
}
