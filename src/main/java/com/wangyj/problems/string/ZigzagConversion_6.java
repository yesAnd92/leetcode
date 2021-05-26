package com.wangyj.problems.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author W.Y.J
 * @Date 2021/5/26 23:52
 * https://leetcode-cn.com/problems/zigzag-conversion/
 */
public class ZigzagConversion_6 {


    /**
     * 其实就是一个z字形排列的字符串，按照行输出
     * 那么我们就初始化n个行，分别为每行赋上值
     * 观察z形，每个字符的顺序是从第1行-->n行-->1行... 往复
     *
     * @param s
     * @param numRows
     * @return java.lang.String
     * @author W.Y.J
     * @date 2021/5/26 23:54
     */
    public String convert(String s, int numRows) {
        //特判
        if (numRows==1) return s;

        //先生成n个行
        List<StringBuilder> row = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            row.add(new StringBuilder());
        }

        int r = 0;//每次存放的行下标
        int flag = 1;//每次增加的行下标步长，需要往返，采用取反
        for (char c : s.toCharArray()) {
            row.get(r).append(c);
            r += flag;
            //控制增减
            if (r == 0 || r == numRows - 1) {
                flag = -flag;
            }
        }

        StringBuilder  ansSb=new StringBuilder();
        row.forEach(sb->ansSb.append(sb));
        return ansSb.toString();
    }


    @Test
    public void test(){
        System.out.println(convert("PAYPALISHIRING",2));
    }
}
