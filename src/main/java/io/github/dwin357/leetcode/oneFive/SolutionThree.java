package io.github.dwin357.leetcode.oneFive;

import java.util.*;

public class SolutionThree {
/*
 * New plan... if we sort the list prior to running the loops, given the way i < j < k,
 * we obviate the need to sort them after the fact (as long as we retreive them i,j,k)
 *
 * Furthermore (and importantly) this will also give the ability to break the inner loop
 * when we exceed the threshold which will short circuit processing
 */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> results = new HashSet<>();
        int n = nums.length;
        int sum;

        Arrays.sort(nums);
        for(int i=0; i<n-2; i++) {
            for(int j=(i+1); j<(n-1); j++) {
                for(int k=(j+1); k< (n); k++) {
                    sum = nums[i]+nums[j]+nums[k];
                    if(sum < 0) {
                        continue;
                    }
                    if(sum == 0) {
                        results.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k]}));
                    } else {
                        break;
                    }
                }
            }
        }
        return new ArrayList<>(results);
    }
}
