package io.github.dwin357.leetcode.oneFour;

public class Solution {
    /*
     * Meditation: So my first thought it a graph.
     *  each node of the graph represents a letter,
     *  and each lvl of the graph represents an index (prob w/ an artificial root node)
     *
     * You hand each word to the root, and it creates/updates the chain of nodes which the word breaks down to
     *
     * Then, for resolution, you walk the graph from the root --aggregating characters until you hit your first branch
     *
     * However....  while I absolutely think that would work, I think there might be a better way.
     *   As far as I can tell there is no need to tolerate deviants from the pattern,
     *   so as soon as pattern breaks you can end processing.
     *
     * So... nested for loop
     *  - outter for loop moves over char in the first word
     *   - inner for loop moves over other words in collection
     *    - every cycle of the inner loop that completes adds that char to accumulator
     *    - as soon inner loop finds a mismatch, short circuit everything and return whatever the acumulator has
     *
     * Runtime: 1 ms, faster than 65.41% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 37.2 MB, less than 59.56% of Java online submissions for Longest Common Prefix.
     */

    public String longestCommonPrefix(String[] strs) {
        StringBuffer accumulator = new StringBuffer();
        // for each letter in first word, combined w/ each subsequent word
        for(int c=0; c < strs[0].length(); c++) {
            for(int w=1; w<strs.length; w++) {
                // end evaluation if index is out of bounds for || char @ index mismatch for eval word
                if(c >= strs[w].length() || strs[0].charAt(c) != strs[w].charAt(c)) {
                    return accumulator.toString();
                }
            }
            // if a char passes every evaluated word, add to accumulator
            accumulator.append(strs[0].charAt(c));
        }
        return accumulator.toString();
    }
}
