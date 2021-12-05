package io.github.dwin357.leetcode.tens.oneSix;

import java.util.Arrays;

public class Solution {

    /*
     * Meditation: so after all that stuff w/ 2-pointers and pivots, this seems like a pretty straight fwd
     * application of it.
     *
     * Runtime: 3 ms, faster than 98.75% of Java online submissions for 3Sum Closest.
     * Memory Usage: 38.9 MB, less than 45.08% of Java online submissions for 3Sum Closest
     */

    public int threeSumClosest(int[] nums, int target) {

        // reusable vas, waste not want not
        int winningSum = Integer.MIN_VALUE;
        int winningSpread = Integer.MAX_VALUE;
        int contestantSum;
        int contestantSpread;
        int leftPointer;
        int rightPointer;

        // order data
        Arrays.sort(nums);

        // for each position
        for(int i=0; i<nums.length-2; i++) {
            leftPointer = i+1;
            rightPointer = nums.length-1;
            while(rightPointer > leftPointer) {
                contestantSum = nums[i] + nums[leftPointer] + nums[rightPointer];
                contestantSpread = contestantSum - target;

                //// deal with the pivot stuff
                // if we hit the money stop, since there is only 1 correct ans
                if(contestantSpread == 0) {
                    return contestantSum;
                }
                // if spread is high, drop higher number
                if(contestantSpread > 0) {
                    rightPointer--;
                // otherwise (ie spread is low), raise lower number
                } else {
                    leftPointer++;
                }

                //// deal with updating cache if contestant beats current winner
                contestantSpread = Math.abs(contestantSpread);
                if(winningSpread > contestantSpread) {
                    winningSum = contestantSum;
                    winningSpread = contestantSpread;
                }
            }
        }

        return winningSum;
    }
}
