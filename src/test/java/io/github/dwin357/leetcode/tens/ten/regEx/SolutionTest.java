/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.tens.ten.regEx;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dwin
 */
public class SolutionTest {
    
    public SolutionTest() {
    }
    
    private Solution classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new Solution();
    }

    @Test
    public void ex1() {
        String input = "aa";
        String pattern = "a";
        
        boolean expected = false;
        
        boolean actual = classUnderTest.isMatch(input, pattern);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex2() {
        String input = "aa";
        String pattern = "a*";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(input, pattern);
        
        assertEquals(expected, actual);
    }
    
    
    
    @Test
    public void ex3() {
        String input = "ab";
        String pattern = ".*";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(input, pattern);
        
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void ex4() {
        String input = "aab";
        String pattern = "c*a*b";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(input, pattern);
        
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void ex5() {
        String input = "mississippi";
        String pattern = "mis*is*p*.";
        
        boolean expected = false;
        
        boolean actual = classUnderTest.isMatch(input, pattern);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex6() {
        String input = "aa";
        String pattern = "aa";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(input, pattern);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex7() {
        String in = "ab";
        String pat = ".*c";
        
        boolean expected = false;
        
        boolean actual = classUnderTest.isMatch(in, pat);
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex8() {
        String in = "aaa";
        String pat = "aaa";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(in, pat);
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex9() {
        String in = "aaa";
        String pat = "a*a";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(in, pat);
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex10() {
        String in = "aaa";
        String pat = "ab*a*c*a";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(in, pat);
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex11() {
        String in = "mississippi";
        String pat = "mis*is*ip*.";
        
        boolean expected = true;
        
        boolean actual = classUnderTest.isMatch(in, pat);
        assertEquals(expected, actual);
    }
    
    @Test
    public void ex_12() {
        String in = "aaba";
        String pat = "ab*a*c*a";
        
        boolean expected = false;
        
        boolean actual = classUnderTest.isMatch(in, pat);
        assertEquals(expected, actual);
    }
}
