package io.github.dwin357.leetcode.fourties.fourOne;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionOneTest {
    @Test
    public void example_1() {
        int[] given = new int[]{1,2,0};
        int expected = 3;
        SolutionOne tested = new SolutionOne();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }


    @Test
    public void example_2() {
        int[] given = new int[]{3,4,-1,1};
        int expected = 2;
        SolutionOne tested = new SolutionOne();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int[] given = new int[]{7,8,9,11,12};
        int expected = 1;
        SolutionOne tested = new SolutionOne();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void test_11() {
        int[] given = new int[]{1};
        int expected = 2;
        SolutionOne tested = new SolutionOne();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void explore_11_a() {
        int[] given = new int[]{1,2,3};
        int expected = 4;
        SolutionOne tested = new SolutionOne();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }

    @Test
    public void explore_11_b() {
        int[] given = new int[]{3};
        int expected = 1;
        SolutionOne tested = new SolutionOne();

        int actual = tested.firstMissingPositive(given);

        assertEquals(expected,actual);
    }
}