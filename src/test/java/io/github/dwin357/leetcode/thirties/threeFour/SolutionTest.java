package io.github.dwin357.leetcode.thirties.threeFour;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SolutionTest {

    @Test
    public void example_1() {
        int[] given = new int[]{5,7,7,8,8,10};
        int target = 8;
        int[] expected = new int[]{3,4};

        Solution tested = new Solution();

        int[] actual = tested.searchRange(given,target);

        assertArrayEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int[] given = new int[]{5,7,7,8,8,10};
        int target = 6;
        int[] expected = new int[]{-1,-1};

        Solution tested = new Solution();

        int[] actual = tested.searchRange(given,target);

        assertArrayEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int[] given = new int[]{};
        int target = 0;
        int[] expected = new int[]{-1,-1};

        Solution tested = new Solution();

        int[] actual = tested.searchRange(given,target);

        assertArrayEquals(expected,actual);
    }

    @Test
    public void edge_1() {
        int[] given = new int[]{1,2,3,3};
        int target = 3;
        int[] expected = new int[]{2,3};

        Solution tested = new Solution();

        int[] actual = tested.searchRange(given,target);

        assertArrayEquals(expected,actual);
    }
}