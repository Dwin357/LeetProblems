package io.github.dwin357.leetcode.twenties.twoTwo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        int given = 3;
        List<String> expected = Arrays.asList("((()))","(()())","(())()","()(())","()()()");
        Solution tested = new Solution();

        List<String> actual = tested.generateParenthesis(given);
        assertThat(actual, containsInAnyOrder(expected));
    }

    @Test
    public void example_() {
        int given = ;
        List<String> expected = Arrays.asList("()");
        Solution tested = new Solution();

        List<String> actual = tested.generateParenthesis(given);
        assertThat(actual, containsInAnyOrder(expected));
    }
}