package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique {

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        //升序
        Arrays.sort(num);

        ArrayList<ArrayList<Integer>> re = new ArrayList<>();

        //这里存放使用过的下标，防止重复数字被过滤掉
        List<Integer> used = new ArrayList<>();
        backTrace(num, re, used);

        return re;

    }

    private void backTrace(int[] num, ArrayList<ArrayList<Integer>> re, List<Integer> used) {
        if (num.length == used.size()) {
            //排列完成，增加到结果集中
            ArrayList<Integer> tempList = new ArrayList<>();
            for (Integer i : used) {
                tempList.add(num[i]);
            }
            re.add(tempList);
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (used.contains(i) ) {
                continue;
            }
            if (i > 0 && num[i] == num[i - 1]&&used.contains(i-1)){
                //如果前一个相同的元素已经在used中，则不应重复添加
                continue;
            }

            used.add(i);

            backTrace(num, re, used);

            used.remove(used.size() - 1);
        }

    }


    @Test
    public void test() {
        int[] nums = new int[]{1, 1, 2};
        ArrayList<ArrayList<Integer>> permuteUnique = permuteUnique(nums);
        for (ArrayList<Integer> list : permuteUnique) {
            for (Integer n : list) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
