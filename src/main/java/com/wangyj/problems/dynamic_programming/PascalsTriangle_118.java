package com.wangyj.problems.dynamic_programming;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle/
 * @author W.Y.J
 * @Date 2021/8/8 12:36 下午
 */
public class PascalsTriangle_118 {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>(numRows);

        //第一行赋初值
        ans.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                List<Integer> previousRow = ans.get(i-1);
                //当前元素值=上一行相同下标对应值+上一行相同小标-1对应值，同事考虑边界问题，超出边界统一设为0
                int left =j-1<0?0:previousRow.get(j-1);
                int right = j>i-1?0:previousRow.get(j);
                list.add(left+right);
            }
            ans.add(list);
        }

        return ans;
    }


    @Test
    public void test(){
        System.out.println(generate(1));
    }
}
