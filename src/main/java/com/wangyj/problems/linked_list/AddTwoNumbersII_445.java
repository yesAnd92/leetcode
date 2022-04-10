package com.wangyj.problems.linked_list;


import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 *
 * @author W.Y.J
 * @Date 2022/4/10 17:44
 */
public class AddTwoNumbersII_445 {



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //由于顺序是反的，需要借助栈来调序
        Deque<Integer> num1 = new ArrayDeque<>(128);
        Deque<Integer> num2 = new ArrayDeque<>(128);

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                num1.addLast(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                num2.addLast(l2.val);
                l2 = l2.next;
            }
        }

        ListNode ans = null;
        int carry = 0;
        while (num1.peek() != null || num2.peek() != null || carry > 0) {
            int a = num1.peekFirst() == null ? 0 : num1.pollLast();
            int b = num2.peekFirst() == null ? 0 : num2.pollLast();
            int sum = a + b + carry;
            carry = sum / 10;
            //这块需要反向拼接链表，每次新节点放到前边，这种问题最好能画图来写指针指向
            ListNode curr = new ListNode(sum % 10);
            curr.next = ans;
            ans=curr;
        }

        return ans;
    }


    @Test
    public void test() {
        ListNode l1 = ListNodeUtil.convert2ListNode(7, 2, 4, 3);
        ListNode l2 = ListNodeUtil.convert2ListNode(5, 6, 4);
        ListNode head = addTwoNumbers(l1, l2);
        ListNodeUtil.print(head);
    }

}
