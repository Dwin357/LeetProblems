package io.github.dwin357.leetcode.twenties.twoTwo;

import java.util.List;

public class Solution {
    /*
     * So at first blush this is clearly a recursive problem
     * - the seed always has to apply an "open bracket", & then call recursive
     * - the recursive method accepts in...
     *  -- the seq thus far
     *  -- ct of unused "opens"
     *  -- ct of unused "closes"
     *
     * if both counts are == 0, that is the base case
     * if one count is != 0, it recursively calls itself 1x for the other
     * if both counts != 0, it recursively calls itself 2x (once for each)
     *
     * Interestingly, if I keep track of my depth, that also corresponds to my
     * index position w/in the result ary, so I could optimize the return by having
     * the base-case init a char[] of appropriate length & have each level
     * set their value on the rebound (ie recurse first & take action second)
     */

    public List<String> generateParenthesis(int n) {

        return null;
    }

    private List<char[]> abc(int open, int close) {
        return null;
    }
}
