package com.wangyj.problems.recursion;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * @author W.Y.J
 * @Date 2021/7/7 10:50 下午
 */
public class MaximumDepthOfBinaryTree_104 {


    private Integer maxDepth = 0;

    public int maxDepth(TreeNode root) {

        findDepth(root,0);
        return maxDepth;
    }

    /**
     * 定义一个函数计算当前节点的深度
     *
     * @param root
     * @param depth
     * @return void
     * @author W.Y.J
     * @Date 2021/7/7 11:08 下午
     */
    private void findDepth(TreeNode root, int depth) {
        //递归遍历条件退出条件 遍历到叶子节点
        if (root == null) {
            return;
        }
        depth++;
        if (depth > maxDepth)
            maxDepth = depth;
        //函数的等价关系
        findDepth(root.left,depth);
        findDepth(root.right,depth);
    }



    /**
     * 上边使用全局变量的方式实现并不优雅，本质是对递归理解不够深入
     * 如果我们知道了左子数和右子树的最大深度为l,r,那么整个二叉树的最大深度一定是Max(r,l)+1
     * 整体思路是和上边一致的
     *
     * 1、函数意义  当前节点子树的深度
     * 2、退出条件  叶子结点没有子树，深度为0
     * 3、等价条件  分别遍历左右子树
     * @author W.Y.J
     * @Date 2021/7/7 11:22 下午
     * @param root
     * @return void
     */
    public int maxDepth2(TreeNode root) {
        if(root==null)
            return 0;
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        return Math.max(left,right)+1;
    }

}
