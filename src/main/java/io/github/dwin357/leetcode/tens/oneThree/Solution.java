package io.github.dwin357.leetcode.tens.oneThree;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /*
     * Runtime: 3 ms, faster than 99.81% of Java online submissions for Roman to Integer.
     * Memory Usage: 38.9 MB, less than 96.13% of Java online submissions for Roman to Integer.
     *
     */

    private static final Map<Character,Integer> NUMERALS;
    static {
        NUMERALS = new HashMap<>();
        NUMERALS.put('I', 1);
        NUMERALS.put('V', 5);
        NUMERALS.put('X', 10);
        NUMERALS.put('L', 50);
        NUMERALS.put('C', 100);
        NUMERALS.put('D', 500);
        NUMERALS.put('M', 1000);
    }

    public int romanToInt(String s) {
        int total = 0;
        Integer current;
        Integer next;
        for(int i=0; i < s.length(); i++) {

            // grab current character value & next character value (or null if current is last character)
            current = NUMERALS.get(s.charAt(i));
            next = ((i+1) < s.length()) ? NUMERALS.get(s.charAt(i+1)) : null;

            // if the next value exists & is bigger than the current value,
            // then it should be subtracted from the total as it is "modifying" the next value
            // ie the "I" in IV
            if(next != null && next > current) {
                total -= current;

            // in all other cases, just add current value to the total
            } else {
                total += current;
            }
        }

        return total;
    }

}
