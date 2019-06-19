/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.thirtyTwo.stack;

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
        String input = "(()";
        int expected = 2;
        
        int actual = classUnderTest.longestValidParentheses(input);
        
        assertEquals(expected, actual);
    }

    @Test
    public void ex_2() {
        String input = ")()())";
        int expected = 4;
        
        int actual = classUnderTest.longestValidParentheses(input);
        
        assertEquals(expected, actual);
    }

    @Test
    public void ex_3() {
        String input = ")";
        int expected = 0;
        
        int actual = classUnderTest.longestValidParentheses(input);
        
        assertEquals(expected, actual);
    }

    @Test
    public void ex_4() {
        String input = "()(()";
        int expected = 2;
        
        int actual = classUnderTest.longestValidParentheses(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex_5() {
        String input = "()(())";
        int expected = 6;
        
        int actual = classUnderTest.longestValidParentheses(input);
        
        assertEquals(expected, actual);
    }
    
}
