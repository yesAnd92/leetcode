package com.wangyj.problems.other;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeIntervals_56 {


    public int[][] merge(int[][] intervals) {
        //申请一个区间
        int[] area = new int[10001];

        List<int[]> tmp=new ArrayList<>();
        /**
         * 开辟一段空间，如果在区间内标记对应位置
         */
        for (int i = 0; i < intervals.length; i++) {
            //特判
            if (intervals[i][0]==intervals[i][1]){
                tmp.add(new int[]{intervals[i][0],intervals[i][0]});
                continue;
            }
            for (int j = intervals[i][0]; j <= intervals[i][1]; j++) {
                area[j] = 1;
            }
        }

        //根据标记 重新隔离开
        for (int i = 0; i < area.length; i++) {
            if (area[i] == 1) {
                int start = i;
                while (area[i] == 1) {
                    i++;
                }
                int[] r = new int[]{start, i-1};
                tmp.add(r);
            }
        }

        int[][] result = new int[tmp.size()][2];
        for (int i = 0; i < tmp.size(); i++) {
            result[i]=tmp.get(i);
        }
        return result;
    }

    @Test
    public void test() {
        int[][] intervals = new int[][]{new int[]{1, 1}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}};
        int[][] merge = merge(intervals);
        for (int[] ints : merge) {
            System.out.println("[" + ints[0] + "," + ints[1] + "]");
        }
    }
}
