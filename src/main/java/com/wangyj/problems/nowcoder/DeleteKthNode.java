package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

public class DeleteKthNode {

    public void delKthNode(ListNode head, int n) {

        ListNode fast = head;
        ListNode slow = head;

        //快指针先走k次，快指针遍历到尾巴时慢指针刚好在k的位置
        int count = 0;
        while (count <= n) {
            fast = fast.next;
            count++;
        }

        while (fast!= null) {
            //快慢指针同时走,直到快指针走到头
            slow = slow.next;
            fast = fast.next;
        }

        //此时slow在倒数k+1的位置，删除倒数第k个节点
        slow.next = slow.next.next;


    }


    @Test
    public void test(){
        ListNode head = ListNodeUtil.convert2ListNode(1, 2, 3, 4, 5, 6);
        delKthNode(head,4);
        ListNodeUtil.print(head);
    }
}
