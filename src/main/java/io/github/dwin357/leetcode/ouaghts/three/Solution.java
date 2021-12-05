package io.github.dwin357.leetcode.ouaghts.three;

import java.util.HashMap;
import java.util.Map;

public class Solution {
/*
 * Runtime: 58 ms, faster than 17.55% of Java online submissions for Longest Substring Without Repeating Characters.
 * Memory Usage: 39.5 MB, less than 56.35% of Java online submissions for Longest Substring Without Repeating Characters.
 */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        Character c;
        int best = 0;
        int trimStart;
        for(int i=0; i<s.length(); i++) {
            c = s.charAt(i);

            // if previously seen char in this run
            if(cache.containsKey(c)) {
                // increment high score
                best = cache.size() > best ? cache.size() : best;
                // clear subset of last run that is now excluded
                // by clearing everything and replaying from index after invalid
                trimStart = cache.get(c) +1;
                cache.clear();
                for(; trimStart <= i; trimStart++) {
                    cache.put(s.charAt(trimStart), trimStart);
                }
            } else {
                cache.put(c, i);
            }
        }
        return cache.size() > best ? cache.size() : best;
    }


}
