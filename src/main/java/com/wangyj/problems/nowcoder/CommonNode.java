package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;

public class CommonNode {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        ListNode p1 = pHead1;
        ListNode p2 = pHead2;

        //两种情况会退出循环：p1、p2相等即第一个公共节点、p1 p2都为null
        while (p1 != p2) {
            //p1遍历完pHead1之后遍历pHead2
            if (p1 != null) {
                p1 = p1.next;
            } else {
                p1 = pHead2;
            }

            if (p2 != null) {
                p2 = p2.next;
            } else {
                p2 = pHead1;
            }
        }

        return p1;
    }
}
