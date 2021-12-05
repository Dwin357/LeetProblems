package io.github.dwin357.leetcode.tens.oneSeven;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        // setup
        String given = "23";
        Set<String> expectedValues = new HashSet<>(Arrays.asList("ad","ae","af","bd","be","bf","cd","ce","cf"));
        int expectedSize = expectedValues.size();

        Solution tested = new Solution();

        // tested act
        List<String> actual = tested.letterCombinations(given);

        // measurement
        int actSize = actual.size();
        Set<String> act = new HashSet<>(actual);

        // assertion
        assertEquals(expectedSize,actSize);
        assertEquals(expectedValues,act);
    }

    @Test
    public void example_2() {
        // setup
        String given = "";
        Set<String> expectedValues = new HashSet<>();
        int expectedSize = expectedValues.size();

        Solution tested = new Solution();

        // tested act
        List<String> actual = tested.letterCombinations(given);

        // measurement
        int actSize = actual.size();
        Set<String> act = new HashSet<>(actual);

        // assertion
        assertEquals(expectedSize,actSize);
        assertEquals(expectedValues,act);
    }
}