package io.github.dwin357.leetcode.twenties.twoOne;


import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        ListNode given_a = buildList(1,2,4);
        ListNode given_b = buildList(1,3,4);
        ListNode expected = buildList(1,1,2,3,4,4);

        Solution tested = new Solution();

        ListNode actual = tested.mergeTwoLists(given_a,given_b);

        assertEqual(expected,actual);
    }

    @Test
    public void example_2() {
        ListNode given_a = buildList();
        ListNode given_b = buildList();
        ListNode expected = buildList();

        Solution tested = new Solution();

        ListNode actual = tested.mergeTwoLists(given_a,given_b);

        assertEqual(expected,actual);
    }

    @Test
    public void example_3() {
        ListNode given_a = buildList();
        ListNode given_b = buildList(0);
        ListNode expected = buildList(0);

        Solution tested = new Solution();

        ListNode actual = tested.mergeTwoLists(given_a,given_b);

        assertEqual(expected,actual);
    }

    @Test
    public void self_1() {
        ListNode given_a = buildList(0);
        ListNode given_b = buildList();
        ListNode expected = buildList(0);

        Solution tested = new Solution();

        ListNode actual = tested.mergeTwoLists(given_a,given_b);

        assertEqual(expected,actual);
    }

    /*
     * fails b/c has gets 0,1,2,3,5
     */
    @Test
    public void self_2() {
        ListNode given_a = buildList(1,2,4);
        ListNode given_b = buildList(0,3,5);
        ListNode expected = buildList(0,1,2,3,4,5);

        Solution tested = new Solution();

        ListNode actual = tested.mergeTwoLists(given_a,given_b);

        assertEqual(expected,actual);
    }
    /*
     * fails for missing 5
     */
    @Test
    public void self_3() {
        ListNode given_a = buildList(1,2,5);
        ListNode given_b = buildList(0,3,4);
        ListNode expected = buildList(0,1,2,3,4,5);

        Solution tested = new Solution();

        ListNode actual = tested.mergeTwoLists(given_a,given_b);

        assertEqual(expected,actual);
    }


    private void assertEqual(ListNode expected, ListNode actual) {
        int i = 0;
        while(expected != null && actual != null) {
            assertEquals("at i:"+i,expected.val, actual.val);
            expected = expected.next;
            actual = actual.next;
            i++;
        }
        if(expected != null) {
            fail("actual missing:"+toString(expected));
        }
        if(actual != null) {
            fail("actual has extra:"+toString(actual));
        }
    }

    private String toString(ListNode list) {
        StringBuilder sb = new StringBuilder();
        while(list != null) {
            sb.append(list.val);
            sb.append(", ");
            list = list.next;
        }
        return sb.toString();
    }

    private ListNode buildList(int... nums) {
        if(nums.length == 0) {
            return null;
        }
        if(nums.length == 1) {
            return new ListNode(nums[0]);
        }
        ListNode previous = new ListNode(nums[nums.length-1]);
        for(int i=nums.length-2; i>=0; i--) {
            previous = new ListNode(nums[i], previous);
        }
        return previous;
    }
}