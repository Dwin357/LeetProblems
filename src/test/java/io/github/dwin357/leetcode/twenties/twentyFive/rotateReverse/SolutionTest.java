package io.github.dwin357.leetcode.twenties.twentyFive.rotateReverse;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        ListNode given = buildList(1,2,3,4,5);
        int k = 2;
        ListNode expected = buildList(2,1,4,3,5);

        Solution tested = new Solution();
        ListNode actual = tested.reverseKGroup(given, k);

        assertEqual(expected,actual);
    }

    @Test
    public void example_2() {
        ListNode given = buildList(1,2,3,4,5);
        int k = 3;
        ListNode expected = buildList(3,2,1,4,5);

        Solution tested = new Solution();
        ListNode actual = tested.reverseKGroup(given, k);

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