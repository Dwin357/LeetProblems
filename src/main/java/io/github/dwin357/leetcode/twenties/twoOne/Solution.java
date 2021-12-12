package io.github.dwin357.leetcode.twenties.twoOne;

public class Solution {
    /*
     * So conceptually this looks pretty straight fwd...
     * - look at the 2 heads, pick the lower as the combined-head
     * - then basically work on "ticks", where for each tick you look at
     * the current pointer of both lists, pick the lower, add it to the
     * combined list, and adv that pointer.  There is even an optimization
     * here, where if one of the lists is null you don't actually have to
     * tick through the whole thing --just append to the end.
     *
     * In fact, the real difficulty I see is all the conditional logic in terms
     * of setting up the starting state, and then the number of pointers (4?)
     * - head of combined (to return)
     * - tail of combined (to append to)
     * - head of A (to compare & append with)
     * - head of B (to compare & append with)
     *
     * ...and actually, I guess if I had 2 "if X==null rtn Y" covers
     * the edge case stuff then I can just do a straight comparison to set the head.
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
     * Memory Usage: 38.6 MB, less than 41.98% of Java online submissions for Merge Two Sorted Lists.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        ListNode head;
        if(list1.val > list2.val) {
            head = list2;
            list2 = list2.next;
        } else {
            head = list1;
            list1 = list1.next;
        }
        ListNode tail = head;
        // that feels like a lot of setup code

        // so, easy little optimization
        // b/c we need to ck both sides of this on each cycle, but only one side will update, cache the value
        boolean oneOut = list1 == null;
        boolean twoOut = list2 == null;

        /*
         * not a huge fan of this code...  it is very spaghetti
         */
        while(true) {
            if(oneOut) {
                tail.next = list2;
                break;
            }
            if(twoOut) {
                tail.next = list1;
                break;
            }

            if(list1.val < list2.val) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
                oneOut = list1 == null;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
                twoOut = list2 == null;
            }
        }

        return head;
    }
}
