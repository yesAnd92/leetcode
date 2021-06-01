package com.wangyj.problems.array;

import org.junit.Test;

import java.util.Collections;
import java.util.Set;

/**
 * @Author W.Y.J
 * @Date 2021/6/1 21:05
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes_73 {

    public void setZeroes(int[][] matrix) {

        //记录所有出现0的横竖索引
        StringBuilder sb = new StringBuilder();
        int row = matrix.length;
        int col = matrix[0].length;

        //收集出现0的行列
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    //行的索引用+区分，列的用-区分
                    sb.append("+"+i+",").append("-"+j+",");
                }
            }
        }
        String zeroIndexStr = sb.toString();

        //出现0的一行，全部置为0
        for (int i = 0; i < row; i++) {
            if (zeroIndexStr.contains("+"+i+",")) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //出现0的一列，全部置为0
        for (int j = 0; j < col; j++) {
            if (zeroIndexStr.contains("-"+j+",")) {
                for (int i = 0; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    @Test
    public void test() {
//        int[][] matrix3 = new int[][]{{1, 2, 3, 4}, {5, 0, 7, 8}, {0, 10, 11, 12}, {13, 14, 15, 0}};
//        setZeroes(matrix3);

        int[][] matrix3 = new int[][]{{1}};
        setZeroes(matrix3);
        for (int[] ints : matrix3) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
