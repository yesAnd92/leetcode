package problems;

/* *
 *  https://leetcode.com/problems/unique-paths/
 *  robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *  The robot can only move either down or right at any point in time. The robot is trying to reach
 *  the bottom-right corner of the grid (marked 'Finish' in the diagram below)
 *  How many possible unique paths are there?
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 *      From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 *      1. Right -> Right -> Down
 *      2. Right -> Down -> Right
 *      3. Down -> Right -> Right
 *
 * @author wangyj
 * @date 2019/4/9
 */
public class UniquePaths62 {

    public static void main(String[] args) {

        System.out.println(dpOpt(7,3));
    }

    /**
     * 想要到达(m,n)点，按照规则只有两种方法
     * 1、从（m-1,n）点向右走一步
     * 2、从（m,n-1）向下走一步
     * 故：
     * 设opt[m][n]为按照规则向右或者向下到(m,n)这个点方法总数，
     * 状态方程为 opt[i][j]=opt[m][n-1]+opt[m-1][n];
     */
    public static int uniquePaths(int m, int n) {

        int[][] opt =new int[m+1][n+1];
        opt[1][1]=1;
        //初始化边界值 第一行
        for (int i=2;i<=n;i++){
            opt[1][i]=1;
        }
        //初始化边界值 第一列
        for (int i=2;i<=m;i++){
            opt[i][1]=1;
        }

        for (int i=2;i<=m;i++){
            for (int j=2;j<=n;j++){
                opt[i][j]=opt[i][j-1]+opt[i-1][j];
            }
        }
        return opt[m][n];
    }

    /**
     * 接上，可以在边界初始化上优化，减少循环数量
     */
    public static int dpOpt(int m, int n) {

        int[][] opt =new int[m+1][n+1];

        if (m==1&&n==1){
            return 1;
        }
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (i==1){
                    //opt[][]第一行的的值都是1
                    opt[i][j-1]=1;
                }
                if (j==1){
                    //opt[][]第一列的的值都是1
                    opt[i-1][j]=1;
                }
                opt[i][j]=opt[i][j-1]+opt[i-1][j];
            }
        }
        return opt[m][n];
    }
}
