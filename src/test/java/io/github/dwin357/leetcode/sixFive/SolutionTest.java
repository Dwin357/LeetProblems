package io.github.dwin357.leetcode.sixFive;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void validSamples() {
        String[] valids = new String[]{ "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789" };
        Solution tested = new Solution();
        for(String valid : valids) {
            assertTrue(valid, tested.isNumber(valid));
        }
    }

    @Test
    public void invalidSamples() {
        String[] notValid = new String[]{ "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53" };
        Solution tested = new Solution();
        for(String inVal : notValid) {
            assertFalse(inVal, tested.isNumber(inVal));
        }
    }

    @Test
    public void example_1() {
        String given = "0";
        boolean expected = true;
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        String given = "e";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        String given = ".";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        String given = ".1";
        boolean expected = true;
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }

    @Test
    public void custom_1() {
        String given = "-.";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }

    @Test
    public void custom_2() {
        String given = "+.";
        boolean expected = false;
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }

    @Test
    public void custom_3() {
        String given = "23.e32";
        boolean expected = true; // I think
        Solution tested = new Solution();

        boolean actual = tested.isNumber(given);

        assertEquals(expected,actual);
    }
}