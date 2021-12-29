package io.github.dwin357.leetcode.thirties.threeFour;

public class Solution {
    /*
     * Problem: search a sorted ary w/ dupes to find start and end index of tgt
     *
     * So I have absolutely had "mediums" kick my butt...  but it really seems like there
     * is nothing to this one???
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
     * Memory Usage: 42 MB, less than 80.23% of Java online submissions for Find First and Last Position of Element in Sorted Array.
     */
    public int[] searchRange(int[] nums, int target) {
        int i = 0;
        int j = -1;
        boolean measuring = false;

        // only search the ary if all these plausibility checks are met
        // - ary has elements
        // - tgt is above arg min val
        // - tgt is below arg max val
        if(nums.length > 0 && nums[0] <= target && nums[nums.length-1] >= target) {
            while(i < nums.length) {

                // case.1) if we are not measuring & we find a matching el
                // this is the first el, so we should note it and start measuring
                if(!measuring && nums[i] == target) {
                    j = i;
                    measuring = true;

                // case.2) in cases where we are measuring but the item doesn't match we should quit
                } else if(measuring && nums[i] != target) {
                    break;
                }

                i++;
            }
        }

        // in the case where we did measure, the end index is 1 before we stopped
        // in cases where we did not measure, reset end index to default -1
        i = measuring ? i-1 : -1;
        return new int[]{j, i};
    }
}
