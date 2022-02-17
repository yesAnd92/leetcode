package com.wangyj.problems.recursion;


import com.wangyj.problems.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * @author W.Y.J
 * @Date 2021/7/9 10:35
 */
public class InvertBinaryTree_226 {


    /**
     * 递归---深度优先遍历
     *
     * @param root
     * @return com.wangyj.problems.common.TreeNode
     * @author W.Y.J
     * @Date 2021/7/9 11:23
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        //交换左子树
        TreeNode left = invertTree(root.left);
        //交换右子树
        TreeNode right = invertTree(root.right);

        //交换当前节点的左右孩子
        root.left = right;
        root.right = left;
        return root;
    }


    /**
     * 迭代---广度优先遍历
     *
     * @param root
     * @return com.wangyj.problems.common.TreeNode
     * @author W.Y.J
     * @Date 2021/7/9 11:23
     */
    public TreeNode invertTree2(TreeNode root) {
        //特判
        if(root==null) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        //首先往队列里塞入跟节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            //左右节点塞入
            if (node.left!=null) {
                queue.offer(node.left);
            }
            if (node.right!=null) {
                queue.offer(node.right);
            }
            TreeNode tmp = node.left;
            node.left=node.right;
            node.right=tmp;
        }
        return root;
    }

}
