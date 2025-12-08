package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static com.wangyj.problems.common.TreeNodeUtils.array2BT;

public class TreeNodeLevelOrderArray {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();

        queue.offerLast(root);

        while (!queue.isEmpty()) {
            //当层有几个元素
            int n = queue.size();
            ArrayList<Integer> levelList = new ArrayList<>();
            //单独遍历这一层
            for (int i = 0; i < n; i++) {
                TreeNode top = queue.pollFirst();
                levelList.add(top.val);

                if (top.left != null) {
                    queue.offerLast(top.left);
                }

                if (top.right != null) {
                    queue.offerLast(top.right);
                }
            }

            //这一层完毕之后，将这一层放到结果集
            ans.add(levelList);
        }

        return ans;
    }


    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);

        ArrayList<ArrayList<Integer>> arrayLists = levelOrder(treeNode);
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer integer : arrayList) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }

    }
}
