package com.wangyj.problems.linked_list;

import org.junit.Test;

import java.awt.*;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author W.Y.J
 * @Date 2021/5/8 21:11
 */
public class removeNthNodeFromEndOfList_19 {


    /**
     * 快慢指针法
     * 这道题最容易想到的时两次循环，第一次计算链表长度，计算出要删除的节点位置，第二次遍历删除。
     * 但是如果想要一次遍历就得到结果怎么办？
     * 我们知道只有完整遍历后，才能知道倒数第N个节点的位置，所以要想保证不二次遍历，
     * 必须在完整遍历的同时，有另外一个指针记录倒数第N个节点位置，考虑双指针遍历。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 \textit{next}next 指针指向链表的头节点。
         * 这样一来，我们就不需要对头节点进行特殊的判断了。
         *
         * 例如，在本题中，如果我们要删除节点 yy，我们需要知道节点 yy 的前驱节点 xx，并将 xx 的指针指向 yy 的后继节点。
         * 但由于头节点不存在前驱节点，因此我们需要在删除头节点时进行特殊判断。但如果我们添加了哑节点，
         * 那么头节点的前驱节点就是哑节点本身，此时我们就只需要考虑通用的情况即可。
         *
         */
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next=head;
        ListNode fast = dummyNode;
        ListNode slow = dummyNode;

        for (int i = 0; i <=n; i++) {
            //让快指针右移n+1个节点，这样快指针会领先n个节点遍历完链表，
            // 这时慢指针指向的节点就是需要删除的节点
            fast=fast.next;
        }

        //快慢指针同时右移，直到快指针遍历完毕
        while (fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        //慢指针的下一个节点就是要删除的节点
        ListNode delNode = slow.next;
        slow.next=delNode.next;
        delNode.next=null;
        return dummyNode.next;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        ListNode one1 = new ListNode(4);
        ListNode two1 = new ListNode(3);
        l1.next = one1;
        one1.next=two1;

        ListNode result = removeNthFromEnd(l1, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

}
