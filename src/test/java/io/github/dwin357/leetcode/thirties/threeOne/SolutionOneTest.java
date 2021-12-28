package io.github.dwin357.leetcode.thirties.threeOne;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionOneTest {

    @Test
    public void example_1() {
        int[] given = new int[]{1,2,3};
        int[] expected = new int[]{1,3,2};
        SolutionOne tested = new SolutionOne();

        tested.nextPermutation(given);

        assertArrayEquals(expected,given);
    }

    @Test
    public void example_2() {
        int[] given = new int[]{3,2,1};
        int[] expected = new int[]{1,2,3};
        SolutionOne tested = new SolutionOne();

        tested.nextPermutation(given);

        assertArrayEquals(expected,given);
    }

    @Test
    public void example_3() {
        int[] given = new int[]{1,1,5};
        int[] expected = new int[]{1,5,1};
        SolutionOne tested = new SolutionOne();

        tested.nextPermutation(given);

        assertArrayEquals(expected,given);
    }

    @Test
    public void assumed_1() {
        int[] given = new int[]{1};
        int[] expected = new int[]{1};
        SolutionOne tested = new SolutionOne();

        tested.nextPermutation(given);

        assertArrayEquals(expected,given);
    }

    @Test
    public void test_8() {
        int[] given = new int[]{1,3,2};
        int[] expected = new int[]{2,1,3};
        SolutionOne tested = new SolutionOne();

        tested.nextPermutation(given);

        assertArrayEquals(expected,given);
    }
}