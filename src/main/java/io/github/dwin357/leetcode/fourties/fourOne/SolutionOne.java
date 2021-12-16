package io.github.dwin357.leetcode.fourties.fourOne;

public class SolutionOne {
    /*
     * Given an unsorted aray, return the first missing positive int.
     * Solution must run in O(n)-time & O(1)-space
     * ary can be 1 - 500,000 long
     * int can be any value from int.min - int.max
     *
     * yikes!
     * Where to start: I see 3 obvious places
     * - how would you find a minimum integer in an unsorted ary?
     * - how would you find a missing integer in an unsorted ary?
     * - how would you solve if you didn't have the time+space constraints?
     *
     * A) loop the list maintaining a "leader board" which is eval at each pos, and at end return leaderboard
     * C.1)
     *  -- sort list
     *  -- advance counter to first pos-num
     *  -- then start counting 1,2,3... & adv pointer, where pointer is above count that is your ans
     *
     * Oh?!?! -- hahaha is that the ans?
     * - tick through the list, and generally you want to be nulling the spaces -so
     *  -- if value is neg, null space and don't care about value
     *  -- if value is pos & greater than n, null space and don't care about value
     *  -- if value is pos & less than pointer, put value in that index location (which should be "null cleared" already)
     *  -- if value is pos & > pointer & < n, adv to that position, swap value you are holding w/ that value, repeat
     *      NOTE: this logic forces 2 further wrinckels A) if you get to index & its value == index skip, and B) after getting pulled on this kind of tangent, need to fallback so as to not miss processing some of the list
     * But! if you do all that (which is an O(n)-ish path, assuming the future-cascades aren't too degenerate),
     * then what you should get is a sorted & compacted view of all the values in the list betweeen 0 & N w/ nulls
     * and by looping the list a second time and returning the first position which is empty you get the ans in
     * something that at worst will be 3 passes through the list (1x as a "future-cascade" that goes through the whole list,
     * 1x in the follow up when the "actual pointer" goes through and skips everything for value==index, and 1x as the null
     * check goes through and so returns n).
     *
     * Hmm... at it even looks like 0 is out of bounds, so I can even use that first pos of the arg as free storage - lol
     * Ok, here we go - first pass, wish me luck
     *
     * Take 2: so I tripped over the test case of {1}... which seems to center around uncompressible seq (ie 1,2,3...n)
     * which either or do not contain 0 (which in turn throws off if I need to do length || length+1 as default return
     *
     * ...this probably deserves another iteration.
     */

    public int firstMissingPositive(int[] nums) {

        // do the initial pseudo-sort
        int index = 0;
        int value = nums[0];
        nums[0] = Integer.MIN_VALUE;

        while(true) {

            // if value is outside of the scope of our ary, null and move on
            if(value <= 0 || value >= nums.length) {
                nums[index] = Integer.MIN_VALUE;
                index++;
                if(index >= nums.length) {
                    break;
                }
                value = nums[index];
                continue;
            }

            // if value is already "correctly positioned" just skip it
            if(value == index) {
                index++;
                if(index >= nums.length) {
                    break;
                }
                value = nums[index];
                continue;
            }

            // at this point, implicitly, all numbers are both inbounds but out of position

            // if value falls in the cleared area, just position it and move on
            // it doesn't matter if this is overwriting a null || a copy of itself
            if(value < index) {
                nums[value] = value;
                index++;
                if(index >= nums.length) {
                    break;
                }
                value = nums[index];
                continue;
            }

            // this is the tricky one, value is inbounds but falls in the non-cleared area
            nums[0] = nums[value]; // capture the value at the tgt location
            nums[value] = value; // assign the tgt location w/ the current value
            value = nums[0]; // assign next evaluation value to be that future one (and cross fingers it hits one of the 3 degenerate cases soon)
        }

        // and finally, do the pass for nulls
        index = 1;
        while (index < nums.length) {
            if(nums[index] == Integer.MIN_VALUE) {
                return index;
            }
            index++;
        }
        return nums.length; // default value if listed is populated 1,2,3,...n
    }
}
