package io.github.dwin357.leetcode.tens.oneEight;

import java.util.*;

public class SolutionBenchmark {

    /*
     * starting from the ground up to see if anything jumps out at me...
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort the list as a pre-req
        Arrays.sort(nums);

        return fourNestedForLoop(nums,target);
//        return fourNestedTwist(nums,target);
    }

    /*
     * Now logically it shouldnt matter which direction I run the loop,
     * so to demonstrate that I can flip the inner loop to run backwards
     * NOTE: n has also changed from length of the list to the index of the last element
     */
    private List<List<Integer>> fourNestedTwist(int[] nums, int target) {
        List<List<Integer>> collector = new LinkedList<>();
        int n = nums.length-1;
        int i = 0;
        int j;
        int k;
        int l;
        int sum;
        while(i < n-2) {
            j = i +1;
            while(j < n-1) {
                k = j+1;
                while(k < n) {
                    l = n;
                    while(l > k) {
                        sum = nums[i]+nums[j]+nums[k]+nums[l];
                        if(sum == target) {
                            collector.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        }
                        l--;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        return collector;
    }

    /*
     * This is pure brute force
     * - this correctly solves all test cases and is our baseline
     */
    private List<List<Integer>> fourNestedForLoop(int[] nums, int target) {
        List<List<Integer>> collector = new LinkedList<>();
        int n = nums.length;
        int i = 0;
        int j;
        int k;
        int l;
        int sum;
        while(i < n-3) {
            j = i +1;
            while(j < n-2) {
                k = j+1;
                while(k < n-1) {
                    l = k +1;
                    while(l < n) {
                        sum = nums[i]+nums[j]+nums[k]+nums[l];
                        if(sum == target) {
                            collector.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        }
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        return collector;
    }
}
