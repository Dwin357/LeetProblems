package io.github.dwin357.leetcode.tens.oneFour;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void example_1() {
        String[] given = new String[] {"flower","flow","flight"};
        String expected = "fl";
        Solution tested = new Solution();

        String actual = tested.longestCommonPrefix(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_2() {
        String[] given = new String[] {"dog","racecar","car"};
        String expected = "";
        Solution tested = new Solution();

        String actual = tested.longestCommonPrefix(given);

        assertEquals(expected,actual);
    }
    @Test
    public void custom_1() {
        String[] given = new String[] {"dog"};
        String expected = "dog";
        Solution tested = new Solution();

        String actual = tested.longestCommonPrefix(given);

        assertEquals(expected,actual);
    }
}