package com.wangyj.problems.binary_tree;


import com.wangyj.problems.common.TreeNodeUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 */
public class PrintBinaryTreeI_Offer32 {

    public int[] levelOrder(TreeNode root) {
        if (root==null)
            return new int[0];

        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);


        List<Integer> ans=new ArrayList<>();

        while (!deque.isEmpty()){
            TreeNode node = deque.pollFirst();
            ans.add(node.val);
            if (node.left!=null){
                deque.addLast(node.left);
            }

            if (node.right!=null){
                deque.addLast(node.right);
            }
        }
        return ans.stream().mapToInt(Integer::shortValue).toArray();
    }


    @Test
    public void test(){
        Integer[] integers = {3, 9, 20, null, null, 15, 7};

        int[] ans = levelOrder(TreeNodeUtils.array2BT(integers));
        for (int an : ans) {
            System.out.print(an+" ");
        }
    }
}
