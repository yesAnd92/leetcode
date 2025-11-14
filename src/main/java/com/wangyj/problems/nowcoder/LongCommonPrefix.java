package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class LongCommonPrefix {

    public  String longestCommonPrefix(String[] strs) {
        // write code here
        if (strs == null||strs.length == 0 )
            return "";

        //最长公共前缀，理论上随机找一个字符串都得符合最长前缀原则，即便它最短，那么他就是答案
        //选一个字符串作为基础，优选第一个，方便遍历
        String firstStr = strs[0];

        for (int i = 0; i < firstStr.length(); i++) {
            //从第二个元素开始遍历
            for (int j = 0; j < strs.length; j++) {
                String currStr = strs[j];
                //i==currStr.length()其它字符有可能比第一个要短
                if (i == currStr.length() || firstStr.charAt(i) != currStr.charAt(i)) {
                    return firstStr.substring(0, i);
                }
            }
        }
        return strs[0];
    }


    //使用结果容器，接收最终结果，可以规避边界特殊值的问题
    public String longestCommonPrefix2(String[] strs) {

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
    public  void main(String[] args) {

        String[] strs = new String[]{"abca"};
        System.out.println(longestCommonPrefix(strs));
    }
}
