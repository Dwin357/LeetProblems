package io.github.dwin357.leetcode.twenties.twoTwo;

import java.util.LinkedList;
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
     *
     * Runtime: 6 ms, faster than 14.62% of Java online submissions for Generate Parentheses.
     * Memory Usage: 41.4 MB, less than 5.45% of Java online submissions for Generate Parentheses.
     *
     * ...A pretty bad score, no doubt on account of all the lists I am making to aggregate my return
     */

    public List<String> generateParenthesis(int n) {
        // always opens w/ a '('
        List<char[]> results = recursive(n-1,n,1);
        List<String> rtn = new LinkedList<>();
        for(char[] el : results) {
            el[0] = '(';
            rtn.add(new String(el));
        }
        return rtn;
    }

    private List<char[]> recursive(int open, int close, int depth) {
        List<char[]> rtn = null;
        // base case
        if(open==0 && close==1){
            rtn = new LinkedList<>();
            char[] el = new char[depth+1];
            el[depth] = ')';
            rtn.add(el);
            return rtn;
        }




        if(close > open) {
            rtn = recursive(open, close-1, depth+1);
            for(char[] el : rtn) {
                el[depth] = ')';
            }
        }

        // only decrement the open if they are avail (they always run out first)
        if(open >0) {
            if(rtn == null){
                rtn = recursive(open-1,close,depth+1);
                for(char[] el : rtn) {
                    el[depth] = '(';
                }
            } else {
                int changeIndex = rtn.size();
                int countIndex = 0;
                rtn.addAll(recursive(open-1,close,depth+1));
                for(char[] el : rtn){
                    if(countIndex >= changeIndex){
                        el[depth] = '(';
                    }
                    countIndex++;
                }
            }
        }


        return rtn;
    }
}
