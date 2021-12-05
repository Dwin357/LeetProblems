package io.github.dwin357.leetcode.tens.oneFive;

import java.util.*;

public class AltSolution {

    /*
     * Meditation: If I want to and use my 2Sum soltuion, one idea is to create my lookup map & run a double nested for loop
     *
     * We can see how well this stacks up
     * Runtime: 1802 ms, faster than 5.00% of Java online submissions for 3Sum.
     * Memory Usage: 45.1 MB, less than 25.36% of Java online submissions for 3Sum.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // create a map-list w/ the key as the orig-val & the val as the original referencing keys
        // essentially inverting the data struct
        Map<Integer, Set<Integer>> cache = new HashMap<>();
        Set<Integer> position;
        for(int i=0; i<nums.length; i++) {
            position = cache.get(nums[i]);
            if(position == null) {
                position = new HashSet<>();
                cache.put(nums[i], position);
            }
            position.add(i);
        }

        // some reusable items
        Set<List<Integer>> collector = new HashSet<>();
        Integer[] out;
        Set<Integer> valueset = cache.keySet();
        Integer missingPart;
        // reusing from above: Set<Integer> position;
        int pointerCount;

        // look at every combo of units
        for(int i=0; i< nums.length-1; i++) {
            for(int j=i+1; j<nums.length; j++) {

                // for each combo
                //// calculate the gap & reference cache
                missingPart = -1*(nums[i] + nums[j]);
                if(valueset.contains(missingPart)) {

                    //// ensure that we are not inadvertently ref i or j (since they are also in cache)
                    position = cache.get(missingPart);
                    pointerCount = position.size();
                    if(position.contains(i)) {
                        pointerCount--;
                    }
                    if(position.contains(j)) {
                        pointerCount--;
                    }

                    //// if there is at least 1 valid pointer, capture the valid combination
                    if(pointerCount > 0) {

                        // I don't like this - but it is the easiest way to make sure items collide in the collector set
                        // personally, I would prefer negotiating w/ my consumer on if they would accept Set<Set<Integer>>
                        out = new Integer[] {nums[i], nums[j], missingPart};
                        Arrays.sort(out);
                        collector.add(Arrays.asList(out));
                    }
                }
            }
        }

        return new LinkedList<>(collector);
    }
}
