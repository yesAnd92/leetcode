package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import com.wangyj.problems.common.TreeNodeUtils;
import org.junit.Test;

/*
 *在二叉树中找到两个节点的最近公共祖先
 */
public class LowestCommonAncestor {


    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        // write code here
        return dfs(root, o1, o2);
    }

    private int dfs(TreeNode root, int o1, int o2) {
        if (root == null) {
            return -1;
        }


        //有一个命中，两边
        if (root.val == o1 || root.val == o2) {
            return root.val;
        }

        //最近的公共根节点，他的左右子树肯定是各有一个目标值
        int left = dfs(root.left, o1, o2);
        int right = dfs(root.right, o1, o2);

        if (left == -1) {
            //左边没找到，则右边肯定是最近的公共节点，因为递归是先执行下层的
            return right;
        }

        if (right == -1) {
            return left;
        }

        //刚好是当前节点
        return root.val;
    }

    @Test
    public void test() {
        TreeNode root = TreeNodeUtils.array2BT(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        int common = lowestCommonAncestor(root, 2, 7);
        System.out.println(common);
    }
}
