/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.twentyThree.mergeLists;

import io.github.dwin357.leetcode.twentyThree.mergeLists.Solution;
import io.github.dwin357.leetcode.utils.ListNode;
import io.github.dwin357.leetcode.utils.ListNodeUtil;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dwin
 */
public class SolutionTest {
    
    Solution classUnderTest;
    ListNodeUtil utils;
    
    @Before
    public void setUp() {
        classUnderTest = new Solution();
        utils = new ListNodeUtil();
    }

    @Test
    public void ex_0() {
        ListNode[] input = utils.buildArrayOfLists(new int[][] {
                                            new int[] {1,2},
                                            new int[] {3,4},
                                            new int[] {5,6}});
        
        ListNode expected = utils.buildList(new int[] {1,2,3,4,5,6});
        ListNode actual = classUnderTest.mergeKLists(input);
        
        utils.display(actual);
        assertTrue(utils.assertEqual(expected, actual));
    }
    
    @Test
    public void ex_1() {
        ListNode[] input = utils.buildArrayOfLists(new int[][] {
                                            new int[] {1,4,5},
                                            new int[] {1,3,4},
                                            new int[] {2,6}});
        
        ListNode expected = utils.buildList(new int[] {1,1,2,3,4,4,5,6});
        ListNode actual = classUnderTest.mergeKLists(input);
        
        utils.display(actual);
        assertTrue(utils.assertEqual(expected, actual));
    }
    
    @Test
    public void ex_2() {
        ListNode[] input = utils.buildArrayOfLists(new int[][] {});
        
        ListNode expected = null;
        ListNode actual = classUnderTest.mergeKLists(input);
        
        utils.display(actual);
        assertNull(actual);
    }
    
    @Test
    public void ex_3() {
        ListNode[] input = utils.buildArrayOfLists(new int[][] { new int[] {} });
        
        ListNode expected = null;
        ListNode actual = classUnderTest.mergeKLists(input);
        
        utils.display(actual);
        assertNull(actual);
    }
    

    
}
