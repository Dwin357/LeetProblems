package io.github.dwin357.leetcode.tens.oneTwo;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int given = 3;
        String expected = "III";
        Solution tested = new Solution();

        String actual = tested.intToRoman(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int given = 4;
        String expected = "IV";
        Solution tested = new Solution();

        String actual = tested.intToRoman(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int given = 9;
        String expected = "IX";
        Solution tested = new Solution();

        String actual = tested.intToRoman(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        int given = 58;
        String expected = "LVIII";
        Solution tested = new Solution();

        String actual = tested.intToRoman(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_5() {
        int given = 1994;
        String expected = "MCMXCIV";
        Solution tested = new Solution();

        String actual = tested.intToRoman(given);

        assertEquals(expected,actual);
    }
}