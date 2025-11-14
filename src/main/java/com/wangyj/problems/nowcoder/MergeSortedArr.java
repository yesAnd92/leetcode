package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class MergeSortedArr {


    public void merge(int A[], int m, int B[], int n) {

        int i = m - 1; //A遍历指针
        int j = n - 1;//B遍历指针
        int k = m + n - 1;//A数组队尾指针


        while (i >= 0 && j >= 0) {

            if (A[i] > B[j]) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }

            k--;
        }

        if (j >= 0) {
            while (k >= 0) {
                A[k--] = B[j--];
            }
        }
        for (int e : A) {
            System.out.print(e + " ");

        }
    }


    //没通过，不要在一个循环体内解决
    public void merge2(int A[], int m, int B[], int n) {

        int i = m - 1; //A遍历指针
        int j = n - 1;//B遍历指针
        int k = m + n - 1;//A数组队尾指针


        while (k != 0) {
            if (j < 0)
                break;
            if (i < 0) {
                while (k >= 0) {
                    A[k--] = B[j--];
                }
                break;
            }
            if (A[i] > B[j]) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }

            k--;
        }
        for (int e : A) {
            System.out.print(e + " ");

        }
    }

    @Test
    public void test() {
//        int A[] = new int[]{4, 5,6, 0, 0, 0};
//        int B[] = new int[]{1, 2, 3};
//        merge(A, 3, B, 3);

        int A[] = new int[]{0};
        int B[] = new int[]{1};
        merge(A, 0, B, 1);
    }
}
