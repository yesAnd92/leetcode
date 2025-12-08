package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;

public class HasCircle {


    public boolean hasCycle(ListNode head) {
        //使用快慢指针,快指针每次右移两步，慢指针一步
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;
            //如果存在换则一定会有相遇的情况
            if (fast == slow) {
                return true;
            }

        }

        return false;
    }
}
