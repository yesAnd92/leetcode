package com.wangyj.problems.binary_tree;

import com.wangyj.problems.common.TreeNode;
import com.wangyj.problems.common.TreeNodeUtils;
import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * 是否平衡二叉树
 */
public class IsBalanced_110 {

    public boolean isBalanced(TreeNode root) {

        if (root == null)
            return true;

        //分别计算出左右子树的最大深度，最大深度不等超过2,并且每个子树本身的左右子树深度也不能超过2（这个条件容易忽略）
        int leftDepth = dfs(root.left, 0);
        int rightDepth = dfs(root.right, 0);
        return leftDepth != -1 && rightDepth != -1 && Math.abs(leftDepth - rightDepth) < 2;

    }

    private int dfs(TreeNode root, int dp) {
        if (root == null) {
            return dp;
        }
        //递归判断左右子树的深度
        int leftDepth = dfs(root.left, dp + 1);
        //遍历每个子树时，如果发现已经是不满足条件的了，提前标记结束
        if (leftDepth == -1)
            return -1;
        int rightDepth = dfs(root.right, dp + 1);
        if (rightDepth == -1)
            return -1;
        //每次取子树的最大深度作为这个树的深度
        return Math.abs(leftDepth - rightDepth) <= 1 ? Math.max(leftDepth, rightDepth) : -1;
    }


    @Test
    public void test() {
        System.out.println(isBalanced(TreeNodeUtils.array2BT(3, 9, 20, null, null, 15, 7)));
        System.out.println(isBalanced(TreeNodeUtils.array2BT(1, 2, 2, 3, 3, null, null, 4, 4)));
        System.out.println(isBalanced(TreeNodeUtils.array2BT(1, 2, 2, 3, null, null, 3, 4, null, null, 4)));
    }
}
