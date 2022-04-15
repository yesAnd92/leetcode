package com.wangyj.problems.common;



import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.List;

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


    public static  void printTree(TreeNode treeNode){

        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(treeNode);

        while (!deque.isEmpty()){
            TreeNode tmp = deque.pollFirst();
            if (tmp.left!=null){
                deque.addLast(tmp.left);
            }
            if (tmp.right!=null){
                deque.addLast(tmp.right);
            }
            ans.add(tmp.val);
        }
        System.out.println(ans);
    }


    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);
        System.out.println(treeNode.left.right.val);

        printTree(treeNode);
    }
}
