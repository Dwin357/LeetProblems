package io.github.dwin357.leetcode.ouaghts.one;

import java.util.HashMap;
import java.util.Map;

/*
 * Runtime: 1 ms, faster than 99.68% of Java online submissions for Two Sum.
 * Memory Usage: 39.2 MB, less than 66.25% of Java online submissions for Two Sum.
 */

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> catalog = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {

            if(catalog.containsKey(nums[i])) {
                return new int[] {catalog.get(nums[i]), i};
            }

            catalog.put(target - nums[i], i);
        }

        return null;
    }
}
