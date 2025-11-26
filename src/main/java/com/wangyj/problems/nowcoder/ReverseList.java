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
