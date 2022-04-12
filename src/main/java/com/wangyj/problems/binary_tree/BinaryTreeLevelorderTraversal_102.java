package com.wangyj.problems.binary_tree;

import com.wangyj.problems.common.TreeNodeUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelorderTraversal_102 {


    /**
     * 广度优先搜索需要借助队列
     */
    public void bfs(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return;

        ArrayDeque<TreeNode> deque = new ArrayDeque();
        //放入头结点
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode first = deque.pollFirst();
            if (first.left != null)
                deque.add(first.left);
            if (first.right != null)
                deque.add(first.right);
            res.add(first.val);
        }
        System.out.println(res);
    }


    /**
     * 相对于普通的bts将所有结果放到一个list中，现在需要分层放到list中，即同一层放到一个list中
     * 问题就在于如何找到每一层的分界点？
     * 我们可以分层遍历，这样每次循环进来队列中的元素都是在同一层次的，都是上一层各个节点入队的
     * 在每一层遍历开始前，先记录队列中的结点数量 n（也就是这一层的结点数量），然后一口气处理完这一层的 nn 个结点。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        //题目中确定节点数量是2000个，直接初始化时指定，可以减少扩容，但是也提高了空间占用。
        ArrayDeque<TreeNode> deque = new ArrayDeque(2001);
        //放入头结点
        deque.add(root);
        while (!deque.isEmpty()) {
            //此时队列的节点都是同一层的，得到同一层的节点数量，遍历这一层所有的节点
            int size = deque.size();
            List<Integer> innerList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode first = deque.pollFirst();
                if (first.left != null)
                    deque.add(first.left);
                if (first.right != null)
                    deque.add(first.right);
                innerList.add(first.val);
            }
            res.add(innerList);
        }
        return res;
    }


    @Test
    public void test() {
        TreeNode head = TreeNodeUtils.array2BT(new Integer[]{1, 2, 5, 3, 4, null, 6, 7});
        bfs(head);
        System.out.println(levelOrder(head));
    }
}
