package io.github.dwin357.leetcode.nine;

public class Solution {

    /*
     * Runtime: 7 ms, faster than 76.45% of Java online submissions for Palindrome Number.
     * Memory Usage: 38.4 MB, less than 67.35% of Java online submissions for Palindrome Number.
     */

    public boolean isPalindrome(int x) {
        char[] xx = Integer.toString(x).toCharArray();
        for(int i=0; i < (xx.length / 2); i++) {
            if(xx[i] != xx[xx.length-i-1]) {
                return false;
            }
        }
        return true;
    }
}
