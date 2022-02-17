package com.wangyj.problems.recursion;

import com.wangyj.problems.common.TreeNode;
import org.junit.Test;

/**
 * 将有序数组转换为二叉搜索树
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * @author W.Y.J
 * @Date 2021/7/6 10:49 下午
 */
public class SortedArrayToBST_108 {

    //参考 https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-24/

    public TreeNode sortedArrayToBST(int[] nums) {

        return convert(nums,0,nums.length-1);
    }

    /**
     * 递归的最小单元是初始化一个节点，然后使用递归方法去初始化左右子树
     * @param nums
     * @param left
     * @param right
     */
    private TreeNode convert(int[] nums,int left,int right){
        if (left>right){
            return null;
        }
        int mid=(left+right)>>1;

        //初始化当前节点
        TreeNode root = new TreeNode(nums[mid]);
        root.left=convert(nums,left,mid-1);
        root.right=convert(nums,mid+1,right);
        return root;
    }


    @Test
    public void test(){
        int[] nums= new int[]{-10,-3,0,5,9};

        TreeNode treeNode = sortedArrayToBST(nums);
        System.out.println(treeNode.val);
    }
}
