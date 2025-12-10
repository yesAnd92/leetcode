package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import com.wangyj.problems.common.TreeNodeUtils;
import org.junit.Test;

public class MirrorTree {

    public TreeNode Mirror(TreeNode pRoot) {
        // write code here

        if (pRoot == null) {
            return null;
        }


        //先递归子树
        TreeNode left = Mirror(pRoot.left);
        TreeNode right = Mirror(pRoot.right);

        //交换
        pRoot.left = right;
        pRoot.right = left;

        return pRoot;

    }

    @Test
    public void test() {
        TreeNode t1 = TreeNodeUtils.array2BT(8,6,10,5,7,9,11);
        TreeNodeUtils.printTree(Mirror(t1));
    }
}
