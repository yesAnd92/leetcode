package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.*;

import static com.wangyj.problems.common.TreeNodeUtils.array2BT;

public class TreeNodeInorderTraversal {


    public int[] inorderTraversal(TreeNode root) {
        // write code here

        List<Integer> list = new ArrayList<>();

        Deque<Object> stack = new ArrayDeque<>();

        //第一个节点入栈
        stack.offerLast(root);

        while (!stack.isEmpty()) {
            Object ele = stack.pollLast();

            if (ele == null)
                continue;

            //是节点，则继续遍历，中序遍历的正常顺序是左 根 右，则入栈顺序是右 根 左
            if (ele instanceof TreeNode) {

                TreeNode right = ((TreeNode) ele).right;
                if (right != null) {
                    stack.offerLast(right);
                }
                stack.offerLast(((TreeNode) ele).val);

                TreeNode left = ((TreeNode) ele).left;
                if (left != null) {
                    stack.offerLast(left);
                }
            } else {
                //是值，说明之前已经访问过，直接就是结果
                list.add((Integer) ele);

            }
        }

        //转成int[]
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }

        return res;

    }

    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);

        int[] ints = inorderTraversal(treeNode);
        for (int anInt : ints) {
            System.out.println(anInt + " ");
        }
    }
}
