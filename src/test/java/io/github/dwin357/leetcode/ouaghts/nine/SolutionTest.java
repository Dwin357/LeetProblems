package io.github.dwin357.leetcode.ouaghts.nine;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int given = 121;
        boolean expected = true;

        Solution tested = new Solution();

        boolean actual = tested.isPalindrome(given);

        assertEquals(expected, actual);
    }

    @Test
    public void example_2() {
        int given = -121;
        boolean expected = false;

        Solution tested = new Solution();

        boolean actual = tested.isPalindrome(given);

        assertEquals(expected, actual);
    }

    @Test
    public void example_3() {
        int given = 10;
        boolean expected = false;

        Solution tested = new Solution();

        boolean actual = tested.isPalindrome(given);

        assertEquals(expected, actual);
    }

    @Test
    public void example_4() {
        int given = -101;
        boolean expected = false;

        Solution tested = new Solution();

        boolean actual = tested.isPalindrome(given);

        assertEquals(expected, actual);
    }

    @Test
    public void custom_1() {
        int given = 12321;
        boolean expected = true;

        Solution tested = new Solution();

        boolean actual = tested.isPalindrome(given);

        assertEquals(expected, actual);
    }

    @Test
    public void custom_2() {
        int given = 123321;
        boolean expected = true;

        Solution tested = new Solution();

        boolean actual = tested.isPalindrome(given);

        assertEquals(expected, actual);
    }
}