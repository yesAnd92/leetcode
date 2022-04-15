package com.wangyj.problems.binary_tree;


import com.wangyj.problems.common.TreeNode;
import com.wangyj.problems.common.TreeNodeUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 根据给定一个树的先序遍历和中序遍历结果，重构出这个树，也就是返回层序遍历结果
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class RebuildBinaryTree_105 {


    /**
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
     * 整体的思路是：
     * 根据先序遍历结果，我们可以知道整个树的根节点，拿着这个根节点去中序遍历的结果中对比，
     * 根节点左右两边的值分别属于左、右子树
     * 递归构建左右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        int preLength = preorder.length;
        int inLength = inorder.length;
        //每次都需要从inorder中找寻子树的根节点，为了性能直接缓存到map中
        Map<Integer, Integer> inorderMap = new HashMap<>(inLength);
        for (int i = 0; i < inLength; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildSubTree(inorderMap, preorder, 0, preLength, inorder, 0, inLength);
    }

    private TreeNode buildSubTree(Map<Integer, Integer> inorderMap, int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {

        //退出条件是不存在子树了，返回null
        if (pStart == pEnd)
            return null;

        //子树的根节点
        TreeNode root = new TreeNode(preorder[pStart]);
        //当前子树根节点对应的下标
        Integer rootIndex = inorderMap.get(root.val);
        int leftLength = rootIndex - iStart;
        /**
         * preorder:   [...,pStart,...left-tree...,...right-tree...,...]
         *
         * inorder:   [...,iStart,...left-tree...,rootIndex,...right-tree...,iEnd,...],
         * 相当于是把inorder的[iStart,iEnd)区间分为[iStart,rootIndex),rootIndex,(rootIndex+1,iEnd)三个部分
         *
         */
        root.left = buildSubTree(inorderMap, preorder, pStart + 1, pStart + leftLength + 1, inorder, iStart, rootIndex);
        root.right = buildSubTree(inorderMap, preorder, pStart + leftLength + 1, pEnd, inorder, rootIndex + 1, iEnd);

        return root;
    }


    @Test
    public void test() {
        TreeNode treeNode = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

        TreeNodeUtils.printTree(treeNode);
    }
}
