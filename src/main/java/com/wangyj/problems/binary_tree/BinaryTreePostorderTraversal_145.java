package com.wangyj.problems.binary_tree;

import com.wangyj.problems.common.Array2BtUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 二叉树的后序遍历
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal_145 {


    /**
     * 思路是每个节点都可以看做一个树，我们按照前序、中序、后序要求将一个个子树入栈，
     * 整体结构根节点的相对位置就是符合题意的，再想办法将每个子树根节点的值“同步”记录下来，这个结果
     */
    public List<Integer> postorderTraversal(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        if (head == null) return res;

        //前中后序迭代通用模版，重点是把值也入栈
        Stack<Object> stack = new Stack<>();
        //放入头结点
        stack.push(head);
        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            /**
             * 如果栈顶元素是一个树，我们根据要求继续遍历当前树的根、子树
             * 如果栈顶元素是一个数字，也就是我们需要的结果值，直接保存下来
             */
            if (obj instanceof TreeNode) {
                TreeNode root = (TreeNode) obj;
                //后序遍历，入栈顺序是 根 右 左
                //根节点值塞入栈
                stack.push(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left!= null) {
                    stack.push(root.left);
                }
            } else {
                res.add((Integer) obj);
            }
        }
        return res;
    }



    @Test
    public void test() {
        TreeNode head = Array2BtUtils.array2BT(new Integer[]{1, 2, 5, 3, 4, null, 6, 7});
        System.out.println(postorderTraversal(head));
    }
}
