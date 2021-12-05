package io.github.dwin357.leetcode.tens.oneEight;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionOneTest {

    @Test
    public void example_1() {
        int[] given = new int[]{1,0,-1,0,-2,2};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1)
        );

        SolutionOne tested = new SolutionOne();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[]{2,2,2,2,2};
        int tgt = 8;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2,2,2,2)
        );

        SolutionOne tested = new SolutionOne();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertEquals(expected,actual);
    }

    /*
    actual
    [[-3,-2,2,3],
    [-3,-1,1,3],
    [-3,0,0,3],
    [-3,0,1,2],
    [-2,-1,1,2],
    [-2,0,0,2],
    [-1,0,0,1]]
    expected
    [[-3,-2,2,3],
    [-3,-1,1,3],
    [-3,0,0,3],
    [-3,0,1,2],
    [-2,-1,0,3],
    [-2,-1,1,2],
    [-2,0,0,2],
    [-1,0,0,1]]
     */
    @Test
    public void test_207() {
        int[] given = new int[]{-3,-2,-1,0,0,1,2,3};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-3,-2,2,3),
                Arrays.asList(-3,-1,1,3),
                Arrays.asList(-3,0,0,3),
                Arrays.asList(-2,-1,0,3),
                Arrays.asList(-3,0,1,2),
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1)
        );

        SolutionOne tested = new SolutionOne();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertEquals(expected,actual);
    }

    @Test
    public void test_158() {
        int[] given = new int[]{-3,-1,0,2,4,5};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-3,-1,0,4)
        );

        SolutionOne tested = new SolutionOne();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertEquals(expected,actual);
    }
}