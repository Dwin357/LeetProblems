package io.github.dwin357.leetcode.oneOne;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int[] given = new int[] {1,8,6,2,5,4,8,3,7};
        int expected = 49;
        Solution tested = new Solution();

        int actual = tested.maxArea(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[] {1,1};
        int expected = 1;
        Solution tested = new Solution();

        int actual = tested.maxArea(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int[] given = new int[] {4,3,2,1,4};
        int expected = 16;
        Solution tested = new Solution();

        int actual = tested.maxArea(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        int[] given = new int[] {1,2,1};
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.maxArea(given);

        assertEquals(expected,actual);
    }
}