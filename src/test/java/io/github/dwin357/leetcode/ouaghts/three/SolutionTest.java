package io.github.dwin357.leetcode.ouaghts.three;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void example_1() {
        String given = "abcabcbb";
        int expected =3;
        Solution tested = new Solution();

        int actual = tested.lengthOfLongestSubstring(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        String given = "bbbbb";
        int expected =1;
        Solution tested = new Solution();

        int actual = tested.lengthOfLongestSubstring(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        String given = "pwwkew";
        int expected =3;
        Solution tested = new Solution();

        int actual = tested.lengthOfLongestSubstring(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        String given = "";
        int expected =0;
        Solution tested = new Solution();

        int actual = tested.lengthOfLongestSubstring(given);

        assertEquals(expected,actual);
    }

    @Test
    public void test_30() {
        String given = " ";
        int expected =1;
        Solution tested = new Solution();

        int actual = tested.lengthOfLongestSubstring(given);

        assertEquals(expected,actual);
    }

    @Test
    public void test_407() {
        String given = "dvdf";
        int expected =3;
        Solution tested = new Solution();

        int actual = tested.lengthOfLongestSubstring(given);

        assertEquals(expected,actual);
    }
}