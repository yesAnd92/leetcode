package com.wangyj.problems.math;

/**
 * @author W.Y.J
 * @Date 2021/5/30 19:06
 * https://leetcode-cn.com/problems/add-digits/
 */
public class AddDigits_258 {

    /**
     * 能够被9整除的整数，各位上的数字加起来也必然能被9整除，所以，连续累加起来，最终必然就是9。
     * 不能被9整除的整数，各位上的数字加起来，结果对9取模，和初始数对9取摸，
     * 是一样的，所以，连续累加起来，最终必然就是初始数对9取摸。
     */
    public int addDigits(int num) {
        if (0 == num % 9) {
            return 9;
        }
        return num % 9;
    }
}
