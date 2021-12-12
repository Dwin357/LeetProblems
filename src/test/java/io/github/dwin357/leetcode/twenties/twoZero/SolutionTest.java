package io.github.dwin357.leetcode.twenties.twoZero;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        String given = "()";
        boolean expected = true;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        String given = "()[]{}";
        boolean expected = true;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        String given = "(]";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        String given = "([)]";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_5() {
        String given = "{[]}";
        boolean expected = true;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }

    @Test
    public void id_close() {
        String given = "}";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }

    @Test
    public void testCase_82() {
        String given = "[";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isValid(given);

        assertEquals(expected,actual);
    }
}