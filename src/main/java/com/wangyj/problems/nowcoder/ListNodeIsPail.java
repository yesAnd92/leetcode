package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayDeque;

public class ListNodeIsPail {


    public boolean isPail(ListNode head) {
        if (head == null) {
            return false;
        }
        // write code here

        ArrayDeque<Integer> stack = new ArrayDeque();

        //先遍历放到栈中
        ListNode curr = head;
        while (curr != null) {
            stack.offerLast(curr.val);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            if (curr.val != stack.pollLast()) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    @Test
    public void test() {
        ListNode listNode = ListNodeUtil.convert2ListNode(1);
        System.out.println(isPail(listNode));
    }
}
