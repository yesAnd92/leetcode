package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;
import org.junit.Test;

public class ReverseList {


    public ListNode ReverseList(ListNode head) {
        // write code here

        //准备三个节点
        ListNode pre = null;// 前一个节点，初始为nul
        ListNode curr = head;// 当前节点，从头节点开始
//        ListNode nextTemp = head;// 指向下一个临时节点

        while (curr!= null) {
            //先记录下一个节点，防止修改完之后找不到下一个节点
            ListNode nextTemp = curr.next;
            // 2. 反转指针：将当前节点的next指向前一个节点
            //    这是反转的核心操作
            curr.next = pre;
            // 3. 移动指针：为下一次循环做准备
            //    prev向后移动到当前节点
            pre = curr;
            //    curr向后移动到之前保存的下一个节点
            curr = nextTemp;

        }
        // 循环结束后，prev指向的就是新链表的头节点
        return pre;
    }


    /**
     * 我觉得这种思路更适合我
     * 定义虚节点dummy，遍历链表不断插入到dummy后边
     * dummy->null
     * dummy->1->null
     * dummy->2->1->null ...
     *
     */
    public ListNode ReverseList2(ListNode head) {

        ListNode dummy=new ListNode(-1);
        dummy.next=null;

        ListNode curr=head;
        while (curr!=null){
            //记录原有俩表的下一个节点
            ListNode currNext = curr.next;

            //插入dummy节点后边
            ListNode next = dummy.next;
            dummy.next=curr;
            curr.next=next;

            //原有链表后移
            curr=currNext;

        }

        return dummy.next;
    }

    @Test
    public void test() {
        ListNode pre = new ListNode(-1);
        ListNode curr = pre;
        for (int i = 1; i < 10; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }


        ListNode reverseList = ReverseList(pre.next);
        ListNode p = reverseList;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }

    }
}
