package com.wangyj.problems.binary_tree;

import com.wangyj.problems.common.Array2BtUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的先序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreorderTraversal_144 {

    public List<Integer> preorderTraversal(TreeNode head) {

        List<Integer> res = new ArrayList<>();

        if (head==null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        //放入头结点
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode root = stack.pop();
            //先序，先把根节点输出
            res.add(root.val);
            //右节点后输出所以先进栈
            TreeNode right = null;
            if ((right = root.right) != null) {
                stack.push(right);
            }
            TreeNode left = null;
            if ((left = root.left) != null) {
                stack.push(left);
            }
        }
        return res;
    }


    @Test
    public void test(){
        TreeNode head = Array2BtUtils.array2BT(new Integer[]{1, 2, 5, 3, 4, null, 6, 7});
        System.out.println(preorderTraversal(head));
    }
}
