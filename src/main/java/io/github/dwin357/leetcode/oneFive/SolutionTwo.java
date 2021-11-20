package io.github.dwin357.leetcode.oneFive;

import java.util.*;

public class SolutionTwo {
    /*
     * New plan, we are going to try for that nested ary business...
     * Observations
     *  - any point where any of the indexs ==, that violates an obj identity (and so can be skipped) - this is part of the given
     *  - with running a triple nested loop, the potential problem space is gigantic, so the name of the seems to be applying constraints?
     *  - related to this point, instinct tells me that at no point should i + j + k be allowed to exceed.
     *      this is representitive of the fact that [a, mirrror-z] & [mirror-a, z] are not meaningfully distinct from each other.  They both represent a self-join of [a,z].
     *
     * Correlated idea, when inner loops reset, they never need to reset to before their underlying loop (I think)...
     *
     * given: [-1,0,1,2,-1,-4]
     * return: [[-1,-1,2],[-1,0,1]]
     *
     * given: [A,B,C,D,E,F] all possible combos
     * ABC, ABD, ABE, ABF
     * ACD, ACE, ACF
     * ADE, ADF
     * AEF
     * BCD, BCE, BCF
     * BDE, BDF
     * BEF
     * CDE, CDF
     * CEF
     * DEF
     * for(i=0; i<(n-2); i++)
     *  for(j=0; j<(n-2-i); j++)
     *   for(k=0; j<(n-2-i-j); k++) ... I think
     *
     * Again - time limit exceeded
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> results = new HashSet<>();
        int n = nums.length;
        for(int i=0; i<n-2; i++) {
            for(int j=(i+1); j<(n-1); j++) {
                for(int k=(j+1); k< (n); k++) {
                    if((nums[i]+nums[j]+nums[k]) == 0) {
                        Integer[] unsorted = new Integer[]{nums[i], nums[j], nums[k]};
                        Arrays.sort(unsorted);
                        results.add(Arrays.asList(unsorted));
                    }
                }
            }
        }
        return new ArrayList<>(results);
    }
}
