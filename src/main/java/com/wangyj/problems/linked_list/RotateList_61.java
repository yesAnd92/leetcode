package com.wangyj.problems.linked_list;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

/**
 * 旋转链表
 * https://leetcode-cn.com/problems/rotate-list/
 */
public class RotateList_61 {

    /**
     * 最容易想到的思路是k次移动之后，把被顶出的元素按照顺序移动队头再接上原有的队头即可。
     * 怎么找到找到这个位置呢？联想到利用快慢指针，找到切割位置。
     * @author W.Y.J
     * @Date 2021/5/16 23:43
     */
    public ListNode rotateRight(ListNode head, int k) {
        //特殊值判定
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;

        int len = 0;
        while (len < k && fast != null) {
            len++;
            fast = fast.next;
        }
        //如果fast为空，说明链表长度小于k，记录下链表长度，将k等价缩小到一次循环内
        if (fast == null) {
            k = k % len;
            //如果k==0，则实际上不用旋转，直接返回即可
            if (k == 0) {
                return head;
            }
            //重新先走k次，此时的k已经是和len取模的结果了
            fast = head;
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        //此时fast所在位置就是原来链表的最后一个节点
        fast.next=head;
        return newHead;
    }


    @Test
    public void test() {
        ListNode head = ListNodeUtil.convert2ListNode(1, 2, 3);
        ListNode result = rotateRight(head, 2000000000);
        ListNodeUtil.print(result);
    }
}
