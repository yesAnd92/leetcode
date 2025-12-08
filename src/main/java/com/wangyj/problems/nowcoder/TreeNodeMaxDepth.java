package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.wangyj.problems.common.TreeNodeUtils.array2BT;

public class TreeNodeMaxDepth {

    public int maxDepth(TreeNode root) {
        // write code here
        if (root==null){
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        dfs(root, list, 0);

        int max = 0;
        for (Integer m : list) {
            if (max < m) {
                max = m;
            }

        }
        return max;
    }

    private void dfs(TreeNode root, List<Integer> list, int maxDepth) {
        if (root != null) {
            list.add(maxDepth + 1);
            dfs(root.left, list, maxDepth + 1);
            dfs(root.right, list, maxDepth + 1);
        }
    }


    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, null, 4, 5, 6};

        TreeNode treeNode = array2BT(nums);

        int max = maxDepth(treeNode);
        System.out.println(max);

    }
}
