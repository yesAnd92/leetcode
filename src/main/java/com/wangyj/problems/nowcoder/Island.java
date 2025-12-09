package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class Island {

    public int solve(char[][] grid) {
        // write code here

        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;

                    //将这个点所在的岛都置为0，防止重复计数
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {

        int row = grid.length;
        int col = grid[0].length;

        //退出条件
        if (i<0||i>=row||j<0||j>=col||grid[i][j]=='0'){
            return;
        }
        grid[i][j]='0';

        //遍历四个方向
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
    }



    @Test
    public void test(){
//        char[][] grid=new char[5][5];
//        grid[0]=new char[]{'1','1','0','0','0'};
//        grid[1]=new char[]{'0','1','0','1','1'};
//        grid[2]=new char[]{'0','0','0','1','1'};
//        grid[3]=new char[]{'0','0','0','0','0'};
//        grid[4]=new char[]{'0','0','1','1','1'};


        char[][] grid=new char[1][1];
        grid[0]=new char[]{'0'};
        int solve = solve(grid);
        System.out.println(solve);
    }

}
