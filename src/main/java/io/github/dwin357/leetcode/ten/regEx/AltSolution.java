package io.github.dwin357.leetcode.ten.regEx;

import java.util.ArrayDeque;
import java.util.Deque;

public class AltSolution {

    /*
     * Meditations: so, conceptually both the pattern and the underlying
     *   represent lists, and we are trying to increment our way through
     *   both of them.  I never need to take a step backwards in either
     *   list (yes?), and if I can get to the end of both it is a valid
     *   match.
     *
     * Looking through the test cases, there is branching logic wherein a
     *   wild card character has an option to consume an element, but maybe
     *   it shouldn't in order to allow that element to meet a later requirement.
     *   In this way the problem kind of looks like a search.  Taking a depth-first
     *   approach makes sense to me to address this aspect (since any valid solution
     *   negates need to find further solutions), but some kind of ck-point system
     *   will be needed fall back to previous decisions and make different ones in the
     *   case of dead ends.
     *
     * Also, no looking at the old solution.  The fun of the exercise is seeing the
     *   difference in my code between then and now.
     */

    /*
     * Runtime: 37 ms, faster than 31.00% of Java online submissions for Regular Expression Matching.
     * Memory Usage: 39.3 MB, less than 40.47% of Java online submissions for Regular Expression Matching.
     *
     * Turns out last time I hand-coded a tree structure, decomposed pattern into that, and then had it chew
     * through the data.  Compared to others, it isn't fast (although it is apparently very memory efficient),
     * but it beats my current impl on both memory and speed (24ms, 37MB).
     * -Never forget, overconfidence is a slow and insidious killer.
     */

    private static final char WILD = '.';
    private static final char OPT = '*';
    private static final char HALT_UNDERLYING = '@';
    private static final char HALT_PATTERN = '!';

    public boolean isMatch(String s, String p) {
        char[] given = s.toCharArray();
        int gI = 0;
        char el;
        char[] pattern = p.toCharArray();
        int pI = 0;
        char reg;

        boolean isWild;
        boolean isOpt;

        Deque<Checkpoint> decsionPoints = new ArrayDeque<>();
        Checkpoint doOver;

        // chew on problem until you stop making progress
        boolean isMakingProgress;
        do {
            // assign initial values for run
            el = gI < given.length ? given[gI] : HALT_UNDERLYING;
            reg = pI < pattern.length ? pattern[pI] : HALT_PATTERN;
            isWild = reg == WILD;
            isOpt = (pattern.length > (pI+1) && OPT == pattern[pI+1]);
            isMakingProgress = false;

            // as long as there is underlying to ck; ck for match between underlying & pattern
            if(HALT_UNDERLYING != el  && (isWild || el == reg)) {
                // only advance pattern-position if not optional
                if(!isOpt) {
                    pI++;
                } else {

                    decsionPoints.push(new Checkpoint(gI, pI));
                }
                // adv underlying position
                gI++;
                isMakingProgress = true;
                continue;
            }

            // if can't marry off element & reg is optional, try skipping past it
            if(isOpt) {
                pI++;
                pI++;
                isMakingProgress = true;
                continue;
            }

            // if can't adv either list, go back to last ck-pt and make a dif decision
            if(HALT_PATTERN != reg  && !decsionPoints.isEmpty()) {
                doOver = decsionPoints.pop();
                gI = doOver.GI; // needs to give make element it consumed for others
                pI = doOver.PI + 2; // +2 b/c this consumes both the reg & its optional flag
                isMakingProgress = true;
                continue;
            }

        } while(isMakingProgress);

        // success is completing both lists
        return gI >= given.length && pI >= pattern.length;
    }

    private class Checkpoint {
        public final int GI;
        public final int PI;

        private Checkpoint(int gi, int pi) {
            GI = gi;
            PI = pi;
        }
    }

}
