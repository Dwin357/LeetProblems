/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.thirties.thirty.comboSubstring;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dwin
 */
public class SolutionTest {
    
    private Solution classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new Solution();
    }

    @Test
    public void ex_1() {
        
        String blob = "barfoothefoobarman";
        String[] words = new String[] {"foo", "bar"};
        
        List<Integer> expected = Arrays.asList(0, 9);
        List<Integer> actual = classUnderTest.findSubstring(blob, words);
        
        assertEquals(expected, actual);
    }
    
}
