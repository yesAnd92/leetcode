package com.wangyj.problems.recursion;

import com.wangyj.problems.common.Array2BtUtils;
import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author W.Y.J
 * @Date 2021/7/10 9:29 上午
 */
public class LowestCommonAncestorBt_236 {


    /**
     * 使用笨方法进行模拟，果然，这个方法在极端情况下{0,null,1,null,2,null,3,null,4,null,5,null,6，.....}会超时
     * 从根节点开始，每个节点都进行遍历，找到最后一个满足条件的节点（使用广度优先遍历）
     * 每个节点都判断p\q是否是其子节点，使用深度优先遍历
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode re = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        //塞入左右孩子节点
        if (root.left != null) {
            queue.offer(root.left);
        }

        if (root.right != null) {
            queue.offer(root.right);
        }
        //遍历所有节点
        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            //如果这个节点下不包含p或者q，那么这个节点下的所有节点都不用看了
            boolean pFlag = inChildrenTree(node, p);
            if (!pFlag)
                continue;
            boolean qFlag = inChildrenTree(node, q);
            if (!qFlag)
                continue;
            //能够走到这里说明，p\q都在这个节点下（包含此节点）
            re = node;
            //将此节点下的左右孩子塞入队列进一步缩小范围
            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }
        return re;
    }

    private boolean inChildrenTree(TreeNode node, TreeNode target) {
        //这里使用深度优先遍历，也就是递归
        if (Objects.equals(node.val, target.val))
            return true;

        boolean leftFlag = false;
        if (node.left != null) {
            leftFlag = inChildrenTree(node.left, target);
        }

        boolean rightFlag = false;
        if (node.right != null) {
            rightFlag = inChildrenTree(node.right, target);
        }
        return leftFlag || rightFlag;

    }


    /*****************************************************以下为私有方法**************************************************/


    /**
     * 方法二于方法一最大的不同是，方法二直接搜索到pq的值，直接一下定位到准确位置进行判断左右子树
     * 方法一只是判断每个节点下是否涵盖pq两个点，在一堆公共父节点中找到最近的，这样必然导致效率低下
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //临界条件 当前节点已经是空，或者当前节点就是所寻找的p或者q
        if (root == null || root == p || root == q)
            return root;
        /**
         * 这里递归每个节点搜索p、q，对于p、q来讲，要么存在于同一个子树上，要么左右子树各一个
         * 如果在同一个子树上，则返回值不是空的那个子树就是pq所在子树，并且返回值就是最近的祖先（因为在同一个子树上，又是第一个被搜索到的）
         * 如果不在同一个子树上，即左右子树都有返回值，那么当前节点就是最近的祖先
         */
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

//        if (left == null && right == null)
//            return root;
//        if (left == null){
//            return right;
//        }else {
//            return left;
//        }

        //一种更简洁的，等效上边的写法
        return left==null?right:right==null?left:root;
    }


    @Test
    public void test() {
//        Integer[] nums =new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
//        Integer[] nums =new Integer[]{1,2};
        Integer[] nums = new Integer[]{0, null, 1, null, 2, null, 3, null, 4, null, 5, null, 6, null, 7, null, 8, null, 9, null, 10, null, 11, null, 12, null, 13, null, 14, null, 15, null, 16, null, 17, null, 18, null, 19, null, 20, null, 21, null, 22, null, 23, null, 24, null, 25};
        TreeNode root = Array2BtUtils.array2BT(nums);
//        TreeNode re = lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1));
        TreeNode re = lowestCommonAncestor(root, new TreeNode(1), new TreeNode(2));
        System.out.println(re.val);
    }
}
