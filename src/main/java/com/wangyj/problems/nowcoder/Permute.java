package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Permute {

    // write code here
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> permute(int[] num) {


        List<Integer> used = new ArrayList<>();

        backTrace(num, used);

        return res;

    }

    private void backTrace(int[] num, List<Integer> used) {

        if (used.size() == num.length) {
            //排列完毕
            res.add(new ArrayList<>(used));
            return;
        }


        for (int i = 0; i < num.length; i++) {
            if (used.contains(num[i])) {
                //已经添加就跳过
                continue;
            }

            used.add(num[i]);
            backTrace(num, used);

            //撤回当前的添加,继续寻找全排序列
            used.remove(used.size() - 1);
        }
    }


    @Test
    public void test(){

//        int[] num = {1, 2, 3};
        int[] num = {1};
        ArrayList<ArrayList<Integer>> permute = permute(num);
        for (ArrayList<Integer> list : permute) {
            for (Integer e : list) {
                System.out.println(e+" ");

            }
            System.out.println();
        }
    }

}
