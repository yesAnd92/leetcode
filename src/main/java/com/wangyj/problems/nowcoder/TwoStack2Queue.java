package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.Stack;

public class TwoStack2Queue {


    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {

        //stack1负责push,push之前，确保stack2中无元素
        while (!stack2.isEmpty()) {
            //转移到stack1
            stack1.push(stack2.pop());
        }
        stack1.push(node);

    }

    public int pop() {

        //stack2负责pop,pop之前，确保stack1中无元素
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }


    @Test
    public void test(){
        push(1);
        push(2);
        push(3);

        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());

    }
}
