package io.github.dwin357.leetcode.thirties.threeThree;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstSolutionTest {
    @Test
    public void example_1() {
        int[] given = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        int expected = 4;
        FirstSolution tested = new FirstSolution();

        int actual = tested.search(given,target);

        assertEquals(expected,actual);
    }
    @Test
    public void example_2() {
        int[] given = new int[]{4,5,6,7,0,1,2};
        int target = 3;
        int expected = -1;
        FirstSolution tested = new FirstSolution();

        int actual = tested.search(given,target);

        assertEquals(expected,actual);
    }
    @Test
    public void example_3() {
        int[] given = new int[]{1};
        int target = 0;
        int expected = -1;
        FirstSolution tested = new FirstSolution();

        int actual = tested.search(given,target);

        assertEquals(expected,actual);
    }
}