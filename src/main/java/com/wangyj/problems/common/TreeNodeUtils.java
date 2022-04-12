package com.wangyj.problems.common;


import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 数组按照行顺序转换为二叉树
 *
 * @author W.Y.J
 * @Date 2021/7/10 9:49 上午
 */
public class TreeNodeUtils {


    /**
     * 使用广度优先遍历，遍历时构建树结构并赋值
     *
     * @param nums
     * @return com.wangyj.problems.common.TreeNode
     * @author W.Y.J
     * @Date 2021/7/10 10:41 上午
     */
    public static TreeNode array2BT(Integer... nums) {

        int length = nums.length;
        int index = 0;
        Queue<TreeNode> treeQueue = new ArrayDeque<>();
        TreeNode root = new TreeNode(nums[index++]);
        treeQueue.offer(root);
        while (!treeQueue.isEmpty()) {
            TreeNode node = treeQueue.poll();
            if (node == null)
                continue;
            //取出当前节点，分别为左右节点赋值
            TreeNode left = null;
            if (index < length) {
                Integer num = nums[index++];
                if (num != null) {
                    left = new TreeNode(num);
                    treeQueue.offer(left);
                }
            }

            TreeNode right = null;
            if (index < length) {
                Integer num = nums[index++];
                if (num != null) {
                    right = new TreeNode(num);
                    treeQueue.offer(right);
                }
            }

            node.left = left;
            node.right = right;
        }

        return root;
    }


    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);
        System.out.println(treeNode.left.right.val);
    }
}
