package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

public class DeleteDuplicatesListNode {

    //一次性删除连续的重复
    public ListNode deleteDuplicates(ListNode head) {
        // write code here

        ListNode curr = head;
        ListNode firstRepeat;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                firstRepeat = curr;
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                firstRepeat.next = curr.next;
            }
            curr = curr.next;
        }

        return head;
    }


    //先删除相邻的重复元素，有重复的再继续删除
    //代码要简洁很多。
    public ListNode deleteDuplicates2(ListNode head) {
        // write code here

        if (head == null)
            return null;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
    }


    //利用新建一个链表实现，链表尾部有重复的就不加入
    public ListNode deleteDuplicates3(ListNode head) {
        // write code here

        if (head == null)
            return null;
        ListNode curr = head;
        ListNode newHead = new ListNode(head.val);
        ListNode newCurr = newHead;
        while (curr != null) {
            if (newCurr.val != curr.val) {
                //不相等就追加到新链表
                newCurr.next = new ListNode(curr.val);
                newCurr = newCurr.next;
            }
            curr = curr.next;
        }

        return newHead.next;
    }


    @Test
    public void test() {
//        ListNode listNode = ListNodeUtil.convert2ListNode(1, 2, 2, 3, 4, 4, 4, 4, 5);
        ListNode listNode = ListNodeUtil.convert2ListNode(1);
//        ListNode listNode = ListNodeUtil.convert2ListNode(1, 1, 1, 1);
        ListNode duplicates = deleteDuplicates3(listNode);
        ListNodeUtil.print(duplicates);
    }
}
