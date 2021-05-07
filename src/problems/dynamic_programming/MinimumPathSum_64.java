package problems.dynamic_programming;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 *
 *Given a m x n grid filled with non-negative numbers, find a path from top left to bottom
 * right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum_64 {


    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {2, 2}};

        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0) return 0;
        //grid为m*n的二维数组
        int m = grid.length;
        int n = grid[0].length;

//        print(grid);
        /**
         * 设opt[i][j]为grid从[0][0]到[i][j]中依照规则路径的最小和,
         * 则状态转移方程 opt[i][j]=min{opt[i-1][j],opt[i][j-1]}+grid[i][j]
         */
        int[][] opt = new int[m][n];

        //初始化边界值 第一行
        opt[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            opt[0][i] = opt[0][i - 1] + grid[0][i];
        }
        //初始化边界值 第一列
        for (int i = 1; i < m; i++) {
            opt[i][0] = opt[i - 1][0] + grid[i][0];
        }

//        print(opt);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int left = opt[i][j - 1];  //当前元素左边的值
                int up = opt[i - 1][j];    //当前元素上边的值
                int min = Math.min(left, up);
                opt[i][j] = min + grid[i][j];
//                print(opt);
            }
        }
        return opt[m - 1][n - 1];
    }


    private static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
}
