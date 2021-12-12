package io.github.dwin357.leetcode.twenties.twoSix;

public class Solution {
    /*
     * Meditation: so...  I am to modify in the input ary, not using more the O(1) space, and return essentially
     * the length of the compacted ary.  Any values after the compacted ary don't matter.
     * And the given is already sorted in "non-decreasing" order (why not just say ascending?)
     *
     * So the plates we need to keep spinning are...
     * - index for the interrogation pointer
     * - index for the write pointer
     * - ...I was going to say a counter for what has been modified; but this is actually the write-pointer
     * - the last seen value, b/c if interrogation-value == last seen-value, then we skip
     *
     * And the input is allowed to be length.0, so will need to guard the edge cases of sz.0 & sz.1
     * Luckily, these edge cases are by definition uncompressible (ie just return 0)
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
     * Memory Usage: 40.7 MB, less than 36.95% of Java online submissions for Remove Duplicates from Sorted Array.
     */

    public int removeDuplicates(int[] nums) {
        // guard uncompressible edge cases {} and {x}
        if(nums.length < 2) {
            return nums.length;
        }

        int writePointer = 1;
        int readPointer = 1;

        // process whole ary w/ read-pointer
        while(readPointer < nums.length) {

            // only if the thing we are looking at is novel do we write it
            if(nums[writePointer-1] != nums[readPointer]) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }

            // always advance read pointer
            readPointer++;
        }
        return writePointer;
    }
}
