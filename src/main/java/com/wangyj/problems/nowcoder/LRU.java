package com.wangyj.problems.nowcoder;

import java.util.HashMap;

public class LRU {

    public static class Node {
        private Integer key;
        private Integer value;
        private Node next;
        private Node pre;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

    }

    private int capacity;
    private HashMap<Integer, Node> store;
    private Node head;
    private Node tail;


    public LRU(int capacity) {
        // write code here
        this.capacity = capacity;
        store = new HashMap<>(capacity);
        // 使用虚拟头尾节点简化边界处理
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;

    }

    public int get(int key) {
        // write code here
        if (!store.containsKey(key)) {
            return -1;
        }

        //如果key存在，更新值，并把该节点移动到链表头部
        Node keyNode = store.get(key);

        moveNodeToHead(keyNode);
        return keyNode.value;
    }

    public void set(int key, int value) {

        //如果key存在，更新值，并把该节点移动到链表头部
        if (store.containsKey(key)) {
            Node keyNode = store.get(key);
            keyNode.value = value;
            moveNodeToHead(keyNode);
        } else {

            if (store.size() >= capacity) {
                //超容，先淘汰链表尾部数据
                removeLastNode();
            }

            Node newNode = addToHead(key, value);
            store.put(key, newNode);
        }

    }

    private Node addToHead(int key, int value) {
        Node newNode = new Node(key, value);

        newNode.next = head.next;
        newNode.pre = head;
        head.next = newNode;
        newNode.next.pre = newNode;
        return newNode;
    }

    private void removeLastNode() {

        //tail.pre才是最后一个节点
        Node lastNode = tail.pre;
        if (lastNode == head) {
            return;
        }

        //移除map中元素
        store.remove(lastNode.key);
        lastNode.pre.next = tail;
        tail.pre = lastNode.pre;
    }

    private void moveNodeToHead(Node keyNode) {
        //摘掉旧节点
        keyNode.pre.next=keyNode.next;
        keyNode.next.pre=keyNode.pre;

        //放到头结点
        keyNode.next = head.next;
        keyNode.pre = head;
        head.next = keyNode;
        keyNode.next.pre = keyNode;
    }


    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.set(1, 1);
        lru.set(2, 2);
        lru.set(3, 3);
        lru.get(1);
        lru.set(4, 4);
        lru.set(4, -4);
        System.out.println();
    }
}
