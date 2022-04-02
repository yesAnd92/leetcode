package com.wangyj.problems.fast_slow_pointer;


import com.wangyj.problems.common.ListNode;

/**
 * 环形链表 II,需要返回循环入口
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class LinkedListCycleII_142 {


    /**
     * 参考 https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     * 难点是，根据：
     * f=2s （快指针每次2步，路程刚好2倍）
     * f = s + nb (相遇时，刚好多走了n圈）
     * 推出：s = nb
     * 从head结点走到入环点需要走 ： a + nb， 而slow已经走了nb，那么slow再走a步就是入环点了。
     * 如何知道slow刚好走了a步？ 从head开始，和slow指针一起走，相遇时刚好就是a步
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head, slow = head;
            while (true) {
                if (fast == null || fast.next == null) return null;
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) break;
            }
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
    }

}
