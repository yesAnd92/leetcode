package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;

public class IsBalanceTree {

    public boolean IsBalanced_Solution(TreeNode pRoot) {
        // write code here
        int depth = depth(pRoot);
        return depth != -1;
    }

    private int depth(TreeNode pRoot) {
        if (pRoot == null) {
            return 0;
        }

        int leftDepth = depth(pRoot.left);
        //发现不平衡了 直接返回
        if (leftDepth == -1) {
            return -1;
        }

        int rightDepth = depth(pRoot.right);
        //发现不平衡了 直接返回
        if (rightDepth == -1) {
            return -1;
        }

        //判断两个子树的深度差
        if (Math.abs(leftDepth - rightDepth) > 1)
            return -1;


        //返回最大深度
        return  1 + Math.max(rightDepth, leftDepth);
    }
}
