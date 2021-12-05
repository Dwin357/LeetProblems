package io.github.dwin357.leetcode.tens.oneSeven;

import java.util.*;

public class Solution {
    /*
     * Meditation: So this just looks like a tree.  Every Node is a number which connects to the next by edges which represent the letters.
     * So, given that, it seems like there are 3 basic ways to come at this: representing these things as objects (a true graph),
     * representing them as method calls (a recursive solution),
     * & maybe modeling them with for-loops (not as obvious to me off the bat, but logically I am pretty sure I can rewrite any recursion into a for-loop).
     *
     * Having done the recursive, I imagine the real trick w/ converting this to a for loop in managing all the sub-lists that come up
     *
     * Runtime: 1 ms, faster than 75.04% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 38.9 MB, less than 63.68% of Java online submissions for Letter Combinations of a Phone Number
     */

    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        for(StringBuffer sb : node(digits.length()-1, digits)) {
          result.add(sb.toString());
        }

        // this is a bit of a hack, but the only time result will ever be 1 is if it has an empty string in it
        // and doing this ck once is a lot easier than cking each el to see if it is empty
        if(result.size() == 1) {
            result = new LinkedList<>();
        }
        return result;
    }

    private List<StringBuffer> node(int i, String given) {
        // basecase: if a node does represent a digit, it is an still needed as an "empty leaf node" to anchor edges coming off of parent
        if(i < 0) {
            return Arrays.asList(new StringBuffer());
        }

        // otherwise, delegate to each child; appending the edges letter and aggregating returns as they come in
        List<StringBuffer> outPut = new LinkedList<>();
        Number n = Number.forChar(given.charAt(i));
        List<StringBuffer> path;
        for(int j=0; j < n.letters.length; j++) {
            path = node(i-1, given);
            for(StringBuffer el : path) {
                el.append(n.letters[j]);
            }
            outPut.addAll(path);
        }
        return outPut;
    }



    private enum Number {
        TWO('2', new char[]{'a','b','c'}),
        THREE('3', new char[]{'d','e','f'}),
        FOUR('4', new char[]{'g','h','i'}),
        FIVE('5', new char[]{'j','k','l'}),
        SIX('6', new char[]{'m','n','o'}),
        SEVEN('7', new char[]{'p','q','r','s'}),
        EIGHT('8', new char[]{'t','u','v'}),
        NINE('9', new char[]{'w','x','y','z'})
        ;
        public final char[] letters;
        public final char num;

        Number(char num, char[] letters) {
            this.num = num;
            this.letters = letters;
        }

        private static final Map<Character,Number> LOOKUP;
        static {
            LOOKUP = new HashMap<>();
            LOOKUP.put(TWO.num, TWO);
            LOOKUP.put(THREE.num, THREE);
            LOOKUP.put(FOUR.num, FOUR);
            LOOKUP.put(FIVE.num, FIVE);
            LOOKUP.put(SIX.num, SIX);
            LOOKUP.put(SEVEN.num, SEVEN);
            LOOKUP.put(EIGHT.num, EIGHT);
            LOOKUP.put(NINE.num, NINE);
        }

        public static Number forChar(Character c) {
            return LOOKUP.get(c);
        }
    }
}
