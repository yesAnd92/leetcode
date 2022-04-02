package com.wangyj.problems.math;

import org.junit.Test;


/**
 * https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475
 * 牛客NC1--大数加法
 *
 */
public class BigNumSum_NC1 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        // write code here

        if (s.length() + t.length() == 0) {
            return "";
        }

        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;

        //进位
        int add = 0;

        //大量字符串拼接，不要用String，性能比较低
        StringBuilder ans = new StringBuilder();
        //这里注意，如果进位add不等0，需要在拼接上add
        while (sIndex >= 0 || tIndex >= 0||add!=0) {
            int a = 0, b = 0;
            if (sIndex >= 0) {
                a = s.charAt(sIndex--) - 48;
            }
            if (tIndex >= 0) {
                b = t.charAt(tIndex--) - 48;
            }
            int tmp = a + b + add;
            ans.append(tmp%10);
            add = tmp >= 10 ? 1 : 0;
        }
        //存放结果字符串的顺序是反的，需要反转
        return ans.reverse().toString();
    }

    @Test
    public void test() {

        System.out.println(solve("999999", "2234"));
    }
}
