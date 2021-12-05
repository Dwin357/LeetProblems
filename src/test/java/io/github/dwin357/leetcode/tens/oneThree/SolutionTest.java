package io.github.dwin357.leetcode.tens.oneThree;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        String given = "III";
        int expected = 3;
        Solution tested = new Solution();

        int actual = tested.romanToInt(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        String given = "IV";
        int expected = 4;
        Solution tested = new Solution();

        int actual = tested.romanToInt(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        String given = "IX";
        int expected = 9;
        Solution tested = new Solution();

        int actual = tested.romanToInt(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        String given = "LVIII";
        int expected = 58;
        Solution tested = new Solution();

        int actual = tested.romanToInt(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_5() {
        String given = "MCMXCIV";
        int expected = 1994;
        Solution tested = new Solution();

        int actual = tested.romanToInt(given);

        assertEquals(expected,actual);
    }
}