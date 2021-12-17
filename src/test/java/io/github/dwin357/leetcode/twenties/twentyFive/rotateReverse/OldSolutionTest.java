/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.twenties.twentyFive.rotateReverse;

import io.github.dwin357.leetcode.utils.ListNode;
import io.github.dwin357.leetcode.utils.ListNodeUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author dwin
 */
public class OldSolutionTest {
    
    private ListNodeUtil util;
    private OldSolution classUnderTest;
    
    
    @Before
    public void setUp() {
        classUnderTest = new OldSolution();
        util = new ListNodeUtil();
    }

    @Test
    public void ex_1() {
        ListNode inputList = util.buildList(new int[] {1,2,3,4,5});
        int inputCount = 2;
        
        ListNode expected = util.buildList(new int[] {2,1,4,3,5});
        
        ListNode actual = classUnderTest.reverseKGroup(inputList, inputCount);
                System.out.println("expected:" + util.toString(expected));
        System.out.println("actual:" + util.toString(actual));        
        assertTrue(util.assertEqual(expected, actual));        
    }

    @Test
    public void ex_2() {
        ListNode inputList = util.buildList(new int[] {1,2,3,4,5});
        int inputCount = 3;
        
        ListNode expected = util.buildList(new int[] {3,2,1,4,5});
        
        ListNode actual = classUnderTest.reverseKGroup(inputList, inputCount);
        
        System.out.println("expected:" + util.toString(expected));
        System.out.println("actual:" + util.toString(actual));        
        assertTrue(util.assertEqual(expected, actual));        
    }

    @Test
    public void ex_3() {
        ListNode inputList = util.buildList(new int[] {1,2,3,4,5,6,7,8});
        int inputCount = 3;
        
        ListNode expected = util.buildList(new int[] {3,2,1,6,5,4,7,8});
        
        ListNode actual = classUnderTest.reverseKGroup(inputList, inputCount);
        
        System.out.println("expected:" + util.toString(expected));
        System.out.println("actual:" + util.toString(actual));        
        assertTrue(util.assertEqual(expected, actual));        
    }

    @Test
    public void ex_4() {
        ListNode inputList = util.buildList(new int[] {1,2,3,4,5});
        int inputCount = 1;
        
        ListNode expected = util.buildList(new int[] {1,2,3,4,5});
        
        ListNode actual = classUnderTest.reverseKGroup(inputList, inputCount);
        
        System.out.println("expected:" + util.toString(expected));
        System.out.println("actual:" + util.toString(actual));        
        assertTrue(util.assertEqual(expected, actual));        
    }
    
}
