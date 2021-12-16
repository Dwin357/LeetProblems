package io.github.dwin357.leetcode.twenties.twoZero;

import java.util.*;

public class Solution {

    /*
     * So at first pass, this looks pretty straight forward...
     * - read string L to R
     * - if el is an "open" char
     *  - push char onto a stack & continue
     * - if el is a close char
     *  - pop char off stack & compare
     *  - if match (ie mirror) continue
     *  - if break false
     * ...and if you make it to the end, default to true
     *
     * Runtime: 1 ms, faster than 98.96% of Java online submissions for Valid Parentheses.
     * Memory Usage: 37 MB, less than 84.46% of Java online submissions for Valid Parentheses.
     */

    private static final Set<Character> OPEN;
    private static final Map<Character,Character> MIRROR;
    static {
        OPEN = new HashSet<>();
        OPEN.add('(');
        OPEN.add('[');
        OPEN.add('{');

        MIRROR = new HashMap<>();
        MIRROR.put(')', '(');
        MIRROR.put(']', '[');
        MIRROR.put('}', '{');
    }

    public boolean isValid(String s) {
        Deque<Character> opened = new ArrayDeque<>();
        for(Character c : s.toCharArray()) {
            if(OPEN.contains(c)) {
                opened.push(c);
            } else {
                if(opened.isEmpty() || !opened.pop().equals(MIRROR.get(c))) {
                    return false;
                }
            }
        }
        return opened.isEmpty();
    }
}
