package io.github.dwin357.leetcode.twenties.twoSeven;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int remove = 2;
        int[] given = new int[]{3,2,2,3};
        int[] expectedState = new int[]{3,3};
        int expectedReturn = 2;

        Solution tested = new Solution();

        int actualReturn = tested.removeElement(given,remove);

        assertEquals(expectedReturn,actualReturn);
        assertMutatedState(expectedState,given);
    }

    @Test
    public void example_2() {
        int remove = 2;
        int[] given = new int[]{0,1,2,2,3,0,4,2};
        int[] expectedState = new int[]{0,1,3,0,4};
        int expectedReturn = 5;

        Solution tested = new Solution();

        int actualReturn = tested.removeElement(given,remove);

        assertEquals(expectedReturn,actualReturn);
        assertMutatedState(expectedState,given);
    }

    private void assertMutatedState(int[] expected, int[] actual) {
        for(int i=0; i<expected.length; i++){
            if(expected[i] != actual[i]) {
                fail(String.format("i:%d exp:%s act:%s", i, Arrays.toString(expected), Arrays.toString(actual)));
            }
        }
    }
}