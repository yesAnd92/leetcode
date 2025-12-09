package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {


    public ArrayList<String> generateParenthesis(int n) {


        // write code here

        ArrayList<String> res = new ArrayList<>();
        int left = 0;//左括号的个数
        int right = 0;//右括号的个数

        backTrace(n, left, right, "", res);
        return res;
    }

    private void backTrace(int n, int left, int right, String temp, List<String> res) {

        if (temp.length() == 2*n) {

            res.add(temp);
            return;
        }

        //左括号没到到数量可以一直增加
        if (left<n){
            backTrace(n,left+1,right,temp+"(",res);
        }

        //右括号必须要少于左括号
        if (left<=n&&left>right){
            backTrace(n,left,right+1,temp+")",res);
        }
    }


    @Test
    public void test(){
        ArrayList<String> strings = generateParenthesis(0);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
