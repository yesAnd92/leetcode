package problems.Linked_list;

/* *
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing
 * together the nodes of the first two lists.
 * Example:
 *  Input: 1->2->4, 1->3->4
 *  Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists_21 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        mergeTwoLists(l1, null);
    }

    /**
     * 思路： 从两个列表里起始位置分别拿出一个元素进行大小比较，值较小的放入结果列表中，
     * 同时将值最小所在列表的游标右移，继续用下一个元素和另一个列表的元素进行比较，如此往复
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null&&l2==null) return null;
        ListNode result = new ListNode(0);
        ListNode t = result;  //记录结果列表的头个元素指针
        ListNode c1 = l1;// 遍历l1的游标
        ListNode c2 = l2;// 遍历l2的游标
        while (c1 != null || c2 != null) {
            if (c1 == null) {
                //l1已经遍历完了
                t.val = c2.val;
                t.next = c2.next;
                break;
            } else if (c2 == null) {
                //l2已经遍历完了
                t.val = c1.val;
                t.next = c1.next;
                break;
            } else {
                if (c1.val < c2.val) {
                    t.val = c1.val;//将较小值放入结果列表
                    c1 = c1.next; //较小值所在列表游标右移
                } else {
                    t.val = c2.val;
                    c2 = c2.next;
                }
                t.next = new ListNode(0);
                t = t.next;
            }

        }
        print(result);
        return result;
    }


    public static void print(ListNode node) {
        while (node!= null) {
            System.out.print(node.val + "  ");
            node = node.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
