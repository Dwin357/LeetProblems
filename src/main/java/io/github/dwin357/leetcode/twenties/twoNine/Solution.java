package io.github.dwin357.leetcode.twenties.twoNine;

public class Solution {

    /*
     * Meditation: Ohhhh - this is one of those problems that always feels unfair to me b/c it assumes I am using a tool
     * that is not on my tool belt.  At least I was less stubborn this time and just looked up the solution... b/c
     * there was no way I was going to invent bit-math on my own.
     *
     * Growth is Painful
     *
     * Code from Algorithms Made Easy: https://www.youtube.com/watch?v=m4L_5qG4vG8
            public int divide(int dividend, int divisor) {
                if(dividend == 1 << 31 && divisor == -1) return Integer.MAX_VALUE;
                boolean sign = ( (dividend>=0) == (divisor>=0) );

                dividend = Math.abs(dividend);
                divisor = Math.abs(divisor);
                int count;
                int rtn = 0;
                while(dividend - divisor >= 0) {
                    count = 0;
                    while(dividend - (divisor << 1 << count) >= 0) {
                        count++;
                    }
                    rtn += 1 << count;
                    dividend -= divisor << count;
                }
                return sign ? rtn : -rtn;
            }
     *
     * Further reading: https://www.baeldung.com/java-print-integer-binary
     *
     * Ok, completed my "visualization workshop" so I can at least now picture in my mind how all the >> << is working
     *
     * ...So I got "time limit exceeded" on this at test case 624 / 992
     * But the sample code I copied was
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
     * Memory Usage: 36 MB, less than 87.83% of Java online submissions for Divide Two Integers.
     *
     * ... but all the difference I see seem to me superficial
     * - On the outer while loop I compare the nums directly instead of the dif to 0
     *      ??? - why is this the thing?
     * - On the inner while loop I add +1 to bite and shift result (instead of just shifting an extra time)
     *      X - time limit exceeded at 624
     * - I run everything in mirror-land
     * - I manually flip the things to neg instead of using Math.abs
     *
     * Finally
     * Runtime: 2 ms, faster than 39.17% of Java online submissions for Divide Two Integers.
     * Memory Usage: 38.6 MB, less than 5.91% of Java online submissions for Divide Two Integers
     * (w/ all my quarks)
     *
     *
     */

    public int divide(int dividend, int divisor) {
        // per given if result goes out of bounds we are supposed to return Integer.Max || Integer.Min
        // since we are given ints & division makes things approach 0, the only case where this can come up
        // is Integer.Min / -1
        if(dividend == Integer.MIN_VALUE) {
            if(divisor == -1) {
                return Integer.MAX_VALUE;
            } else if(divisor == 1) {
//                return Integer.MIN_VALUE;
            }
        }

        // this covers the edge case where the divisor is Min value
        // if this edge case isn't covered, the "divisor << bite" goes into an infinite loop
        // I think this is an artifact of my working in mirror space relative to my example
        //// as an aside: the reason I did that was to guard against data loss when handleing Integer.Min
        // which I think is uncovered by the example impl  since Math.abs(Integer.Min) == Integer.Min
        if(divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        // make a note of if signs are matching (ie is rtn positive or neg)
        boolean matchingSigns = ( (dividend>=0) == (divisor>=0) );

        // flip everything to negative, just to avoid the edge case around converting Integer.Min to a positive
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        int count = 0;
        int bite;
        // NOTE: b/c we are working in mirror-land (ie all negs), we are watching for when the bottom is "less neg" than the top
        // NOTE: every cycle of the inner loop doubles the size of our bite, starting from our min bite sz of the divisor
        // so to eat 23 w/ a min bite sz of 3 we take 3 actual bites - but they still "count as" 7 bites
        // first is 12 (3 * 2^2), counting as 4 & leaving 11
        // second is 6 (3 * 2^1), counting as 2 & leaving 5
        // third is 3 (3 * 2^0), counting as 1 & leaving 2 (below our minimum bite size)
        while(dividend <= divisor) {
            bite = 0; // reset bit to minimum sz

            // if we can take a bite 1 sz (ie 2x) bigger than we currently are...
            while((dividend - (divisor << bite << 1)) < 0) {
                // then we should totally take that bigger bite
                bite++;
            }
            dividend -= (divisor << bite); // record how much pie is left over after our bite
            count += (1 << bite); // record what our bite actually "counts as" in terms of minimum bites
        }

        return matchingSigns ? count : -count; // if signs matched, result is pos --otherwise result is neg
    }

}
