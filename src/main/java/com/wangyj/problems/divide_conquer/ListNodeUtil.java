package com.wangyj.problems.divide_conquer;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ListNodeUtil {

    public static ListNode convert2ListNode(List<Integer> list ){
        ListNode dummyHead =new ListNode(); //创建一个哑的首节点
        ListNode nextNode; //声明一个变量用来在移动过程中指向当前节点
        nextNode=dummyHead; //指向首节点
        //创建链表
        for (Integer val : list) {
            nextNode.next=new ListNode(val);
            nextNode=nextNode.next;
        }
        return dummyHead.next;
    }

    public static ListNode convert2ListNode(Integer... ele){
        List<Integer> list =new ArrayList<>();
        Collections.addAll(list,ele);
        ListNode dummyHead =new ListNode(); //创建一个哑的首节点
        ListNode nextNode; //声明一个变量用来在移动过程中指向当前节点
        nextNode=dummyHead; //指向首节点
        //创建链表
        for (Integer val : list) {
            nextNode.next=new ListNode(val);
            nextNode=nextNode.next;
        }
        return dummyHead.next;
    }


    //打印输出方法
    public static void print(ListNode listNoed){
        //创建链表节点
        while(listNoed!=null){
            System.out.print(listNoed.val+"  ");
            listNoed=listNoed.next;
        }
    }
}