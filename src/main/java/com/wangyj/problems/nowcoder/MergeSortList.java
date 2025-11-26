package com.wangyj.problems.nowcoder;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

public class MergeSortList {

    public ListNode Merge(ListNode pHead1, ListNode pHead2) {
        // write code here

        ListNode pre = new ListNode();
        ListNode curr = pre;
        ListNode curr1 = pHead1;
        ListNode curr2 = pHead2;

        while (curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }

        //curr1还剩余
        if (curr1!=null){
            curr.next=curr1;
        }

        //curr2还剩余
        if (curr2!=null){
            curr.next=curr2;
        }
        return pre.next;
    }

    @Test
    public void test() {
        ListNode pHead;
        int[] n1 = new int[]{1, 3, 5, 6};
        int[] n2 = new int[]{2, 4, 7, 9};

        ListNode pHead1 = arr2ListNode(n1);
        ListNode pHead2 = arr2ListNode(n2);
//        ListNode merge = Merge(pHead1, pHead2);
        ListNode merge = Merge(null, pHead2);


        ListNodeUtil.print(merge);

    }

    public ListNode arr2ListNode(int[] nums) {
        ListNode pre = new ListNode();
        ListNode curr = pre;
        for (int num : nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return pre.next;
    }
}
