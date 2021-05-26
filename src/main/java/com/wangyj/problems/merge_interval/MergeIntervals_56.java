package com.wangyj.problems.merge_interval;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeIntervals_56 {



    /**
     * 这种标记法不适合本题，无法解决[1,2] [3,6]这种场景，
     * 因为标记完1,2,3都连在一起，重新个离开时，无法区分。但实际上2，3并没有重叠，不能合并。
     * @author W.Y.J
     * @date 2021/5/26 21:31
     */
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


    public int[][] merge2(int[][] intervals) {

        //首先对区间的做端点进行排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //存放结果
        int[][] result=new int[intervals.length][2];
        int index=0;
        result[index++]=intervals[0];
        for (int i = 1; i < intervals.length; i++) {

            //如果当前区间的左端点小于上一个区间的右端点说明有重合，需要合并
            if (intervals[i][0]<=result[index-1][1]){
                result[index-1]=new int[] {Math.min(intervals[i-1][0],result[index-1][0]),Math.max(intervals[i][1],result[index-1][1])};
            }else {
                //当前区间的左端点大于 上一个区间的右端点，没有重叠，直接加入
                result[index++]=intervals[i];
            }
        }
        return Arrays.copyOf(result,index);
    }

    @Test
    public void test() {
        int[][] intervals = new int[][]{new int[]{1, 2}, new int[]{2, 6}, new int[]{3, 10}, new int[]{15, 18}};
//        int[][] intervals = new int[][]{new int[]{1, 4},new int[]{0, 2}, new int[]{3, 5}};
        int[][] merge = merge2(intervals);
        for (int[] ints : merge) {
            System.out.println("[" + ints[0] + "," + ints[1] + "]");
        }
    }
}
