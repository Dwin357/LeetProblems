package io.github.dwin357.leetcode.ouaghts.eight;

import java.util.HashSet;
import java.util.Set;

/*
 * Runtime: 3 ms, faster than 47.38% of Java online submissions for String to Integer (atoi).
 * Memory Usage: 39.1 MB, less than 67.02% of Java online submissions for String to Integer (atoi)
 */

public class Solution {

    private static final char SPACE = ' ';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final Set<Character> DIGITS;
    static {
        DIGITS = new HashSet<>();
        DIGITS.add('0');
        DIGITS.add('1');
        DIGITS.add('2');
        DIGITS.add('3');
        DIGITS.add('4');
        DIGITS.add('5');
        DIGITS.add('6');
        DIGITS.add('7');
        DIGITS.add('8');
        DIGITS.add('9');
    }

    public int myAtoi(String s) {

        StringBuilder buffer = new StringBuilder();
        boolean isPos = true;
        boolean chompOpen = true;
        boolean signOpen = true;
        char c;
        for(int i=0; i<s.length(); i++) {
            c = s.charAt(i);

            // chomp spaces
            if(chompOpen && c == SPACE) {
                continue;
            }
            // close chomp if we ever advance past step
            chompOpen = false;

            // set sign
            if(signOpen && (c == MINUS || c == PLUS)) {
                if (c== MINUS) {
                    isPos = false;
                }
                // close sign if we ever perform step
                signOpen = false;
                continue;
            }
            // close sign if we ever advance past step
            signOpen = false;

            // buffer digits
            if(DIGITS.contains(c)) {
                buffer.append(c);

            // end eval if non-digit is reached
            } else {
                break;
            }
        }

        // if no digits buffered, default 0
        if(buffer.length() == 0) {
            return 0;
        }

        try {
            // drain buffer
            int result = Integer.parseInt(buffer.toString());
            // apply sign
            return isPos ? result : -1 * result;

            // if buffered value too big, default max/min value based on sign
        } catch (NumberFormatException tooBig) {
            return isPos ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }
}
