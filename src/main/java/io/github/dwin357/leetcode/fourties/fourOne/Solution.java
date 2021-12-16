package io.github.dwin357.leetcode.fourties.fourOne;

public class Solution {
    /*
     * So the big insight I had is that,
     *  - if I debit value by -1 when I assign the value-int, then value will align w/ i
     *  - also, I think I want to null a position when I assign its contents to the value-int
     *
     * Hopefully, these 2 things will simplify a bunch of this tail-chasing and help me address the edge cases
     *
     * Yay! - it worked
     * Runtime: 2 ms, faster than 98.31% of Java online submissions for First Missing Positive.
     * Memory Usage: 96.3 MB, less than 83.56% of Java online submissions for First Missing Positive.
     */

    public int firstMissingPositive(int[] nums) {

        // do the initial pseudo-sort
        int cache;
        int index = 0;
        int value = nums[index]-1;
        nums[index] = Integer.MIN_VALUE;

        while(true) {

            // if value is outside the scope of our ary, move on
            if(value < 0 || nums.length <= value) {

                index++;
                if(index >= nums.length) {
                    break;
                }
                value = nums[index]-1;
                nums[index] = Integer.MIN_VALUE;
                continue;
            }

            // if value falls in the cleared area, just position it and move on
            if(value <= index) {
                nums[value] = value+1;

                index++;
                if(index >= nums.length) {
                    break;
                }
                value = nums[index]-1;
                nums[index] = Integer.MIN_VALUE;
                continue;
            }

            // if value falls outside the cleared area, don't need to assign but advance pointer
            if(nums[value] == value+1) {
                index++;
                if(index >= nums.length) {
                    break;
                }
                value = nums[index]-1;
                nums[index] = Integer.MIN_VALUE;
                continue;
            }

            // finally, if none of the above special cases apply that means the value is
            // in-bounds, ahead of the pointer, & not correctly positioned
            cache = nums[value]-1; // capture the value at the tgt location
            nums[value] = value+1; // assign the tgt location w/ the current value
            value = cache; // assign next evaluation value to be that future one (and cross fingers it hits one of the 3 degenerate cases soon)
        }

        // do a pass to find the earliest null
        index = 0;
        while (index < nums.length) {
            if(nums[index] == Integer.MIN_VALUE) {
                break;
            }
            index++;
        }

        return index+1; // finally, b/c of the -1 shift, add back
    }
}
