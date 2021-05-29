package com.wangyj.problems.string;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * @author W.Y.J
 * @Date 2021/5/29 11:31
 * https://leetcode-cn.com/problems/partition-labels/
 */
public class PartitionLabels_763 {


    /**
     * 思路不好想到
     * 由于题目规定 同一个字母不能出现在多个片段，所以同一个字母第一次出现的位置(start)和最后一次出现的位置(end)
     * 最终一定是在同一个片段的，同时题目要求尽可能多的产生片段，也就是找满足要求的最短片段（这块其实是贪心算法的思想:每次找到最短的，即也就是最优解），
     * 理想情况下从start到end片段是最短的，但是[start,end]中会引入其他字母，也要保证这些字母也必须在同一个片段中，这个片段中所有字母的max(end)
     * 但是
     * 其实题目的难点在于如何证明这个贪心算法是全局最优解
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        //用一个长度26的数组记录，每个字母最后出现的位置
        int[] last=new int[26];
        for (int i = 0; i < s.length(); i++) {
            //但前字母在last中的位置 s.charAt(i)-'a'
            last[s.charAt(i)-'a']=i;
        }

        int start=0,end=0;
        for (int i = 0; i < s.length(); i++) {
            //随着后移，判断当前片段中出现字母的最后出现位置
            end = Math.max(end,last[s.charAt(i) - 'a']);
            if (i==end){
                ans.add(end-start+1);
                start=end+1;
            }

        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(partitionLabels(""));
    }
}
