package io.github.dwin357.leetcode.twenties.twoEight;

public class SolutionOne {
    /*
     * Meditation: so the clear thing that jumps out to me is
     *  - split haystack into char[]
     *  - split needle into char[]
     * forEach char in haystack ck if it matches needle[0]
     *  - if no, continue
     *  - if yes, ck if i+1 == needle[1] (through all of needle)
     *
     * if we make it through needle, return i
     * if we make it through haystack w/out completing needle, return -1
     *
     * I assume doing stuff w/ char-at is more efficient than substring ... but maybe worth cking
     *
     * Hmm...  I ran out of time for a very long needle
     * That suggests an interesting optimization where you don't actually need to evaluate any h
     * where there are less than needle.length num of char left in haystack
     *
     * So it isn't a good solution, but I kind of feel like they are jerking me around with these
     * very long strings and an unstated time constraint (all the examples that are "failing" pass on my machine)
     * ...so, what if rather than starting from 0 we start from end and count down
     * Oh - that will even buy me some stuff b/c I don't need to calculate the dif in length between haystack & needle each time
     * ya, ok: I belly ached, but this is actually much better code
     *
     * well now we have the issue of returning the last match instead of the first...
     * this is kind of becoming a mess ... we made it to test 80, but again failed on time
     *
     * I should probably just do a substring version -- but I'm kind of being stubborn at the moment
     */
    public int strStr(String haystack, String needle) {
        int h =  -1;
        int limit = haystack.length() - needle.length();
        int n;
        hay:while(h < limit) {
            h++;

            n = 0;
            while(n < needle.length()) {
                if(haystack.charAt(h+n) != needle.charAt(n)) {
                    continue hay;
                }
                n++;
            }
            return h; // if we make it through needle, that h is the ans
        }
        return -1; // default -1 if we can never make it through needle anywhere
    }
}
