package io.github.dwin357.leetcode.fourties.fourOne;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() { // hit default
        int[] given = new int[]{1,2,0};
        int expected = 3;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }


    @Test
    public void example_2() {
        int[] given = new int[]{3,4,-1,1};
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int[] given = new int[]{7,8,9,11,12};
        int expected = 1;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void test_11() {
        int[] given = new int[]{1};
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void explore_11_a() { // hit default
        int[] given = new int[]{1,2,3};
        int expected = 4;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void explore_11_b() {
        int[] given = new int[]{3};
        int expected = 1;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }


    @Test
    public void test_12() { // hit default
        int[] given = new int[]{1,1};
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void explore_12_a() {
        int[] given = new int[]{1,1,1};
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void explore_12_b() {
        int[] given = new int[]{1,2,2};
        int expected = 3;
        Solution tested = new Solution();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }
}