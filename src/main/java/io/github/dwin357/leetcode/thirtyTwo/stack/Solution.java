/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.thirtyTwo.stack;

/**
 *
 * @author dwin
 */
public class Solution {
    /*
    Runtime: 2 ms, faster than 81.25% of Java online submissions for Longest Valid Parentheses.
Memory Usage: 37.4 MB, less than 97.71% of Java online submissions for Longest Valid Parentheses.
*/
  private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public int longestValidParentheses(String s) {
        Frame stack = new Frame(-1, null, true);
        int best = 0;
        int run = 0;
        
        char[] input = s.toCharArray();

        for(int i=0; i<input.length; i++) {
            // push frame
            if(input[i] == OPEN) {
                stack = new Frame(run, stack, false);
                run = 0;
            } 
            if(input[i] == CLOSE){
                           	
            	if(!stack.isAnchor()) { // pop farme 
            		run = run + 2 + stack.getRun();
            		if(run > best) {
            			best = run;
            		}
            		stack = stack.getNxt();

            	} else { // broken run
            		run = 0;
            	}
            }
        }
        
        if(run > best) {
            best = run;
//            System.out.println("i:~"+" best:"+best+" cur:"+run);
        }
        return best;
    }
    
    private class Frame {
        private int run;
        private Frame next;
        private boolean anchor;
        
        public Frame(int rn, Frame nxt, boolean achr) {
            this.run = rn;
            this.next = nxt;
            this.anchor = achr;
        }
        
        public int getRun() {
        	return this.run;
        }
        
        public Frame getNxt() {
            if(anchor) {
                return this;
            } else {
               return this.next;                
            }
        }  
        
        public boolean isAnchor() {
            return this.anchor;
        }
    }
}