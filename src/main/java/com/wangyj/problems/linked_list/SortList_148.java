package com.wangyj.problems.linked_list;


import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * https://leetcode-cn.com/problems/sort-list/
 */
public class SortList_148 {
    public ListNode sortList(ListNode head) {

        ListNode ans = new ListNode(-1);
        ListNode ansCurr = ans;

        ListNode curr = head;

        ListNode preNode = null;
        //维护一个map记录当前节点的上一个节点
        Map<ListNode, ListNode> map = new HashMap<>();
        while (curr != null) {
            map.put(curr, preNode);
            preNode = curr;
            curr = curr.next;
        }

        while (head != null) {
            //重置
            curr = head;
            ListNode minNode = head;

            //找到当前链表中的最小值
            while (curr != null) {
                if (curr.val < minNode.val) {
                    minNode = curr;
                }
                curr = curr.next;
            }

            //取出最小值的上一个节点
            ListNode pre = map.get(minNode);
            if (pre == null) {
                //头结点是最小元素,新的头是minNode的下一个元素
                head = minNode.next;
                map.put(head,null);
            } else {
                //最小节点的上一个节点连接最小节点的下一个节点
                pre.next = minNode.next;
                //修改map关系
                map.put(minNode.next,pre);
            }
            //这次遍历的最小值放到结果中
            ansCurr.next=minNode;
            ansCurr=ansCurr.next;
        }
        return ans.next;
    }


    @Test
    public void test() {
        ListNode listNode = sortList(ListNodeUtil.convert2ListNode());
        ListNodeUtil.print(listNode);
    }

}
