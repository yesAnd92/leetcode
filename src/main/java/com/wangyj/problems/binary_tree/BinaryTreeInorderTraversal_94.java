package com.wangyj.problems.binary_tree;

import com.wangyj.problems.common.TreeNodeUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 二叉树的中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInorderTraversal_94 {

    public List<Integer> inorderTraversal(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        if (head == null) return res;

        Map<TreeNode, Integer> accessCountMap = new ConcurrentHashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        //放入头结点
        stack.push(head);
        accessCountMap.put(head, 1);
        while (!stack.isEmpty()) {
            TreeNode root = stack.pop();
            /**
             * 第一次访问仅是获取左右子树数据，第二次被访问才是从栈中往外拿，
             * 这个时候需要记录到结果中
             */
            if (accessCountMap.get(root) == 1) {
                //中序遍历，右节点后输出所以先进栈
                if (root.right != null) {
                    stack.push(root.right);
                    accessCountMap.put(root.right, 1);
                }
                //根节点
                stack.push(root);
                if (root.left!= null) {
                    stack.push(root.left);
                    accessCountMap.put(root.left, 1);
                }
                //最后将当前节点下次设置为第二次访问
                accessCountMap.put(root, 2);
            } else {
                res.add(root.val);
            }
        }
        return res;
    }


    @Test
    public void test() {
        TreeNode head = TreeNodeUtils.array2BT(new Integer[]{1, 2, 5, 3, 4, null, 6, 7});
        System.out.println(inorderTraversal(head));
    }
}
