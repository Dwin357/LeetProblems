package io.github.dwin357.leetcode.six;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        String given = "PAYPALISHIRING";
        int rows = 3;
        String expected = "PAHNAPLSIIGYIR";

        Solution tested = new Solution();

        String actual = tested.convert(given,rows);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        String given = "PAYPALISHIRING";
        int rows = 4;
        String expected = "PINALSIGYAHRPI";

        Solution tested = new Solution();

        String actual = tested.convert(given,rows);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        String given = "A";
        int rows = 1;
        String expected = "A";

        Solution tested = new Solution();

        String actual = tested.convert(given,rows);

        assertEquals(expected,actual);
    }
}