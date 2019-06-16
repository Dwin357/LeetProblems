/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.utils;

/**
 *
 * @author dwin
 */
public class ListNodeUtil {
    
    public ListNode[] buildArrayOfLists(int[][] abrvList) {
        ListNode[] rtn = new ListNode[abrvList.length];
        for(int i=0; i<abrvList.length; i++) {
            rtn[i] = buildList(abrvList[i]);
        }
        return rtn;
    }
    
    public ListNode buildList(int[] elements) {
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
    
    public boolean assertEqual(ListNode expected, ListNode actual) {
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
    
    public void display(ListNode node) {
        System.out.println(toString(node));
    }
    
    public String toString(ListNode node) {
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
        return sb.toString();
    }
    
}
