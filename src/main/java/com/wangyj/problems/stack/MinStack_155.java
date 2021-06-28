package com.wangyj.problems.stack;


import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/min-stack/
 */
public class MinStack_155 {

    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack_155() {
        xStack = new LinkedList<Integer>();
        //需要注意的就是当移除栈顶元素时最小元素重新赋值的问题，使用一个辅助栈进行解题
        minStack = new LinkedList<Integer>();
        //首先放入最大值作为栈底元素
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        //压入xStack后，同步也将当前最小值压入辅助栈minStack，这相当于把每个元素入栈时的快照都保留下来，
        // 当xStack栈顶元素移除时，同时移除对minStack栈顶元素，那么minStack栈顶元素就是xStack中最小值
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
