package com.wangyj.problems.linked_list;

import com.wangyj.problems.common.ListNode;
import com.wangyj.problems.common.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList_234 {


    /**
     * 转换到列表中，双指针向中间遍历对比
     */
    public boolean isPalindrome(ListNode head) {

        List numsList = new ArrayList();
        ListNode curr = head;
        while (curr != null) {
            numsList.add(curr.val);
            curr = curr.next;
        }

        int size = numsList.size();
        int left = 0, right = size - 1;
        while (left <= right) {
            if (numsList.get(left++) != numsList.get(right--)) {
                return false;
            }
        }
        return true;
    }


    /*****************************************************以下为私有方法**************************************************/


    /**
     * 上边的算法空间复杂度是O(n)，要想达到O(1),只能在链表本身动手脚
     * 一个思路是：
     * 找到链表的中间位置，然后将后半部分的链表反转，再进行比较
     * 找中间的位置，可以先遍历数链表长度，再找到中间位置
     * 或者使用快慢指针，快每次移动两次，慢移动一次，快走到链表尾时，慢走到中间
     */
    public boolean isPalindrome2(ListNode head) {

        ListNode slow = head, fast = head;
        while (fast != null) {
            //慢指针走一次
            slow = slow.next;
            //快指针走两次
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        //从slow位置开始反转链表
        ListNode reverseHead = reverseListNode(slow);
        fast =head;
        slow=reverseHead;
        while (slow!=null){
            if (slow.val!=fast.val){
                return false;
            }
            slow=slow.next;
            fast=fast.next;
        }
        return true;
    }

    public ListNode reverseListNode(ListNode head) {

        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextNode;
        }
        return pre;
    }


    @Test
    public void test() {
        ListNode listNode = ListNodeUtil.convert2ListNode(1,2);
        System.out.println(isPalindrome2(listNode));

//        ListNodeUtil.print(reverseListNode(listNode));
    }
}
