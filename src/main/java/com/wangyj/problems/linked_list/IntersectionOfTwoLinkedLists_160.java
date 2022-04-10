package com.wangyj.problems.linked_list;


import com.wangyj.problems.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表相交
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists_160 {


    /**
     * 需要借助一个集合，不太符合题意
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //特判
        if (headA == null || headB == null)
            return null;

        ListNode a = headA;
        ListNode b = headB;

        Set<ListNode> set = new HashSet<>();
        while (a != null || b != null) {
            //提前相遇直接退出
            if (a == b)
                return a;

            if (a != null) {
                if (set.contains(a)) {
                    return a;
                }
                set.add(a);
                a = a.next;
            }

            if (b != null) {
                if (set.contains(b)) {
                    return b;
                }
                set.add(b);
                b = b.next;
            }
        }

        return null;
    }


    /**
     * 空间复杂度为O(1)，这个方法比较难想到，学习记忆
     * 若相交，设相交之后的节点数量为c
     * 相交前，链表A的长度为a；链表B的长度为b
     * 则链表A： a+c, 链表B : b+c.
     * 两个链表取对方的链表拼接到自己后边则有：a+c+b+c = b+c+a+c 。
     * 若相交因为a+c+b=b+c+a，所以必然会在公共处c起点相遇。
     * 若不相交，由于等长，最终肯定是遍历到最后的null
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        //特判
        if (headA == null || headB == null)
            return null;

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            if (a == b)
                return a;
            //自己链表遍历完拼接对方链表
            a = a.next == null ? headB : a.next;
            b = b.next == null ? headA : b.next;
        }

        return a;
    }

}
