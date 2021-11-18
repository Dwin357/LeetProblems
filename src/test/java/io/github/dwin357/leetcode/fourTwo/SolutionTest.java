package io.github.dwin357.leetcode.fourTwo;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int[] heights = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int expected = 6;
        Solution tested = new Solution();

        int actual = tested.trap(heights);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] heights = new int[]{4,2,0,3,2,5};
        int expected = 9;
        Solution tested = new Solution();

        int actual = tested.trap(heights);

        assertEquals(expected,actual);
    }

    @Test
    public void custom_1() {
        int[] heights = new int[]{4};
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.trap(heights);

        assertEquals(expected,actual);
    }
}