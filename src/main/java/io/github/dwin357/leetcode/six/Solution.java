package io.github.dwin357.leetcode.six;

public class Solution {
    /*
     * n=3
     *  0 1 2 3 4 5 6 7 8 9
     *  0 1 2 1 0 1 2 1 0 1
     *   + + - - + + - - +
     *
     * n=4
     *  0 1 2 3 4 5 6 7 8 9
     *  0 1 2 3 2 1 0 1 2 3
     *   + + + - - - + + +
     * so, if you have a "frame" as [i=0,o=0,a=+], then frames increment
     * A[i=0,o=0,a=+]
     * B[i=1,o=1,a=+] B.i=A.i+1  B.o=A.o+A.a  B.a=(B.i/(n-1) % 2) == 0 ? +1 : -1
     * C[i=2,o=2,a=+]
     * D[i=3,o=3,a=-]
     * E[i=4,o=2,a=-]
     *
     *
     *  n / 3
     *  0 0 0 1 1 1 2 2 2 3
     *
     *  n % 3
     *  0 1 2 0 1 2 0 1 2 0
     *
     * Runtime: 5 ms, faster than 79.98% of Java online submissions for Zigzag Conversion.
     * Memory Usage: 39.8 MB, less than 62.59% of Java online submissions for Zigzag Conversion.
     */

    public String convert(String s, int numRows) {
        // guard against the one edge case
        if(numRows == 1) {
            return s;
        }

        // init data struct; ary of character lists
        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i=0; i<numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // setup oscillator to insert into data struct
        int oscilation = 1;
        int index = 0;
        int oscillator = 0;
        int peek;
        while(index < s.length()) {
//            System.out.printf("C:%s i:%d o:%d a:%d peek:%d \n", s.charAt(index), index, oscillator, oscilation, peek);

            rows[oscillator].append(s.charAt(index));

            oscillator = oscillator + oscilation;
            index++;
            peek = ((index / (numRows-1)) % 2);
            oscilation = (peek != 0) ? -1 : 1;
        }

        // merge all subsequent rows into the first
        for(int i=1; i<numRows; i++) {
            rows[0].append(rows[i].toString());
        }

        return rows[0].toString();
    }
}
