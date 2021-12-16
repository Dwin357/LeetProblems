package io.github.dwin357.leetcode.twenties.twoTwo;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void example_1() {
        int given = 3;
        List<String> expected = Arrays.asList("((()))","(()())","(())()","()(())","()()()");
        Solution tested = new Solution();

        List<String> actual = tested.generateParenthesis(given);
        setComparison(expected,actual);
    }

    @Test
    public void example_2() {
        int given = 1;
        List<String> expected = Arrays.asList("()");
        Solution tested = new Solution();

        List<String> actual = tested.generateParenthesis(given);
        setComparison(expected,actual);
    }

    private void setComparison(List<String> expected, List<String> actual) {
//        assertEquals(expected.size(), actual.size());
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }
}