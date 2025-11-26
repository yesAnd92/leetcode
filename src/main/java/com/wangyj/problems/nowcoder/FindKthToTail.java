package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindKthToTail {

    public ListNode FindKthToTail(ListNode pHead, int k) {
        // write code here

        int count = 0;
        ListNode curr = pHead;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        if (count < k) {
            return null;
        }

        int i = 0;
        curr = pHead;
        while (i < count - k) {
            curr = curr.next;
            i++;
        }

        return curr;
    }

    //使用快慢指针更加巧妙，快指针先走k步，那么当快指针走到头的时候
    // 慢指针刚好走到第k的位置
    public ListNode FindKthToTail2(ListNode pHead, int k) {
        // write code here

        ListNode fast = pHead;
        ListNode slow = pHead;
        int count = 0;
        while (fast != null) {
            count++;

            fast = fast.next;

            if (count > k) {
                slow = slow.next;
            }
        }

        if (count == k) {
            //刚好长度就是k
            return pHead;
        } else if (count > k) {
            return slow;
        } else {
            //说明k>n
            return null;
        }

    }


    @Test
    public void test() {
        ListNode pHead = array2ListNode(1, 2, 3, 4, 5);
        ListNode listNode = FindKthToTail2(pHead, 2);

        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }

    }


    public ListNode array2ListNode(Integer... e) {

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, e);
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (Integer integer : list) {
            curr.next = new ListNode(integer);
            curr = curr.next;
        }
        return dummy.next;
    }
}
