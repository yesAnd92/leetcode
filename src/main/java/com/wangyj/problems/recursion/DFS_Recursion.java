package com.wangyj.problems.recursion;

import com.wangyj.problems.common.TreeNodeUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

/**
 * 深度优先搜索，递归实现
 */
public class DFS_Recursion {


    /**
     * 先序
     *
     * @param node
     */
    public void firstRoot(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + ">> ");
            firstRoot(node.left);
            firstRoot(node.right);
        }
    }


    /**
     * 中序遍历
     * @param node
     */
    public void middleRoot(TreeNode node) {
        if (node != null) {
            middleRoot(node.left);
            System.out.print(node.val + ">> ");
            middleRoot(node.right);
        }
    }

    /**
     * 后序遍历
     * @param node
     */
    public void afterRoot(TreeNode node) {
        if (node != null) {
            afterRoot(node.left);
            afterRoot(node.right);
            System.out.print(node.val + ">> ");
        }
    }


    @Test
    public void demo() {
        //构造二叉树
        TreeNode node = TreeNodeUtils.array2BT(new Integer[]{1, 2, 5, 3, 4, null, 6, 7});
        System.out.println("\n先序遍历:");
        firstRoot(node);

        System.out.println("\n中序遍历:");
        middleRoot(node);

        System.out.println("\n后序遍历:");
        afterRoot(node);
    }


}
