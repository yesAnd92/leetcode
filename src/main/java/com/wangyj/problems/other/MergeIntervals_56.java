package com.wangyj.problems.other;

import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 *
 */
public class MergeIntervals_56 {


    public int[][] merge(int[][] intervals) {
        //申请一个区间
        int[] area=new int[10001];

        for (int i = 0; i < intervals.length; i++) {
            int[] e = intervals[i];
            for (int j = e[0]; j <=e[1] ; j++) {
                area[j]=1;
            }
        }

        int [][] result = new int[intervals.length][2];
        int index=0;
        for (int i = 1; i < area.length; i++) {
            int start=-1;
            if (area[i]==1){
                start=i;
            }
            int end=start+1;
            while (end<area.length&&area[end]==1){
                end++;
            }
            end--;
            int [] r= new int[]{start,end};
            result[index++]=r;
        }
        return result;

    }

    @Test
    public void test(){
        int[][] intervals=new int[][]{new int[]{1,3},new int[]{2,6},new int[]{8,10},new int[]{15,18}};
        int[][] merge = merge(intervals);
        for (int[] ints : merge) {
            System.out.println("["+ints[0]+","+ints[1]+"]");
        }
    }
}
