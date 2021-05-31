package com.wangyj.problems.array;

/**
 * @Author W.Y.J
 * @Date 2021/5/31 21:15
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_54 {


    /**
     * 一圈一圈的遍历
     * 怎么确定圈数？ 观察可以发现它取决于行列中最小的，一半就是需要遍历的圈数，
     * 兼容奇偶两种情况 写成(min(m,n)+1)/2
     *
     * 比较难的是怎么控制转方向？不好找到一个统一的表达能够涵盖四个方向，分开写试试
     * 每一圈的遍历分为四个方向 左-->右，上-->下，右-->左，下-->上
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans=new ArrayList<>();
        int m = matrix.length;//m行
        int n = matrix[0].length;//n列

        //确定矩阵的边界
        int up=0,down=m-1,left=0,right=n-1;
        //圈数
        int circle=(Math.min(m,n)+1)/2;

        int i=0;
        int x=0,y=0;//开始遍历的起点坐标
        while (i<circle){
            //从左到右
            while(y<=right&&left<=right&&up<=down){
                ans.add(matrix[x][y++]);
            }
            //遍历完一行，上边界去掉一行，此时下一次的启动点应为（up+1,right）
            x=++up;
            y=right;

            //从上到下
            while(x<=down&&left<=right&&up<=down){
                ans.add(matrix[x++][y]);
            }
            //遍历完一列，右边界去掉一列，此时下一次的启动点应为（down,right-1）
            x=down;
            y=--right;

            //从右到左
            while(y>=left&&left<=right&&up<=down){
                ans.add(matrix[x][y--]);
            }
            //遍历完一行，右边界去掉一列，此时下一次的启动点应为（down-1,left）
            x=--down;
            y=left;

            //从下到上
            while(x>=up&&left<=right&&up<=down){
                ans.add(matrix[x--][y]);
            }
            //遍历完一行，右边界去掉一列，此时下一次的启动点应为（up,left+1）
            x=up;
            y=++left;

            //下一圈
            i++;

        }

        return ans;

    }


    @Test
    public void test(){

        int[][] matrix =new  int[][]{{1,2,3},{4,5,6},{7,8,9}};//[1,2,3,6,9,8,7,4,5]
        System.out.println(spiralOrder(matrix));
        int[][] matrix2 =new  int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};//[1,2,3,4,8,12,11,10,9,5,6,7]
        System.out.println(spiralOrder(matrix2));
        int[][] matrix3 =new  int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};//[1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
        System.out.println(spiralOrder(matrix3));
    }
}

