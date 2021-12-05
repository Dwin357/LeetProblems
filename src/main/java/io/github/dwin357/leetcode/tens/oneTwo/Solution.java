package io.github.dwin357.leetcode.tens.oneTwo;

public class Solution {

    /*
     * Meditation: so right off the bat a number of things jump out at me.
     *  1) b/c I am building up a string as my return - use string buffer as my return aggregator
     *  2) b/c I am decomposing an int into a series of sums - use a combo of / and % to break number down
     *  2.b) and I will want to process the big romans first, b/c it is better to break 200 into cc rather than i^200
     *
     *  The trick is going to be in doing the "little number in front decrements" stuff
     *  ...pattern is that a num can only decrement a roman 2 above it; so no ic for 99
     *  ...a better pattern is, only a base-10 number can decrement (so no vx for 5)
     *  and it can only decrement up to the next base-10
     *
     *  ...you know ...if I just hard code the edge cases this problem becomes essentially trivial --hahaha
     * Runtime: 4 ms, faster than 81.22% of Java online submissions for Integer to Roman.
     * Memory Usage: 38.4 MB, less than 87.65% of Java online submissions for Integer to Roman.
     *
     *  ...that does kind of feel like cheating.
     *  New rule: your numeral tuple has to be an <int,char>
     * Runtime: 4 ms, faster than 81.22% of Java online submissions for Integer to Roman.
     * Memory Usage: 38.3 MB, less than 92.88% of Java online submissions for Integer to Roman.
     *
     */
    private static final Numeral[] NUMERALS;
    static {
        // it is important that this is a sorted list high -> low
        NUMERALS = new Numeral[]{
                new Numeral(1000, 'M', '!'),
                new Numeral(100, 'C', 'D'),
                new Numeral(10, 'X', 'L'),
                new Numeral(1, 'I', 'V')
        };
    }

    public String intToRoman(int num) {
        StringBuilder rtn = new StringBuilder();
        int count;

        int evaluated=0;
        // working from highest tens to lowest
        for(int i=0; i< NUMERALS.length; i++) {

            // skip if in thousands
            if(i != 0) {
                // try to take out 9*10^i, if possible there will only be 1
                evaluated = 9 * NUMERALS[i].value;
                if((num / evaluated) > 0) {
                    rtn.append(NUMERALS[i].sign);
                    rtn.append(NUMERALS[i-1].sign);
                    num = num % evaluated;
                }

                // try to take out 5*10^i, if possible there will only be 1
                evaluated = 5 * NUMERALS[i].value;
                if((num / evaluated) > 0) {
                    rtn.append(NUMERALS[i].five);
                    num = num % evaluated;
                }

                // try to take out 4*10^i, if possible there will only be 1
                evaluated = 4 * NUMERALS[i].value;
                if((num / evaluated) > 0) {
                    rtn.append(NUMERALS[i].sign);
                    rtn.append(NUMERALS[i].five);
                    num = num % evaluated;
                }
            }

            // take out as much as you can at each step
            count = num / NUMERALS[i].value;
            for(int c=0; c<count; c++) {
                rtn.append(NUMERALS[i].sign);
            }

            // and reset num to remainder
            num = num % NUMERALS[i].value;
        }
        return rtn.toString();
    }

    private static class Numeral {
        public final int value;
        public final char sign;
        public final char five;

        private Numeral(int value, char sign, char five) {
            this.value = value;
            this.sign = sign;
            this.five = five;
        }
    }
}
