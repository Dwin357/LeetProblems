package io.github.dwin357.leetcode.twenties.twoNine;

public class SolutionOne {
    /*
     * Challenge: can't use divide, multiply, modulus
     * Meditation: So...  you just run a loop subtracting the divisor until the dividend goes below zero
     * and return the count for how many loops you did (right?)
     *
     * Ok, so after I have messed with it a bit --the challenge seems to be around dealing with the signs
     * To deal with that, I imagine having lambdas that get plugged in according to the signs & run in something general
     *
     * if dend+ & sor+
     * - run until dend is <= 0
     * - on each cycle: dend - sor
     * - on each cycle: counter++
     *
     * if dend+ & sor-
     * - run until dend is <= 0
     * - on each cycle: dend + sor
     * - on each cycle: counter--
     *
     * if dend- & sor+
     * - run until dend is >= 0
     * - on each cycle: dend + sor
     * - on each cycle: counter--
     *
     * if dend- & sor-
     * - run until dend is >= 0
     * - on each cycle: dend - sor
     * - on each cycle: counter++
     *
     * So the break condition depends upon the sign of the dend, and the cycle condition depends upon if the signs match
     *
     * ...Hugh
     * So I tried to do this using primitive lambdas assigned by ternaries at the top of the function,
     * but I was getting weird behavior around the dividend == 0 case
     * logically I should be able to do the same stuff with methods (which should handle prims better);
     * giving that a go
     */

    public int divide(int dividend, int divisor) {
        boolean matchingSigns = (dividend >= 0 && divisor >= 0) || (dividend < 0 && divisor < 0);
        boolean countingUp = dividend >= 0;


        int count = 0;

        // this is just a cheat to short-circuit the divisor == 1 cases
        // Also, this is a snake-pit of readability --but I feel like the leetcode grading on
        // machine dimensions of speed & memory result in readability being undervalued
        if(divisor == 1 || divisor == -1) {
            if(countingUp && matchingSigns) {
                count = dividend;
            } else if(countingUp || matchingSigns) {
                if(dividend != Integer.MIN_VALUE) {
                    count = -dividend;
                } else {
                    count = Integer.MAX_VALUE;
                }
            } else {
                count = dividend;
            }
            return count;
        }


        // this looks a little funny, but the reason the dividend is incremented once before we start counting is to
        // "throw out" the last tick of the counter (which will cover the modulus that we are truncating)
        dividend = incrementDividend(dividend,divisor,matchingSigns);

        // the primary execution loop
        while(working(dividend,countingUp)) {
            dividend = incrementDividend(dividend,divisor,matchingSigns);
            count = incrementCount(count,matchingSigns);
        }
        return count;
    }

    private boolean working(int dividend, boolean coutingUp) {
        if(coutingUp) {
            return dividend >= 0;
        } else {
            return dividend <= 0;
        }
    }

    private int incrementDividend(int dividend, int divisor, boolean matchingSigns) {
        if(matchingSigns) {
            return dividend - divisor;
        } else {
            return dividend + divisor;
        }
    }

    private int incrementCount(int count, boolean matchingSigns) {
        if(matchingSigns) {
            return count==Integer.MAX_VALUE ? Integer.MAX_VALUE : count +1;
        } else {
            return count==Integer.MIN_VALUE ? Integer.MIN_VALUE : count -1;
        }
    }
}
