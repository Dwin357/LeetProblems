package io.github.dwin357.leetcode.tens.oneNine;

public class Solution {

    /*
     * So the initial pass around advancing runner, advancing 2 pointers in tandom, & stitching list back together
     * works for example 1 & 3.
     *
     * The 2 remaining edge cases are...
     * - what if n is 1 (in which case target.next.next will give a null pointer
     * - what if n is length of list (in which case I want to return tail instead of head)
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
     * Memory Usage: 37.2 MB, less than 40.10% of Java online submissions for Remove Nth Node From End of List.
     * ...Sadly I looked at the graph on this one "73% of ppl" are in the sub-1ms range
     * these results are not as impresive as they first appear
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // extend a runner the designated distance ahead of process node
        ListNode runner = head;
        for(int i=n; i > 0; i--) {
            runner = runner.next;
        }

        // guard edge case of cutting first node in list
        if(runner == null) {
            return head.next;
        }

        // advance runner and target node in tandum until runner his end of list
        ListNode target = head;
        while(runner.next != null) {
            runner = runner.next;
            target = target.next;
        }

        // stitch list back together around cut node (guarding edge case of cutting last node in list)
        if(n == 1) {
            target.next = null;
        } else {
            target.next = target.next.next;
        }

        // return original head of list
        return head;
    }
}
