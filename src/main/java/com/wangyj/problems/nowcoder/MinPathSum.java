package com.wangyj.problems.nowcoder;

import org.junit.Test;

//最小路径和  字节笔试题
public class MinPathSum {

    public int  minPath(int[][] paths) {

        //矩阵的行列长度
        int row = paths.length;
        int col = paths[0].length;

        //先初始化上、左边界
        int[][] dp=new int[row][col];
        dp[0][0]=paths[0][0];
        for (int i = 1; i <row ; i++) {
            dp[i][0]=paths[i][0]+dp[i-1][0];
        }

        for (int j = 1; j <col ; j++) {
            dp[0][j]=paths[0][j]+dp[0][j-1];
        }

        //对于（i，j）位置，它可以由（i-1,j)向右走一步 或者（i,j-1）向下走一步达到
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+paths[i][j];
            }
        }

        //返回右下角的数
        return dp[row-1][col-1];

    }

    @Test
    public void test(){

        int[][] paths=new int[3][3];
        paths[0]=new int[]{1,3,1};
        paths[1]=new int[]{1,5,1};
        paths[2]=new int[]{4,2,1};

        int minPath = minPath(paths);
        System.out.println(minPath);
    }
}
