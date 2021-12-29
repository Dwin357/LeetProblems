package io.github.dwin357.leetcode.thirties.threeFive;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void example_1() {
        int[] given = new int[]{1,3,5,6};
        int target = 5;
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.searchInsert(given,target);

        assertEquals(expected,actual);
    }
    @Test
    public void example_2() {
        int[] given = new int[]{1,3,5,6};
        int target = 2;
        int expected = 1;
        Solution tested = new Solution();

        int actual = tested.searchInsert(given,target);

        assertEquals(expected,actual);
    }
    @Test
    public void example_3() {
        int[] given = new int[]{1,3,5,6};
        int target = 7;
        int expected = 4;
        Solution tested = new Solution();

        int actual = tested.searchInsert(given,target);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_1() {
        int[] given = new int[]{1,3,5,6};
        int target = 0;
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.searchInsert(given,target);

        assertEquals(expected,actual);
    }
}