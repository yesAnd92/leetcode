package com.wangyj.problems.fast_slow_pointer;


import com.wangyj.problems.linked_list.ListNode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class LinkedListCycle_141 {

    public boolean hasCycle(ListNode head) {
        //特判
        if (head==null||head.next==null){
            return false;
        }
        ListNode slow = head;
        ListNode fast=head.next;
        while (slow!=fast){
            if (fast==null||fast.next==null){
                return false;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }
}
