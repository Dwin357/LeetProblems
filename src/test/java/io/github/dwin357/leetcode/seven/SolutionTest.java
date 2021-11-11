package io.github.dwin357.leetcode.seven;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int given = 123;
        int expected = 321;
        Solution tested = new Solution();

        int actual = tested.reverse(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int given = -123;
        int expected = -321;
        Solution tested = new Solution();

        int actual = tested.reverse(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int given = 120;
        int expected = 21;
        Solution tested = new Solution();

        int actual = tested.reverse(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        int given = 0;
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.reverse(given);

        assertEquals(expected,actual);
    }

    @Test
    public void implied_1() {
        int given = Integer.MIN_VALUE;
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.reverse(given);

        assertEquals(expected,actual);
    }

    @Test
    public void error_1() {
        int given = 1534236469;
//        int expected = 9646324351;
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.reverse(given);

        assertEquals(expected,actual);
    }
}