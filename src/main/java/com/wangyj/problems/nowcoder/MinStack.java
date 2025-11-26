package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.Stack;

/**
 * 对于栈来说，如果一个元素 a 在入栈时，栈里有其它的元素 b, c, d，那么无论这个栈在之后经历了什么操作，只要 a 在栈中，b, c, d 就一定在栈中，因为在 a 被弹出之前，b, c, d 不会被弹出。
 * <p>
 * 因此，在操作过程中的任意一个时刻，只要栈顶的元素是 a，那么我们就可以确定栈里面现在的元素一定是 a, b, c, d。
 * <p>
 * 那么，我们可以在每个元素 a 入栈时把当前栈的最小值 m 存储起来。在这之后无论何时，如果栈顶元素是 a，我们就可以直接返回存储的最小值 m。
 */
public class MinStack {

    //主栈
    private Stack<Integer> stack = new Stack<>();

    //辅助栈，用于存放对应主栈不同时期的最小值
    private Stack<Integer> minStack = new Stack<>();


    //
    public void push(int node) {
        stack.push(node);

        if (minStack.empty()) {
            //首次添加
            minStack.push(node);
        } else if (minStack.peek() > node) {
            minStack.push(node);
        } else {
            //最小值要
            minStack.push(minStack.peek());
        }

    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }


    @Test
    public void test() {


        MinStack obj = new MinStack();
        obj.push(10);
        obj.push(4);
        System.out.println(obj.min());
        obj.push(5);
        obj.push(1);
        System.out.println(obj.top());
        System.out.println(obj.min());
        obj.pop();

    }
}
