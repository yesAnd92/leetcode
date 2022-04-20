package com.wangyj.problems.binary_tree;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.TreeNode;
import com.wangyj.problems.common.TreeNodeUtils;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 二叉树的右视图
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class BTRightSideView_199 {


    /**
     * 利用层序遍历分层遍历，每一层遍历时从右向左遍历，取第一个不为null的值作为该层最右边的元素
     */
    public List<Integer> rightSideView(TreeNode root) {

        //存放结果
        List<Integer> ans = new LinkedList<>();

        //特判
        if (root==null)
            return ans;

        TreeNode tmp = null;
        //存放每次需要遍历的子树根节点
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        while (!queue.isEmpty()) {
            //当层最右边的元素
            Integer rightest=null;
            //这里分层变量。int i =  queue.size()；i--可以省一个变量存放当前层的节点个数
            for (int i =  queue.size(); i >0 ; i--) {
                TreeNode node = queue.poll();

                if (node.right!=null){
                    queue.add(node.right);
                }
                if (node.left!=null){
                    queue.add(node.left);
                }
                //记录第一个数作为最右元素
                if (rightest==null){
                    rightest=node.val;
                }
            }
            //将每层的最右元素添加到结果容器内
            ans.add(rightest);
        }

        return ans;
    }



    /*****************************************************还可以使用深度优先算法**************************************************/

    public List<Integer> rightSideView2(TreeNode root) {

        //存放结果
        List<Integer> ans = new LinkedList<>();

        dfs(ans,root,0);
        return ans;
    }

    private void dfs(List<Integer> ans, TreeNode root, int depth) {
        if (root==null)
            return;

        /**
         * 这里是添加最右元素到结果容器，条件是判断是否当前深度的最右元素是否已经添加,可以利用ans.size判断
         * 由于每次都是从右子树开始遍历，右子树存在最右元素，则一定会第一个添加，不会被后边的元素覆盖掉
         */
        if (depth==ans.size()){
            ans.add(root.val);
        }

        depth++;
        dfs(ans,root.right,depth);
        dfs(ans,root.left,depth);
    }


    @Test
    public void test() {
        System.out.println(rightSideView(TreeNodeUtils.array2BT(1)));
        System.out.println(rightSideView(TreeNodeUtils.array2BT(1, 2, 3, null, 5, null, 4)));
        System.out.println(rightSideView(TreeNodeUtils.array2BT(1, null, 2, 3,null, null, 4)));
        System.out.println(rightSideView(TreeNodeUtils.array2BT(4,3,6,1,null,5,null,null,2)));


        System.out.println(rightSideView2(TreeNodeUtils.array2BT(4,3,6,1,null,5,null,null,2)));
    }
}
