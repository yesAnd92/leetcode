package com.wangyj.problems.linked_list;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @Author W.Y.J
 * @Date 2021/5/10 21:38
 */
public class ReverseNodesInKGroup_25 {


    /**
     * 利用栈结构进行反转
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        ListNode nextNode = dummy;
        Stack<ListNode> stack = new Stack();
        while (head != null) {
            ListNode tmp = head;
            head = head.next;
            tmp.next = null;//要讲下一个节点置空
            //遍历放到栈中
            stack.push(tmp);
            //当栈容量大小=k时，需要反转
            if (stack.size() == k) {
                while (!stack.empty()) {
                    //依次将栈中的元素添加
                    nextNode.next = stack.pop();
                    nextNode = nextNode.next;
                }
            }
        }
        //兼容k刚好等分链表，栈容量0的情况
        if (stack.size() == 0) return dummy.next;
        //最后剩余节点小于k,单独处理,注意正序栈中元素
        ListNode tail = stack.pop();
        while (!stack.empty()) {
            ListNode right = stack.pop();
            right.next = tail;
            tail = right;
        }
        nextNode.next = tail;
        return dummy.next;
    }


    /**
     * 不适用栈，直接反转链表
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next=head;
        ListNode pre = dummy;
        ListNode end = dummy;//记录每次累计到k，需要反转的位置
        while (end.next != null) {
            //当k个节点时，需要反转
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            //记录下次开始的位置
            ListNode next = end.next;
            //需要反转开始的位置
            ListNode start = pre.next;
            //切断后续联系
            end.next = null;
            //反转
            pre.next = reverse(start);
            start.next=next;
            pre=start;
            end=pre;
        }

        return dummy.next;
    }


    private ListNode reverse(ListNode start) {

        ListNode pre = null;
        ListNode curr=start;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        ListNode n1 = ListNodeUtil.convert2ListNode(1, 2, 3, 4, 5, 6, 7, 8, 9);
        ListNode result = reverseKGroup2(n1, 4);

        ListNodeUtil.print(result);

    }

}
