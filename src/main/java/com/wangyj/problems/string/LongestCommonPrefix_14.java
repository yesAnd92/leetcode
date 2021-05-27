package com.wangyj.problems.string;


import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix/
 *
 * @author W.Y.J
 * @Date 2021/5/27 18:41
 */
public class LongestCommonPrefix_14 {

    public String longestCommonPrefix(String[] strs) {

        //特判
        if (strs.length<1) return "";
        if (strs.length==1) return strs[0];
        //结果
        StringBuilder sb = new StringBuilder();
        //取数组的第一个元素作为比较模板
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                //取出数组每个元素，和模板形同位置进行比较
                if (i>=strs[j].length()||first.charAt(i)!=strs[j].charAt(i)){
                    return sb.toString();
                }
            }
            sb.append(first.charAt(i));
        }
        return sb.toString();
    }


    @Test
    public void test(){
//        String[] strs=new String[]{"flower","flow","flight"};
        String[] strs=new String[]{"flower"};
        System.out.println(longestCommonPrefix(strs));
    }
}
