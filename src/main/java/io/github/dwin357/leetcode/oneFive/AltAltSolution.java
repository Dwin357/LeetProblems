package io.github.dwin357.leetcode.oneFive;

import java.util.List;

public class AltAltSolution {
    /*
     * Meditation: something that I can just barely see...
     *
     * The basic question I am trying to ask is...
     * - Assume a sorted (potentially compressed?) list
     * - And assume I did the thing where I started at both ends and walked to the middle
     * But!
     *  - instead of advancing based on a pivot calculation, i advanced on a simple oscillator (ie r,l,r,l ...)
     *
     * Like the 2 pointer, when workers meet they will, combined, represent an O(n) pass through the list
     * and if they keep marching would just regenerate the same data again (only now from dif sides)
     * [a,b,c,d,e,f] -> af, bf, be, ce, cd
     *
     * Clearly this does not represent all combinations, but is it enough to triangulate on every combo given I can
     * reference a map holding the whole list & addition is transitive.
     *
     * ...nope: counter example is [-1, 0, 1, 2, 3, 4, 5, 6]
     * assuming you want a triplet that sums to 0, the only valid solution is [0,1,2]
     * And since the 2 marchers will never land on any 2 of those during their march to the center,
     * the fact that you can "discover" the third from the map is immaterial
     *
     * Really all this oscillation is doing is approximating something like pivoting around the mean of the list
     * That constraint of needing a pivot & it needing to be meaningful along the axis-of-sort seems pretty baked in and not going anywhere
     */

    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}
