package com.wangyj.problems.linked_list;

/* *
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing
 * together the nodes of the first two lists.
 * Example:
 *  Input: 1->2->4, 1->3->4
 *  Output: 1->1->2->3->4->4
 */
public class ReverseLinkedList_206 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;

        print(reverseList(l1));
    }


    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;//记录当前节点的上一个节点
        ListNode curr = head;
        while (curr.next!=null){
            ListNode temNode =curr.next;  //临时记下一个节点
            curr.next=prev;     //将当前节点的一下个节点指向上一个节点 从而实现翻转
            prev=curr;    //继续向后遍历下一个节点
            curr=temNode;
        }
        return prev;
    }


        public static void print(ListNode node) {
        while (node!= null) {
            System.out.print(node.val + "  ");
            node = node.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
