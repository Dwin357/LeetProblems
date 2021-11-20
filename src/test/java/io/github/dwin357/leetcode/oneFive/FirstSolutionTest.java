package io.github.dwin357.leetcode.oneFive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirstSolutionTest {

    @Test
    public void example_1() {
        int[] given = new int[] {-1,0,1,2,-1,-4};
        //NOTE: the example has the nested ary in the commented out order --but when I ran the sample test it seemed fine mine were in the reverse seq
//        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-1,-1,2), Arrays.asList(-1,0,1));
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-1,0,1), Arrays.asList(-1,-1,2));
        FirstSolution tested = new FirstSolution();

        List<List<Integer>> actual = tested.threeSum(given);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[] {};
        List<List<Integer>> expected = new ArrayList<>();
        FirstSolution tested = new FirstSolution();

        List<List<Integer>> actual = tested.threeSum(given);

        assertEquals(expected,actual);
    }
    @Test
    public void example_3() {
        int[] given = new int[] {0};
        List<List<Integer>> expected = new ArrayList<>();
        FirstSolution tested = new FirstSolution();

        List<List<Integer>> actual = tested.threeSum(given);

        assertEquals(expected,actual);
    }
}