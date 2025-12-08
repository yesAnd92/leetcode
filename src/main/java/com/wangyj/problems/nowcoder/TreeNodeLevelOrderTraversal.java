package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static com.wangyj.problems.common.TreeNodeUtils.array2BT;


//层序遍历
public class TreeNodeLevelOrderTraversal {


    public List<Integer> levelOrderTraversal(TreeNode root) {
        // write code here

        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> queue = new ArrayDeque();
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            TreeNode top = queue.pollFirst();
            res.add(top.val);
            TreeNode left = top.left;
            if (left != null) {
                queue.offerLast(left);
            }
            TreeNode right = top.right;
            if (right != null) {
                queue.offerLast(right);
            }
        }

        return res;
    }


    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);

        List<Integer> integers = levelOrderTraversal(treeNode);
        for (int anInt : integers) {
            System.out.println(anInt + " ");
        }
    }
}
