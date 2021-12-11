package io.github.dwin357.leetcode.twenties.twoSix;

import org.junit.Test;

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
    /*
     * So I am setting up a new computer, and I thought this problem would be a nice way to do a shake-down
     * cruise to get git setup...  and suddenly I am not able to call static methods on String, which leads
     * to seeing that inteli-j doesn't have an SDK, which leads to a discovery that the new machine doesn't
     * actually have java on it...  Oh, right, I have to set that up...
     */
    private void assertMutatedState(int[] expected, int[] actual) {
        for(int i=0; i<expected.length; i++){
            if(expected[i] != actual[i]) {
                fail("i:" + i + " exp:");
            }
        }
    }
}