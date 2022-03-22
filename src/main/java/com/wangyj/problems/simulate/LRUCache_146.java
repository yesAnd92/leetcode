package com.wangyj.problems.simulate;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用缓存
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * @author W.Y.J
 * @Date 2022/3/21 16:28
 */
public class LRUCache_146 {

    /**
     * 定义一个双向链表
     */
    class Node {
        private Node pre, next;
        private int key, val;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleNodeList {
        Node head, tail;
        int size;

        public DoubleNodeList() {
            //两个虚拟的节点
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }
        /**
         * 队尾增加节点，被使用多的节点放到最后
         */
        public void addLast(Node node) {
            node.pre = tail.pre;
            node.next = tail;
            tail.pre.next = node;
            tail.pre = node;
            size++;
        }

        /**
         * 移除一个节点
         */
        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }
    }


    DoubleNodeList nodeList ;
    Map<Integer,Node> nodeMap;
    private int capacity;

    public LRUCache_146(int capacity) {
        this.nodeList= new DoubleNodeList();
        nodeMap=new HashMap<>(capacity);
        this.capacity=capacity;
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)){
            return -1;
        }
        Node node = nodeMap.get(key);
        //获取过key的值，将节点移到链表最后
        nodeList.remove(node);
        nodeList.addLast(node);
        return node.val;
    }

    public void put(int key, int value) {

        if(nodeMap.containsKey(key)){
            //更新操作
            Node node = nodeMap.get(key);
            node.val=value;
            nodeList.remove(node);
            nodeList.addLast(node);
        }else {
            //不存在需要新增
            Node node = new Node(key, value);
            nodeMap.put(key,node);
            nodeList.addLast(node);
            if(nodeList.size>capacity){
                //容器已满,移除头结点
                Node firstNode = nodeList.head.next;
                nodeList.remove(firstNode);
                nodeMap.remove(firstNode.key);
            }

        }
    }


    @Test
    public void test() {
        DoubleNodeList nodeList = new DoubleNodeList();
        nodeList.addLast(new Node(1, 1));
        nodeList.addLast(new Node(2, 2));
        nodeList.addLast(new Node(3, 3));
        printNodeList(nodeList);
        nodeList.remove(nodeList.head.next);
        printNodeList(nodeList);
    }


    private void printNodeList(DoubleNodeList nodeList) {
        Node curr = nodeList.head.next;
        for (int i = 0; i < nodeList.size; i++) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
