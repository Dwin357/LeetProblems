package io.github.dwin357.leetcode.seven;

public class Solution {
    /*
     * Runtime: 2 ms, faster than 47.60% of Java online submissions for Reverse Integer.
     * Memory Usage: 36.1 MB, less than 76.20% of Java online submissions for Reverse Integer.
     */

    public int reverse(int x) {

        // strip sign
        boolean isPos = x >= 0;
        int abs = Math.abs(x);

        try {
            // toString -> reverse -> toInt
            int reverse = Integer.parseInt(new StringBuilder(Integer.toString(abs)).reverse().toString());
            // reapply original sign
            return isPos ? reverse : -1 * reverse;

        } catch(NumberFormatException tooBig) {
            return 0;
        }

    }
}
