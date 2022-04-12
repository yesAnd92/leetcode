package com.wangyj.problems.binary_tree;


import com.wangyj.problems.common.TreeNodeUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 其实就是层序遍历二叉树和p102类似，II和I的区别在于返回的结构不同，每一层的数据在一块显示
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印，每一层打印到一行。。
 */
public class PrintBinaryTreeII_Offer32 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root==null)
            return new ArrayList<>();

        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);
        List<List<Integer>> ans=new ArrayList<>();
        while (!deque.isEmpty()){
            List<Integer> level =new ArrayList<>();
            /**
             * 通过队列长度确定这一层有几个元素，从而控制一同层元素放一起
             * 其实可以deque.size--这样遍历可以避免后添加元素个数影响遍历
             */
            int levelSize = deque.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = deque.pollFirst();
                level.add(node.val);
                if (node.left!=null){
                    deque.addLast(node.left);
                }

                if (node.right!=null){
                    deque.addLast(node.right);
                }
            }
            ans.add(level);
        }
        return ans;
    }


    @Test
    public void test(){
        Integer[] integers = {3, 9, 20, null, null, 15, 7};

        List<List<Integer>> lists = levelOrder(TreeNodeUtils.array2BT(integers));
        System.out.println(lists);
    }
}
