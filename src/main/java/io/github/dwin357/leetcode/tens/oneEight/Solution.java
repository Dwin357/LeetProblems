package io.github.dwin357.leetcode.tens.oneEight;

import java.util.*;

public class Solution {

    /*
     * Took some time off from this problem, but I think I have come back with some good insights.
     * given:{-5,-4,-3,-2,1,3,3,5}, you can represent that info with the following tree
     *                 [-5, 5]
     *                 -7   6
     *             [-5, 3] [-4, 5]
     *             -9   2  -4   7
     *         [-5, 3] [-4, 3] [-3, 5]
     *         -9   3  -6   3   1   8
     *     [-5, 1] [-4, 3] [-3, 3] [-2, 5]
     *     -11  -9 -6  -2  -1   4   7   9
     * [-5,-2] [-4, 1] [-3, 3] [-2, 3] [ 1, 5]
     *
     * the [braces] are outer pairs, and each "child node" represents either a step-in
     * from the right or the left.
     *
     * These outer-pairs represent sets, so [-5,1] is representing the 3 combinations [-5,-4,-3,1](-11) [-5,-4,-2,1](-10) [-5,-3,-2,1](-9)
     * And here we see something interesting, b/c the underlying list is sorted, every combo is also sorted
     * ...so by looking at the first / last combo in a set, you can make assertions about the whole set
     * ie: if the min is above our tgt or max is below our tgt, you can skip processing the whole set
     *
     * This gives rise to the idea of "set viability" and also provides directionality in terms of seeking smaller/bigger sets
     *
     * Unfortunately, when a set is viable, no such insight can be gained and so all 3 directions (L,R,Down) need to be
     * tested for viability
     *
     * (with big integer)
     * Runtime: 40 ms, faster than 29.43% of Java online submissions for 4Sum.
     * Memory Usage: 41.7 MB, less than 16.67% of Java online submissions for 4Sum.
     *
     * (using longs... once I realized I need to change type part-way through the operation)
     * Runtime: 18 ms, faster than 51.73% of Java online submissions for 4Sum.
     * Memory Usage: 41.3 MB, less than 24.87% of Java online submissions for 4Sum.
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort the list as a pre-req
        Arrays.sort(nums);

        // build set of outer co-ordinates (in a "6sum" problem, this would happen 2x recursively)
        Set<Tuple> coordinates = new HashSet<>();
        createTupleWithChildren(0,nums.length-1, nums,target,coordinates);

        // search each location on graph, running a march to the middle
        Set<List<Integer>> results = new HashSet<>();
        int j;
        boolean stepLeft;
        int k;
        boolean stepRight;
        int pivot;
        int modifiedTgt;
        for(Tuple outerLimits : coordinates) {
            modifiedTgt = target - nums[outerLimits.left] - nums[outerLimits.right];
            j = outerLimits.left +1;
            k = outerLimits.right -1;
            while (j < k) {
                pivot = nums[j]+nums[k]-modifiedTgt;
                stepLeft = false;
                stepRight = false;

                if(pivot == 0){
                    results.add(Arrays.asList(nums[outerLimits.left],nums[j],nums[k], nums[outerLimits.right]));
                    stepLeft = true;
                    stepRight = true;
                } else if (pivot > 0) {
                    stepRight = true;
                } else {
                    stepLeft = true;
                }

                if(stepLeft) {
                    do {
                        j++;
                    } while(j < k && nums[j] == nums[j-1]);
                }
                if(stepRight) {
                    do {
                        k--;
                    } while(j < k && nums[k] == nums[k+1]);
                }
            }
        }

        return new LinkedList<>(results);
    }

    private void createTupleWithChildren(int i, int l, int[] nums, long tgt, Set<Tuple> collector) {
        // if tuple doesn't have enough space for inner-pointers, abort --this is the base case
        if(i+2 >= l) {
            return;
        }

        // if we have already collected this tuple, abort --this is a short-circuit to prevent duplicate work
        Tuple il = new Tuple(i,l);
        if(collector.contains(il)) {
            return;
        }

        // if the low is above the tgt: skip processing, pull right side in, try again
        long low = (long)(nums[i]+nums[i+1]) + (long)(nums[i+2] + nums[l]);
        if(low > tgt) {
            createTupleWithChildren(i,l-1,nums,tgt,collector);
            return;
        }

        // if the high is below the tgt: skip processing, pull left side in, try again
        long high = (long)(nums[i] + nums[l-2]) + (long)(nums[l-1] + nums[l]);
        if(high < tgt) {
            createTupleWithChildren(i+1,l,nums,tgt,collector);
            return;
        }

        // if all the above pass, the tuple is "viable"; it should be added to the collector for consideration
        // additionally the adjoining tuples [i+1,l], [i,l-1], & [i+1,l-1] should all be investigated
        collector.add(il);
        createTupleWithChildren(i+1,l,nums,tgt,collector);
        createTupleWithChildren(i,l-1,nums,tgt,collector);
        createTupleWithChildren(i+1,l-1,nums,tgt,collector);
    }



    private class Tuple {
        public final int left;
        public final int right;

        private Tuple(int left, int right) {
            this.left = left;
            this.right = right;
        }

        /*
         * Auto-gen boilder plate
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Tuple tuple = (Tuple) o;

            if (left != tuple.left) return false;
            return right == tuple.right;
        }

        @Override
        public int hashCode() {
            int result = left;
            result = 31 * result + right;
            return result;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
