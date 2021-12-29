package io.github.dwin357.leetcode.thirties.threeFive;

public class Solution {
    /*
     * Problem: given a sorted list & a tgt, give the index where el is found or would be inserted...
     *
     * Meditation: maybe I am traumatized by having to learn how to do bit-math & permutation counting on
     * "mediums"...  but this just seems like crawling the list until you find an el >= tgt and rtn that i
     * ...what am I missing?
     *
     * Nope - it was just what it looked like
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
     * Memory Usage: 38.5 MB, less than 75.15% of Java online submissions for Search Insert Position.
     */

    public int searchInsert(int[] nums, int target) {

        for(int i=0; i < nums.length; i++) {
            if(nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
