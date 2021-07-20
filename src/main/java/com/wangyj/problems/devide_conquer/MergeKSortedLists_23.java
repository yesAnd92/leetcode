package com.wangyj.problems.devide_conquer;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * @author W.Y.J
 * @Date 2021/7/20 14:29
 */
public class MergeKSortedLists_23 {


    /**
     * 使用优先级队列的特性，每次从各个链表的头节点中选取最小的加入到结果链表
     * 被选中节点的下一个节点就成了所在链表里的新头节点，把这个新头节点加入队列里
     * 直至队列里的元素都被选用
     * @author W.Y.J
     * @Date 2021/7/20 17:16
     * @param lists
     * @return com.wangyj.problems.devide_conquer.ListNode
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        //构造一个优先级队列
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.val < o2.val)
                return -1;
            else if (o1.val == o2.val)
                return 0;
            else return 1;
        });

        //设置一个哑节点
        ListNode ans = new ListNode(0);
        ListNode p = ans;
        //先将各个链表的头节点加入优先级队列
        for (ListNode node : lists) {
            if (node != null)
                queue.offer(node);
        }

        while(!queue.isEmpty()){
            //取出各链表头节点中最小值
            ListNode minNode = queue.poll();
            //把最小的节点加入结果链表里
            p.next= minNode;
            p=p.next;
            //这个最小节点的下一个节点作为所在链表的新头节点加入都队列
            if (minNode.next!=null)
                queue.offer(minNode.next);
        }
        return ans.next;
    }


    @Test
    public void test(){
        ListNode l1 = ListNodeUtil.convert2ListNode(1, 4, 5);
        ListNode l2 = ListNodeUtil.convert2ListNode(1, 3, 4);
        ListNode l3 = ListNodeUtil.convert2ListNode(2,6);
        ListNode[] lists =new ListNode[]{l1,l2,l3};
        ListNode listNode = mergeKLists(lists);
        ListNodeUtil.print(listNode);
    }
}
