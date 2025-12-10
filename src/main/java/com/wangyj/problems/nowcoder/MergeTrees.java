package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import com.wangyj.problems.common.TreeNodeUtils;
import org.junit.Test;

public class MergeTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // write code here

        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        //相当于是结果放到一个全新的树里面，而不是纠结于加到原来的树上
        TreeNode head = new TreeNode(t1.val + t2.val);
        head.left = mergeTrees(t1.left, t2.left);
        head.right = mergeTrees(t2.right, t1.right);

        return head;
    }


    @Test
    public void test(){
        TreeNode t1 = TreeNodeUtils.array2BT(1, 3, 2, 5);
        TreeNode t2 = TreeNodeUtils.array2BT(2, 1, 3, null, 4, null, 7);
        TreeNode treeNode = mergeTrees(t1, t2);
        TreeNodeUtils.printTree(treeNode);

    }
}
