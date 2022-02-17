package com.wangyj.problems.recursion;


import com.wangyj.problems.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 * @author W.Y.J
 * @Date 2021/7/5 9:39 下午
 */
public class SymmetricTree_101 {

    public boolean isSymmetric(TreeNode root) {
        return check(root,root);
    }


    /**
     * 实现一个递归函数，同步移动两个指针来遍历这棵树，p、q一开始都指向这棵树的根，随后p右移时，q左移；p左移时，q右移。
     * 每次移动后都比较连个节点是否相等，如果相等在继续判断左右子树是否对称。
     * @param p
     * @param q
     * @return
     */
    public boolean check(TreeNode p,TreeNode q){
        //同时为空，遍历完了（退出条件）
        if(p==null&&q==null){
            return true;
        }
        //两个值不想等也可以退出
        if (p==null||q==null){
            return false;
        }
        return q.val==p.val&&check(q.left,p.right)&&check(q.right,p.left);
    }



    /**************************************************迭代**************************************************/

    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()){

            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            //同时为空，遍历完了（退出条件）
            if(left==null&&right==null){
                continue;//这块注意不能直接放回true,因为队列里还没有遍历完，遍历到叶子节点时是left==null&&right==null，但是后续还要遍历
            }
            //两个值不想等也可以退出
            if (left==null||right==null||left.val!= right.val){
                return false;
            }
            //左节点的左孩子和有节点的左孩子放一起比较
            queue.offer(left.right);
            queue.offer(right.left);
            //左节点的左孩子和右节点的右孩子放一起比较
            queue.offer(left.left);
            queue.offer(right.right);
        }
        return true;
    }


    private void printQueue(Queue<TreeNode> queue){
        queue.forEach(treeNode -> {
            int val = treeNode.val;
            System.out.println(val+"-");
        });

    }


    private void list2TreeNode(List<Integer> list){


    }
}

