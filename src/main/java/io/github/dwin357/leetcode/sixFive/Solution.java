package io.github.dwin357.leetcode.sixFive;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    /*
     * Meditation: this application of branching logic looks like a directed graph to me...
     *   I think doing first pass w/ if's & flags
     *   Then maybe a second pass w/ graph libs
     */


    private static final Set<Character> DIGITS;
    private static final Set<Character> SIGN;
    private static final Set<Character> NAT_NUM;
    private static final Character DEC = '.';
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

        SIGN = new HashSet<>();
        SIGN.add('+');
        SIGN.add('-');

        NAT_NUM = new HashSet<>();
        NAT_NUM.add('e');
        NAT_NUM.add('E');
    }

    /*
     * Runtime: 2 ms, faster than 73.13% of Java online submissions for Valid Number.
     * Memory Usage: 39.1 MB, less than 67.85% of Java online submissions for Valid Number.
     */

    // if's & flags
    public boolean isNumber(String s) {

        // gate-flags
        boolean signOpen = true;
        boolean decimalOpen = true;
        boolean natNumOpen = true;

        boolean seenDigit = false;
        boolean validTail = false;

        Character c;
        for(int i=0; i<s.length(); i++) {
            c = s.charAt(i);

            // evaluate sign
            if(signOpen && SIGN.contains(c)) {
                // if sign is processed, it can no longer consume digits
                signOpen = false;
                validTail = false;
                continue;
            }
            // if sign is not processed, gate closes
            signOpen = false;

            // evaluate decimal
            if(decimalOpen && DEC.equals(c)) {
                // decimal is only a valid tail if we have previously seen a digit
                validTail = seenDigit;
                // once we see a decimal, the option closes
                decimalOpen = false;
                continue;
            }

            // evaluate natural num
            if(natNumOpen && NAT_NUM.contains(c)) {
                // if previous num not valid, natural num is invalid
                if(!validTail) {
                    return false;
                }

                ////reset flags for integer exponent
                signOpen = true;
                decimalOpen = false;
                natNumOpen = false;

                seenDigit = false;
                validTail = false;

                // adv pointer
                continue;
            }

            // evaluate digit
            if(DIGITS.contains(c)) {
                // flag trailing & advance pointer
                validTail = true;
                seenDigit = true;
                continue;
            }

            // if a char can not be accounted for by previous rule, it is an invalid step & string fails
            return false;
        }

        // if whole string is processed w/out reaching invalid state & trailing char is valid,
        // it is implicitly valid
        return validTail;
    }
}
