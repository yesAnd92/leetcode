package com.wangyj.problems.linked_list;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

/* *
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing
 * together the nodes of the first two lists.
 * Example:
 *  Input: 1->2->4, 1->3->4
 *  Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists_21 {


    /**
     * 思路： 从两个列表里起始位置分别拿出一个元素进行大小比较，值较小的放入结果列表中，
     * 同时将值最小所在列表的游标右移，继续用下一个元素和另一个列表的元素进行比较，如此往复
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode result = new ListNode(0);
        ListNode t = result;  //记录结果列表的头个元素指针
        ListNode c1 = l1;// 遍历l1的游标
        ListNode c2 = l2;// 遍历l2的游标
        while (c1 != null || c2 != null) {
            if (c1 == null) {
                //l1已经遍历完了
                t.val = c2.val;
                t.next = c2.next;
                break;
            } else if (c2 == null) {
                //l2已经遍历完了
                t.val = c1.val;
                t.next = c1.next;
                break;
            } else {
                if (c1.val < c2.val) {
                    t.val = c1.val;//将较小值放入结果列表
                    c1 = c1.next; //较小值所在列表游标右移
                } else {
                    t.val = c2.val;
                    c2 = c2.next;
                }
                t.next = new ListNode(0);
                t = t.next;
            }

        }
        return result;
    }


    /*****************************************************优化，不引入额外的空间**************************************************/


    /**
     * 其实可以在原两个链表上直接进行比较，改变节点的下一个引用，实现拼接
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;

        //需要记录一个哑节点表示最后的结果链表的开始位置
        ListNode dummy = new ListNode(0);
        //表示结果链表中当前节点的位置
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                //当前节点的下个节点指向两个中较小的那个
                curr.next = l1;
                //较小的那个需要右移
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            //当前节点右移
            curr = curr.next;
        }
        //两个原始链表最后最多有一个没有遍历完，curr的下个节点直接未遍历完的链表
        curr.next = l1 == null ? l2 : l1;

        return dummy.next;
    }



    /*****************************************************递归**************************************************/


    /**
     * 这种递归的方式比较巧妙，不容易想到
     */
    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }
        if (l2==null){
            return l1;
        }

        if (l1.val<=l2.val){
            //选择两个链表找那个小的那个，它的next需要连接两个剩余节点的合并后的链表
            l1.next=mergeTwoLists3(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeTwoLists3(l2.next,l1);
            return l2;
        }

    }


    @Test
    public void test() {
        ListNode l1 = ListNodeUtil.convert2ListNode(1, 2, 3, 35);
        ListNode l2 = ListNodeUtil.convert2ListNode(4, 6, 12, 22, 25);
//        ListNodeUtil.print(mergeTwoLists(l1, l2));
//        System.out.println();
//        ListNodeUtil.print(mergeTwoLists2(l1, l2));
//        System.out.println();
        ListNodeUtil.print(mergeTwoLists3(l1, l2));
    }

}

