package io.github.dwin357.leetcode.tens.oneFive;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SolutionTwoTest {

    @Test
    public void example_1() {
        int[] given = new int[] {-1,0,1,2,-1,-4};
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-1,-1,2), Arrays.asList(-1,0,1));
        SolutionTwo tested = new SolutionTwo();

        List<List<Integer>> actual = tested.threeSum(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[] {};
        List<List<Integer>> expected = new ArrayList<>();
        SolutionTwo tested = new SolutionTwo();

        List<List<Integer>> actual = tested.threeSum(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_3() {
        int[] given = new int[] {0};
        List<List<Integer>> expected = new ArrayList<>();
        SolutionTwo tested = new SolutionTwo();

        List<List<Integer>> actual = tested.threeSum(given);

        assertEquals(expected,actual);
    }
}