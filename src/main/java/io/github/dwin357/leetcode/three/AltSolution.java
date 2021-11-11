package io.github.dwin357.leetcode.three;

import java.util.HashMap;
import java.util.Map;

public class AltSolution {

    /*
     * Runtime: 6 ms, faster than 70.79% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 39.7 MB, less than 48.24% of Java online submissions for Longest Substring Without Repeating Characters.
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        Character c;
        int highScore = 0;
        int runStart = 0;
        int cut;
        for(int i=0; i<s.length(); i++) {
            c = s.charAt(i);

            // if previously seen char in this run
            if(cache.containsKey(c)) {
                // increment high score
                highScore = cache.size() > highScore ? cache.size() : highScore;

                // clear subset of last run that is now excluded, now by deleting
                cut = cache.get(c);
                for(int j = runStart; j <= cut; j++) {
                    cache.remove(s.charAt(j));
                }
                runStart = cut+1;
            }
            cache.put(c, i);
        }
        return cache.size() > highScore ? cache.size() : highScore;
    }

}
