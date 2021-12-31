package io.github.dwin357.leetcode.thirties.threeThree;

public class Solution {
    /*
     * Meditation: so this is perfectly functional code...
     * but I have noticed that leetcode pushes me to write in a certain style which I do not like
     * I assume b/c of the competitive nature of showing the percentages combined with the fact that
     * code which is easy for machines is not necessarily easy for humans (b/c different constituencies)
     *
     * This leverages objects to decompose logic into bite-sized chunks and slaps nice labels on everything
     * in the form of method names.  Much better code in my opinion.  So now we see how it holds up in the competition.
     *
     * Runtime: 1 ms, faster than 19.36% of Java online submissions for Search in Rotated Sorted Array.
     * Memory Usage: 39.5 MB, less than 11.38% of Java online submissions for Search in Rotated Sorted Array.
     * ...mixed feelings about that
     */

    public int search(int[] nums, int target) {
        return new ObjectOriented(nums,target).perform();
    }

    private class ObjectOriented {
        private final int[] nums;
        private final int target;

        private ObjectOriented(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
        }

        public int perform() {
            return isValid() ? happyPath() : degenerateCase();
        }

        ////////////////////////////
        ////  Processing Logic  ////
        ////////////////////////////

        private int happyPath() {
            return isUncutAry() ? handleUncutAry() : handleCutAry();
        }

        private int handleCutAry() {
            if(targetWithinStart()) {
                return searchFromFront(true);
            } else if(targetWithinEnd()) {
                return searchFromEnd();
            } else {
                return invalidFlag();
            }
        }

        private int handleUncutAry() {
            return isPlausibleForUncutAry() ? searchFromFront(false) : invalidFlag();
        }

        private int searchFromFront(boolean checkForCrossover) {
            for(int i=0; i < nums.length; i++) {
                if(elementMatchesTarget(i)) {
                    return i;
                }
                if(elementExceedsTarget(i)) {
                    return invalidFlag();
                }
                if(checkForCrossover && elementCrossesOverListCutFromLeft(i)) {
                    return invalidFlag();
                }
            }
            throw new RuntimeException("Ary marked as valid could not be determined with front search");
        }

        private int searchFromEnd() {
            for(int i=nums.length-1; i >=0; i--) {
                if(elementMatchesTarget(i)) {
                    return i;
                }
                if(elementCrossesOverListCutFromRight(i) || elementPreceedsTarget(i)) {
                    return invalidFlag();
                }
            }
            throw new RuntimeException("Ary marked as valid could not be determined with back search");
        }

        private int degenerateCase() {
            return nums[0] == target ? 0 : invalidFlag();
        }

        private int invalidFlag() {
            return -1;
        }



        ///////////////////////////
        ////  Branching Logic  ////
        ///////////////////////////

        private boolean isValid() {
            return nums.length != 1;
        }

        private boolean isUncutAry() {
            return nums[0] < nums[nums.length-1];
        }

        private boolean isPlausibleForUncutAry() {
            return targetWithinStart() && targetWithinEnd();
        }

        private boolean targetWithinStart() {
            return nums[0] <= target;
        }

        private boolean targetWithinEnd() {
            return target <= nums[nums.length-1];
        }

        private boolean elementMatchesTarget(int i) {
            return nums[i] == target;
        }

        // given: [5,6,9,1,2,4], tgt:8, i:2 THEN true
        private boolean elementExceedsTarget(int i) {
            return nums[i] > target;
        }

        // given: [5,6,7,1,2,4], tgt:3, i:4 THEN true
        private boolean elementPreceedsTarget(int i) {
            return nums[i] < target;
        }

        // given: [5,6,7,1,2,4], tgt:0, i:2 THEN true
        private boolean elementCrossesOverListCutFromRight(int i) {
            return nums[i] > nums[nums.length-1];
        }

        // given: [5,6,7,1,2,4], tgt:8, i:3 THEN true
        private boolean elementCrossesOverListCutFromLeft(int i) {
            return nums[i] < nums[0];
        }
    }
}
