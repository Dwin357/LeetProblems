package io.github.dwin357.leetcode.tens.oneEight;

import java.util.*;

public class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort the list as a pre-req
        Arrays.sort(nums);

        // build set of outer co-ordinates (in a "6sum" problem, this would happen 2x recursively)
        Set<Tuple> coordinates = new HashSet<>();
        createTupleWithChildren(0,nums.length-1, nums,target,coordinates);

        System.out.println(coordinates);
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


    private void createTupleWithChildren(int i, int l, int[] nums, int tgt, Set<Tuple> collector) {
        // add the designated tuple to the result-set
        collector.add(new Tuple(i,l));

        // add [i,l-1] if...
        // new position has enough space for inner pointers i+2<l-1
        // and smallest cell in new rage [i,i+1,i+2,l-1] is <= tgt
        // and collector doesn't already have item (short circuit multiple cascades)
        if((i+2 < l-1) && (nums[i]+nums[i+1]+nums[i+2]+nums[l-1] <= tgt) && !collector.contains(new Tuple(i,l-1))) {
            createTupleWithChildren(i,l-1,nums,tgt, collector);
        }
        // add [i+1,l] if...
        // new range has enough space for inner pointers i+3<l
        // and biggest cell in new range [i+1,l-2,l-1,l] >= tgt
        // and collector doesn't already have item (short circuit multiple cascades)
        if((i+3 < l) && (nums[i+1]+nums[l-2]+nums[l-1]+nums[l] >= tgt) && !collector.contains(new Tuple(i+1,l))) {
            createTupleWithChildren(i+1,l,nums,tgt,collector);
        }
        // add [i+1,l-1] if...
        // new range has enough space for inner pointers i+3<l-1
        // and the pair perfectly balance on the pivot [i]+[l] == tgt
        // and collector doesn't already have item (short circuit multiple cascades)
        if((i+3<l-1) && (nums[i]+nums[l] == tgt) && !collector.contains(new Tuple(i+1,l-1))) {
            createTupleWithChildren(i+1,l-1, nums,tgt,collector);
        }
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
