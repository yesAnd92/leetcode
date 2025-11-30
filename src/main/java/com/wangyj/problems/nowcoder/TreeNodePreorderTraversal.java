package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.wangyj.problems.common.TreeNodeUtils.array2BT;

public class TreeNodePreorderTraversal {




    public int[] preorderTraversal(TreeNode root) {
        // write code here

        List<Integer> res=new ArrayList<>();

        dfs(root,res);
        int[] result=new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i]=res.get(i);
        }
        return result;

    }

    private void dfs(TreeNode root, List<Integer> res ) {

        if (root != null) {
            res.add(root.val);
            dfs(root.left, res);
            dfs(root.right, res);
        }
    }

    @Test
    public void test(){
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);

        int[] ints = preorderTraversal(treeNode);
        for (int anInt : ints) {
            System.out.println(anInt+" ");
        }
    }
}
