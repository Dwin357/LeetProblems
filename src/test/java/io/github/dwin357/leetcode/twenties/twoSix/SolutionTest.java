package io.github.dwin357.leetcode.twenties.twoSix;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int[] given = new int[] {1,1,2};
        int[] expectedState = new int[] {1,2};
        int expectedRtn = 2;
        Solution tested = new Solution();

        int actual = tested.removeDuplicates(given);

        assertEquals(expectedRtn,actual);
        assertMutatedState(expectedState,given);
    }

    @Test
    public void example_2() {
        int[] given = new int[] {0,0,1,1,1,2,2,3,3,4};
        int[] expectedState = new int[] {0,1,2,3,4};
        int expectedRtn = 5;
        Solution tested = new Solution();

        int actual = tested.removeDuplicates(given);

        assertEquals(expectedRtn,actual);
        assertMutatedState(expectedState,given);
    }

    @Test
    public void spotted_1() {
        int[] given = new int[] {};
        int[] expectedState = new int[] {};
        int expectedRtn = 0;
        Solution tested = new Solution();

        int actual = tested.removeDuplicates(given);

        assertEquals(expectedRtn,actual);
        assertMutatedState(expectedState,given);
    }

    @Test
    public void spotted_2() {
        int[] given = new int[] {1};
        int[] expectedState = new int[] {1};
        int expectedRtn = 1;
        Solution tested = new Solution();

        int actual = tested.removeDuplicates(given);

        assertEquals(expectedRtn,actual);
        assertMutatedState(expectedState,given);
    }
    /*
     * So I am setting up a new computer, and I thought this problem would be a nice way to do a shake-down
     * cruise to get git setup...  and suddenly I am not able to call static methods on String, which leads
     * to seeing that inteli-j doesn't have an SDK, which leads to a discovery that the new machine doesn't
     * actually have java on it...  Oh, right, I have to set that up...
     */
    private void assertMutatedState(int[] expected, int[] actual) {
        for(int i=0; i<expected.length; i++){
            if(expected[i] != actual[i]) {
                fail(String.format("i:%d exp:%s act:%s", i, Arrays.toString(expected), Arrays.toString(actual)));
            }
        }
    }
}