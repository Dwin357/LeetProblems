package io.github.dwin357.leetcode.one;

import java.util.HashMap;
import java.util.Map;



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
