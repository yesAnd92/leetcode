package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class Fibonacci {


    public int Fibonacci(int n) {
        // write code here
        if (n == 1 || n == 2) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    @Test
    public void test() {
        System.out.println(Fibonacci(4));
    }
}
