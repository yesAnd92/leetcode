package com.wangyj.problems.stack;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * @author W.Y.J
 * @Date 2021/6/30 14:42
 * <p>
 * 第一种方法时，每次都将所有的数据集中到一个栈中，这个操作浪费性能，其实是没有必要的
 * 假如用其中一个栈作为出栈操作用，这个栈没必要每次都存放所有元素，可以存放部分元素（先进入的在栈的上方，保证先进先出）
 * 另一个栈作为进栈使用，当初出栈栈的元素为空时，再将进栈栈的元素转移过去即可，如此反复
 */
public class QueueUsingStacks02_232 {


    //入栈元素存放位置
    private Stack<Integer> in;
    //出栈时使用栈，灭有元素从in转移过俩
    private Stack<Integer> out;

    /**
     * Initialize your data structure here.
     */
    public QueueUsingStacks02_232() {
        //初始化栈
        in = new Stack<>();
        out = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (out.empty()){
            transferInToOut();
        }
        return out.pop();
    }



    /**
     * Get the front element.
     */
    public int peek() {
        if (out.empty()){
            transferInToOut();
        }
        return out.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return in.empty()&& out.empty();
    }

    /**
     * in栈的元素转移到out栈中
     */
    private void transferInToOut() {
        while (!in.empty()){
            out.push(in.pop());
        }
    }
}
