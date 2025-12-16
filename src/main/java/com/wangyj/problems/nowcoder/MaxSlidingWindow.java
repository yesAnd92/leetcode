package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MaxSlidingWindow {


    /**
     * 这个题的想要实现功能并不难，无非是每次窗口中的最大值求出来，但是每次都算一遍最大值，时间会超时
     * <p>
     * 每次窗口滑动，都新增一个元素同时减少一个元素，其实只需要让上个窗口的最大值和新增增元素比比大小，就可以了，但是减少的元素存在刚好是上个窗口的最大值
     * 这个时候，我们就要保证存下最大值 第二大的值，保证最大值最划走之后 不用遍历去找
     * <p>
     * 那我存两个变量，最大值 第二大值，每次比较厚更新这两个值能行不，不可以！因为假如划掉的元素是第二大值，需要第三大值来补位，我们并没有这个值。。。
     * <p>
     * 所以想到，维护一个队列，每次遍历到一个新值时，删除队列中所有比这个值小的值，因为这个值入队之后，所有比这个值小的，都没有机会成为我们需要的最大值了
     * 另外，不管最新遍历到的这个值大小如何，都要加入队列，因为这个值有可能是后面窗口的最大值。
     * <p>
     */


    /**
     * 本实现用了优先级队列，队列存储的是最大值，但是调试过程中发现，需要将已经从窗口滑出的值从优先级队列中移除
     * 也就是说需要将下标也存储起来，那这种实现并无优势
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        // write code here

        ArrayList<Integer> re = new ArrayList<>();
        int length = num.length;
        if (length < size || size == 0) {
            return re;
        }

        //构建一个大顶堆，最大值在队首
        PriorityQueue<Ele> queue = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);

        //先构建第一个窗口到队列中
        for (int i = 0; i < size; i++) {
            queue.add(new Ele(num[i], i));
        }

        //第一个窗口的最大值
        re.add(queue.peek().val);

        for (int i = size; i < length; i++) {

            //移除掉队列中不在窗口内的元素
            while (!queue.isEmpty() && queue.peek().idx < i + 1 - size) {
                queue.poll();
            }
            queue.add(new Ele(num[i], i));

            re.add(queue.peek().val);
        }


        return re;

    }


    public class Ele {
        int val;
        int idx;

        public Ele(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }


    /**
     *
     * 1. 难易理解
     * 优先级队列
     * 思路：非常直观。滑动窗口要找最大值，那我就用一个“大顶堆”来存放窗口里的所有元素，堆顶自然就是当前窗口的最大值。
     * 优点：符合直觉，容易想到。
     * 难点：如何处理窗口的滑动？ 当窗口向右移动时，左边的元素需要被移除。但优先级队列（标准库实现）只提供“移除堆顶元素”的操作，无法高效地移除堆中间或底部的那个“过期”元素。
     * 笨办法：你可以用一个额外的数据结构（如哈希表）来标记哪些元素已经过期，然后在每次取堆顶元素时，检查它是否过期，如果过期就弹出并继续检查下一个。
     * 理解成本：这个“惰性删除”的策略增加了实现的复杂度和理解的难度。你需要维护两个数据结构，并处理它们之间的同步问题。
     *
     * 双端队列
     * 思路：相对不那么直观，但非常巧妙。双端队列中不直接存储窗口的元素值，而是存储这些元素的索引。
     * 并且，队列中索引对应的元素值在原数组中始终保持单调递减（求最大值）或单调递增（求最小值）。
     * 优点：一旦理解了“维护单调性”这个核心思想，代码实现会非常简洁和优雅。它将“窗口滑动”和“求最值”这两个操作完美地融合在了一起。
     * 难点：理解“为什么”要这么做是关键。
     * 为什么存索引？ 为了方便判断队列头的元素是否已经滑出窗口（queue[0] <= i - k）。
     * 为什么维护单调性？ 因为如果一个新元素比队列尾部的元素大（求最大值时），那么队列尾部的那个小元素就“永无出头之日”了，在它过期之前，新来的这个大元素永远是最大值。
     * 所以可以直接把它从队列尾部移除，这个操作保证了队列头永远是当前窗口的最大值。
     * 理解成本：初学者需要花点时间来消化这个“单调队列”的抽象概念，但一旦理解，就会觉得其设计之精妙。
     */
    public ArrayList<Integer> maxInWindows2(int[] num, int size) {
        ArrayList<Integer> maxWindows = new ArrayList<>();
        // 特殊情况处理
        int length = num.length;
        if (length < size || size == 0) {
            return maxWindows;
        }

        //使用双端队列存储下标索引，既有索引，又能根据索引取值
        ArrayDeque<Integer> deque = new ArrayDeque();

        //第一个窗口的数据先入列
        for (int i = 0; i < size; i++) {

            while (!deque.isEmpty() && num[deque.peekFirst()] < num[i]) {
                //移除对应值比当前值小的下标
                deque.pollFirst();
            }
            deque.offerLast(i);
        }
        maxWindows.add(num[deque.peekFirst()]);

        //滑动
        for (int i = size; i < num.length; i++) {

            //注意这里是<= 和当前值相等的，但是早于当前值，在当前值进入后也没必要存在了
            //注意从队尾开始移除
            while (!deque.isEmpty() && num[deque.peekLast()] <= num[i]) {
                //移除对应值比当前值小的下标
                deque.pollLast();
            }
            //同时要移除已经滑出窗口的元素
            while (!deque.isEmpty() && deque.peekFirst() <i-size+1){
                deque.pollFirst();
            }
            deque.offerLast(i);

            //取出队头元素，即为当前窗口最大值
            maxWindows.add(num[deque.peekFirst()]);
        }


        return maxWindows;

    }


    @Test
    public void test() {
        int[] nums=new int[]{2,3,4,2,6,2,5,1};
        ArrayList<Integer> ints = maxInWindows2(nums, 3);
        System.out.println(ints);

        int[] nums2 = new int[]{1,2,3,4};
        ArrayList<Integer> ints2 = maxInWindows2(nums2, 5);
        System.out.println(ints2);

        int[] nums3 = new int[]{9, 10, 9, -7, -3, 8, 2, -6};
        ArrayList<Integer> ints3 = maxInWindows2(nums3, 5);
        System.out.println(ints3);
    }
}

//
//    public ArrayList<Integer> maxInWindows2(int[] num, int size) {
//        ArrayList<Integer> maxWindows = new ArrayList<>();
//        // 特殊情况处理
//        if (num.length == 0 || num == null || num.length < size) {
//            return maxWindows;
//        }
//
//        if (num.length >= size && size >= 1) {
//            // 用来保存可能是滑动窗口最大值的数字的下标
//            ArrayDeque<Integer> indexDeque = new ArrayDeque<>();
//
//            for (int i = 0; i < size; i++) {
//                // 如果已有数字小于待存入的数据，
//                // 这些数字已经不可能是滑动窗口的最大值
//                // 因此它们将会依次地从队尾删除
//                while (!indexDeque.isEmpty() && num[i] >= num[indexDeque.getLast()]) {
//                    indexDeque.pollLast();
//                }
//
//                indexDeque.addLast(i);
//            }
//
//            for (int i = size; i < num.length; i++) {
//                maxWindows.add(num[indexDeque.getFirst()]);
//                // 如果已有数字小于待存入的数据，
//                // 这些数字已经不可能是滑动窗口的最大值
//                // 因此它们将会依次地从队尾删除
//                while (!indexDeque.isEmpty() && num[i] >= num[indexDeque.getLast()]) {
//                    indexDeque.pollLast();
//                }
//                // 如果队列的头部元素已经从滑动窗口里滑出，滑出的数字需要从队列的头部删除
//                if (!indexDeque.isEmpty() && indexDeque.getFirst() <= (i - size)) {
//                    indexDeque.pollFirst();
//                }
//
//                indexDeque.addLast(i);
//            }
//            maxWindows.add(num[indexDeque.getFirst()]);
//        }
//        return maxWindows;
//
//    }