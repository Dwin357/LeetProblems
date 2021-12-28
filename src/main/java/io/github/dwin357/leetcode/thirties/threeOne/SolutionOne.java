package io.github.dwin357.leetcode.thirties.threeOne;

import java.util.Arrays;

public class SolutionOne {
    /*
     * Meditation: so I am suddenly six yrs old again where the adults are talking using big funny words and I have
     * only an emotional / intuitive sense of what is actually going on...

     * "Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
        If such an arrangement is impossible, it must rearrange it to the lowest possible order (i.e., sorted in ascending order)."
     *
     * that said, at first blush this looks like a "baby's first sort" problem where I am trying to sort the list into
     * ascending order - unless it already is in ascending order (in which case I just flip the last two elements).
     *
     * I wonder, Arrays.sort() is probably a better impl of sorting than anything I could come up with off the cuff...
     * so what if I rearrange the problem such that
     * - loop the ary 1x cking (el.n >= el.n-1)
     *  -- if this ever is not true, break the loop and sort the ary w/ Arrays.sort()
     *  -- if you make it through the whole list, that means it is already sorted & you just need to flip the last el
     *
     * Seems like a reasonable first pass, considering I am just guessing at the aim based on the test cases
     *
     * So...  This is wrong b/c, when given 1,3,2 you are supposed to get 2,1,3 ...???
     * I don't understand.
     */
    public void nextPermutation(int[] nums) {
        // dont want to deal w/ 1 length arys
        if(nums.length == 1) {
            return;
        }

        boolean isSorted = true;
        int i = 1;
        while(i < nums.length) {
            if(nums[i-1] > nums[i]) {
                isSorted = false;
                break;
            }
            i++;
        }

        if(isSorted) {
            // when sorted, just flip last 2 position
            i = nums[nums.length-1]; // cache last element
            nums[nums.length-1] = nums[nums.length-2]; // assign 2nd last el to last position
            nums[nums.length-2] = i; // assign 2nd last el from cache
        } else {
            // when not sorted, just sort ary
            Arrays.sort(nums);
        }
    }
}
