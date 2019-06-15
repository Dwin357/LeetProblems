/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.problemTwentyThree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dwin
 */
public class SolutionTest {
    
    private static final int ASCI_ZERO = 48;
    
    Solution classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new Solution();
    }

    @Test
    public void ex_0() {
        ListNode[] input = buildInput(new int[][] {
                                            new int[] {1,2},
                                            new int[] {3,4},
                                            new int[] {5,6}});
        
        ListNode expected = buildOutput(new int[] {1,2,3,4,5,6});
        ListNode actual = classUnderTest.mergeKLists(input);
        
        display(actual);
        assertTrue(assertEqual(expected, actual));
    }
    
    @Test
    public void ex_1() {
        ListNode[] input = buildInput(new int[][] {
                                            new int[] {1,4,5},
                                            new int[] {1,3,4},
                                            new int[] {2,6}});
        
        ListNode expected = buildOutput(new int[] {1,1,2,3,4,4,5,6});
        ListNode actual = classUnderTest.mergeKLists(input);
        
        display(actual);
        assertTrue(assertEqual(expected, actual));
    }
    
    @Test
    public void ex_2() {
        ListNode[] input = buildInput(new int[][] {});
        
        ListNode expected = null;
        ListNode actual = classUnderTest.mergeKLists(input);
        
        display(actual);
        assertNull(actual);
    }
    
    @Test
    public void ex_3() {
        ListNode[] input = buildInput(new int[][] { new int[] {} });
        
        ListNode expected = null;
        ListNode actual = classUnderTest.mergeKLists(input);
        
        display(actual);
        assertNull(actual);
    }
    
    private ListNode[] buildInput(int[][] abrvList) {
        ListNode[] rtn = new ListNode[abrvList.length];
        for(int i=0; i<abrvList.length; i++) {
            rtn[i] = buildOutput(abrvList[i]);
        }
        return rtn;
    }
    
    private ListNode buildOutput(int[] elements) {
        if(elements.length == 0) {
            return null;
        }
        ListNode root = new ListNode(elements[0]);
        ListNode prev = root;        
        for(int i=1; i<elements.length; i++) {
            prev.next = new ListNode(elements[i]);
            prev  = prev.next;
        }
        return root;
    }
    
    private boolean assertEqual(ListNode expected, ListNode actual) {
        int i = 0;
        while(expected != null && actual != null) {
            if(expected.val != actual.val) {
                System.out.println("dif values exp:"+expected.val+" act:"+actual.val);
                return false;
            }
            expected = expected.next;
            actual = actual.next;
            i++;
        }
        if(expected != null || actual != null) {
            System.out.println("dif length i:"+i+" exp?"+(expected!=null)+" act?"+(actual!=null));
            return false;
        }
        return true;
    }
    
    private void display(ListNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        int i=0;
        while(node != null) {
//            sb.append(i);
//            sb.append(":");
            sb.append(node.val);
            sb.append(", ");
            i++;
            node = node.next;
        }
        sb.append(" }");
        System.out.println(sb.toString());
    }
    
}
