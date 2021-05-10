package com.wangyj.problems.linked_list;

import org.junit.Test;

import java.awt.*;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @Author W.Y.J
 * @Date 2021/5/10 21:38
 */
public class ReverseNodesInKGroup_25 {

    /**
     * 自定义链表结构
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 利用栈结构进行反转
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        ListNode nextNode = dummy;
        Stack<ListNode> stack = new Stack();
        while (head != null) {
            ListNode tmp = head;
            head=head.next;
            tmp.next=null;//要讲下一个节点置空
            //遍历放到栈中
            stack.push(tmp);
            //当栈容量大小=k时，需要反转
            if (stack.size() == k) {
                while (!stack.empty()) {
                    //依次将栈中的元素添加
                    nextNode.next = stack.pop();
                    nextNode=nextNode.next;
                }
            }
        }
        //兼容k刚好等分链表，栈容量0的情况
        if (stack.size()==0)return dummy.next;
        //最后剩余节点小于k,单独处理,注意正序栈中元素
        ListNode tail = stack.pop();
        while (!stack.empty()) {
            ListNode right = stack.pop();
            right.next=tail;
            tail=right;
        }
        nextNode.next=tail;
        return dummy.next;
    }


    @Test
    public void test() {
        ListNode n1=new ListNode(3);
        ListNode n2=new ListNode(6);
        ListNode n3=new ListNode(9);
        ListNode n4=new ListNode(1);
        ListNode n5=new ListNode(1);
        ListNode n6=new ListNode(4);
        ListNode n7=new ListNode(7);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        n6.next=n7;
        ListNode result = reverseKGroup(n1, 4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

}
