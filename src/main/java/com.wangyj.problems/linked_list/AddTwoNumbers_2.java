package com.wangyj.problems.linked_list;

import org.junit.Test;
/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 * @author W.Y.J
 * @Date 2021/5/8 21:11
 */
public class AddTwoNumbers_2 {


    /**
     * 自定义链表结构
     */
    public  class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode head  =result;
        //对照位值相加，考虑进位问题
        int carry = 0;
        while (l1!= null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = carry + v1 + v2;
            carry=sum/10;
            ListNode one = new ListNode(sum%10);
            result.next = one;
            result = one;
            //判空
            l1=l1==null?null:l1.next;
            l2=l2==null?null:l2.next;
        }
        //处理最后一次进位
        if (carry>0){
            ListNode one = new ListNode(1);
            result.next = one;
        }
        return head.next;
    }


    @Test
    public void test(){
        ListNode l1 = new ListNode(2);
        ListNode one1 = new ListNode(4);
        ListNode two1 = new ListNode(3);
        l1.next=one1;

        ListNode l2 = new ListNode(5);
        ListNode one2 = new ListNode(6);
        ListNode two2 = new ListNode(4);
        l2.next=one2;
        one2.next=two2;
        ListNode result = addTwoNumbers(l1, l2);
        while (result!=null){
            System.out.println(result.val);
            result=result.next;
        }
    }

}
