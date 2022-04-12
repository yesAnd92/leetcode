package com.wangyj.problems.binary_tree;


import com.wangyj.problems.common.TreeNodeUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * III和II相比需要是之字形打印
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 * 实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 */
public class PrintBinaryTreeIII_Offer32 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null)
            return new ArrayList<>();

        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);
        List<List<Integer>> ans = new ArrayList<>();
        int reverse = 1;
        while (!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            /**
             * 通过队列长度确定这一层有几个元素，从而控制一同层元素放一起
             * */
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.pollFirst();
                level.add(node.val);
                if (node.left != null) {
                    deque.addLast(node.left);
                }

                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
            if (reverse<0){
                Collections.reverse(level);
            }
            ans.add(level);
            reverse *= -1;
        }
        return ans;
    }



    public List<List<Integer>> levelOrder2(TreeNode root) {

        if (root == null)
            return new ArrayList<>();

        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);
        List<List<Integer>> ans = new ArrayList<>();
        int reverse = -1;
        while (!deque.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            /**
             * 通过队列长度确定这一层有几个元素，从而控制一同层元素放一起
             * */
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.pollFirst();
                if (reverse<0){
                    level.addLast(node.val);
                }else {
                    level.addFirst(node.val);
                }
                if (node.left != null) {
                    deque.addLast(node.left);
                }

                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
            ans.add(level);
            reverse *= -1;
        }
        return ans;
    }


    @Test
    public void test() {
//        Integer[] integers = {3, 9, 20, null, null, 15, 7};
        Integer[] integers = {3, 9, 4, 1, 12, 34, 20, null, null, 15, 7};

        List<List<Integer>> lists = levelOrder2(TreeNodeUtils.array2BT(integers));
        System.out.println(lists);
    }
}
