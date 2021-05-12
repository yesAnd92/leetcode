package com.wangyj.problems.linked_list;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 反转链表
 */
public class ReverseLinkedList_206 {



    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;//记录当前节点的上一个节点
        ListNode curr = head;
        while (curr != null) {
            ListNode temNode = curr.next;  //临时记下一个节点
            curr.next = prev;     //将当前节点的一下个节点指向上一个节点 从而实现翻转
            //pre指针右移，指向下一个节点，当前节点也指向下一个节点,遍历下一个元素
            prev = curr;
            curr = temNode;
        }
        return prev;
    }

    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5);
        ListNode l1 = ListNodeUtil.convert2ListNode(list);
        ListNodeUtil.print(reverseList(l1));
    }
}
