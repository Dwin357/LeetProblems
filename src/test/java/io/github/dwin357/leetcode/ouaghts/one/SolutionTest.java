package io.github.dwin357.leetcode.ouaghts.one;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_one() {
        // setup
        int[] givenCollection = new int[] {2,7,11,15};
        int givenTarget = 9;
        int[] expected = new int[] {0,1};
        Solution tested = new Solution();

        // tested act
        int[] actual = tested.twoSum(givenCollection, givenTarget);

        // assertion
        assertArrayEquals(expected,actual);
    }

    @Test
    public void example_two() {
        // setup
        int[] givenCollection = new int[] {3,2,4};
        int givenTarget = 6;
        int[] expected = new int[] {1,2};
        Solution tested = new Solution();

        // tested act
        int[] actual = tested.twoSum(givenCollection, givenTarget);

        // assertion
        assertArrayEquals(expected,actual);
    }

    @Test
    public void example_three() {
        // setup
        int[] givenCollection = new int[] {3,3};
        int givenTarget = 6;
        int[] expected = new int[] {0,1};
        Solution tested = new Solution();

        // tested act
        int[] actual = tested.twoSum(givenCollection, givenTarget);

        // assertion
        assertArrayEquals(expected,actual);
    }
}