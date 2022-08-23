package com.wangyj.problems.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _131 {

    @Test
    public void test() {

        Long a=123L;
        Long b=123L;
        Long c=1234L;
        Long d=1234L;
        System.out.println(a==b);
        System.out.println(c==d);
    }


    public List<List<String>> partition(String s) {

        List<List<String>> ans = new ArrayList<>();
        int length = s.length();
//        for (int step = 1; step <=length; step++) {
//            for (int i = 1; i < length-step; i++) {
//Long
//            }
//        }
        return  ans;
    }



    public boolean isHuiwen(String s) {
        if (s.length() == 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
