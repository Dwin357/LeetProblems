package io.github.dwin357.leetcode.oneFive;

import java.util.*;

public class SolutionFour {
    /*
     * New plan...
     * I think (maybe) I need to be even more aggressive on my breaks
     *
     * Again, leaning on the fact that J is always greater than I, and K is always greater than J
     * plus the fact that the underlying list is sorted, I can make the assertion that whenever
     * a combination exceeds 0, all further elements in that list and all subsidiary lists will also fail
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> results = new HashSet<>();
        int n = nums.length;
        int sum;

        Arrays.sort(nums);
        for(int i=0; i<n-2; i++) {
            if(nums[i] > 0) {
                break;
            }

            for(int j=(i+1); j<(n-1); j++) {
                if((nums[i]+nums[j]) > 0) {
                    break;
                }

                for(int k=(j+1); k< (n); k++) {
                    sum = nums[i]+nums[j]+nums[k];
                    if(sum > 0) {
                        break;
                    }

                    if(sum == 0) {
                        results.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k]}));
                    }
                }
            }
        }
        return new ArrayList<>(results);
    }
}
