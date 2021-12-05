package io.github.dwin357.leetcode.tens.oneSix;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int[] givenNums = new int[]{-1,2,1,-4};
        int givenTgt = 1;
        int expected = 2;
        Solution tested = new Solution();

        int actual = tested.threeSumClosest(givenNums,givenTgt);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] givenNums = new int[]{0,0,0};
        int givenTgt = 1;
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.threeSumClosest(givenNums,givenTgt);

        assertEquals(expected,actual);
    }
}