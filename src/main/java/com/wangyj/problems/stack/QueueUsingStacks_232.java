package com.wangyj.problems.stack;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * @author W.Y.J
 * @Date 2021/6/30 14:42
 * <p>
 * 题目中已经要求使用两个栈实现队列(其实也算是提示)
 * 为了保证先进先出，每次添加新的元素时都需要将最新加入的元素都放到栈底
 * 其他元素的位置顺序保持不变，调整顺序需要一个辅助栈
 */
public class QueueUsingStacks_232 {


    //真实存放数据的栈
    private Stack<Integer> main;
    //交换时使用的辅助栈
    private Stack<Integer> auxiliary;

    /**
     * Initialize your data structure here.
     */
    public QueueUsingStacks_232() {
        //初始化栈
        main = new Stack<>();
        auxiliary = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        //先将main栈中的元素挨个取出放到auxiliary栈中
        while (!main.empty()) {
            auxiliary.push(main.pop());
        }
        //新加入的元素放到main栈底
        main.push(x);
        //重新将auxiliary栈数据取出放回（反着入auxiliary栈一次，再反着出auxiliary栈一次，保持原有顺序）
        while (!auxiliary.empty()) {
            main.push(auxiliary.pop());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return main.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return main.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return main.empty();
    }
}
