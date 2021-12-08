package io.github.dwin357.leetcode.tens.oneNine;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        ListNode given = buildList(1,2,3,4,5);
        int n = 2;

        ListNode expected = buildList(1,2,3,5);

        Solution tested = new Solution();

        ListNode actual = tested.removeNthFromEnd(given,n);

        assertEqual(expected,actual);
    }

    @Test
    public void example_3() {
        ListNode given = buildList(1,2);
        int n = 1;

        ListNode expected = buildList(1);

        Solution tested = new Solution();

        ListNode actual = tested.removeNthFromEnd(given,n);

        assertEqual(expected,actual);
    }

    @Test
    public void example_2() {
        ListNode given = buildList(1);
        int n = 1;

        ListNode expected = buildList();

        Solution tested = new Solution();

        ListNode actual = tested.removeNthFromEnd(given,n);

        assertEqual(expected,actual);
    }

    @Test
    public void test_177() {
        ListNode given = buildList(1);
        int n = 1;

        ListNode expected = buildList();

        Solution tested = new Solution();

        ListNode actual = tested.removeNthFromEnd(given,n);

        assertEqual(expected,actual);
    }

    @Test
    public void test_187() {
        ListNode given = buildList(1,2);
        int n = 2;

        ListNode expected = buildList(2);

        Solution tested = new Solution();

        ListNode actual = tested.removeNthFromEnd(given,n);

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
        assertNull("expected missing item at i:"+i, expected);
        assertNull("actual missing item at i:"+i, actual);
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