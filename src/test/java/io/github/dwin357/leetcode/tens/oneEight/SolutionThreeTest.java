package io.github.dwin357.leetcode.tens.oneEight;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionThreeTest {
    @Test
    public void example_1() {
        int[] given = new int[]{1,0,-1,0,-2,2};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1)
        );

        SolutionThree tested = new SolutionThree();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertCollectionsProbEq(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[]{2,2,2,2,2};
        int tgt = 8;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2,2,2,2)
        );

        SolutionThree tested = new SolutionThree();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertCollectionsProbEq(expected,actual);
    }

    @Test
    public void test_207() {
        int[] given = new int[]{-3,-2,-1,0,0,1,2,3};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-3,-2,2,3),
                Arrays.asList(-3,-1,1,3),
                Arrays.asList(-3,0,0,3),
                Arrays.asList(-3,0,1,2),
                Arrays.asList(-2,-1,0,3),
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1)
        );

        SolutionThree tested = new SolutionThree();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertCollectionsProbEq(expected,actual);
    }

    @Test
    public void test_158() {
        int[] given = new int[]{-3,-1,0,2,4,5};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-3,-1,0,4)
        );

        SolutionThree tested = new SolutionThree();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertCollectionsProbEq(expected,actual);
    }

    /*
     * missing from mine: [-5, 0, 2, 3], [-3, -2, 0, 5]
     */
    @Test
    public void test_209() {
        int[] given = new int[]{-5,-4,-3,-2,-1,0,0,1,2,3,4,5};
        int tgt = 0;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-5,-4,4,5),
                Arrays.asList(-5,-3,3,5),
                Arrays.asList(-5,-2,2,5),
                Arrays.asList(-5,-2,3,4),
                Arrays.asList(-5,-1,1,5),
                Arrays.asList(-5,-1,2,4),
                Arrays.asList(-5,0,0,5),
                Arrays.asList(-5,0,1,4),
                Arrays.asList(-5,0,2,3),
                Arrays.asList(-4,-3,2,5),
                Arrays.asList(-4,-3,3,4),
                Arrays.asList(-4,-2,1,5),
                Arrays.asList(-4,-2,2,4),
                Arrays.asList(-4,-1,0,5),
                Arrays.asList(-4,-1,1,4),
                Arrays.asList(-4,-1,2,3),
                Arrays.asList(-4,0,0,4),
                Arrays.asList(-4,0,1,3),
                Arrays.asList(-3,-2,0,5),
                Arrays.asList(-3,-2,1,4),
                Arrays.asList(-3,-2,2,3),
                Arrays.asList(-3,-1,0,4),
                Arrays.asList(-3,-1,1,3),
                Arrays.asList(-3,0,0,3),
                Arrays.asList(-3,0,1,2),
                Arrays.asList(-2,-1,0,3),
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1)
        );

        SolutionThree tested = new SolutionThree();

        List<List<Integer>> actual = tested.fourSum(given,tgt);

        assertCollectionsProbEq(expected,actual);
    }

    /*
     * NOTE:this would declare the following eq [a,a,b] [a,b,b]
     */
    private <whatever> void assertCollectionsProbEq(List<whatever> a, List<whatever> b) {
        assertEquals(a.size(), b.size());
        assertEquals(new HashSet<>(a), new HashSet<>(b));
    }
}