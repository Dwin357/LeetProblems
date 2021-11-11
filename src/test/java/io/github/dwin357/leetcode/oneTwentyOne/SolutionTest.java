package io.github.dwin357.leetcode.oneTwentyOne;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int[] given = new int[]{7,1,5,3,6,4};
        int expected = 5;

        Solution tested = new Solution();

        int actual = tested.maxProfit(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[]{7,6,4,3,1};
        int expected = 0;

        Solution tested = new Solution();

        int actual = tested.maxProfit(given);

        assertEquals(expected,actual);
    }

    @Test
    public void edgecase_1() {
        int[] given = new int[]{5,10,1,2,3};
        int expected = 5;

        Solution tested = new Solution();

        int actual = tested.maxProfit(given);

        assertEquals(expected,actual);
    }
}