package io.github.dwin357.leetcode.eight;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void example_1() {
        String given = "42";
        int expected = 42;

        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_2() {
        String given = "   -42";
        int expected = -42;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_3() {
        String given = "4193 with words";
        int expected = 4193;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_4() {
        String given = "words and 987";
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_5() {
        String given = "-91283472332";
        int expected = Integer.MIN_VALUE;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void custom_1() {
        String given = "91283472332";
        int expected = Integer.MAX_VALUE;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void custom_2() {
        String given = "91283 472332";
        int expected = 91283;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
    @Test
    public void custom_3() {
        String given = "91283+472332";
        int expected = 91283;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }

    @Test
    public void custom_4() {
        String given = "-2147483648";
        int expected = -2147483648;
        Solution tested = new Solution();

        int actual = tested.myAtoi(given);

        assertEquals(expected,actual);
    }
}