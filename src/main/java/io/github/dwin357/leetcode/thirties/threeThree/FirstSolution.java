package io.github.dwin357.leetcode.thirties.threeThree;

public class FirstSolution {
    /*
     * Problem: So the setup is that nums is a sorted ary of uniq values
     * BUT somewhere in the ary it MAY have been "cut" and the 2 halves flipped.
     *
     * naiev thinking
     * - ck the first & last elements
     * -- if last > first then ary was not cut (treat as one ordered list)
     *  -- if (first < tgt && tgt < last) then search list until i == tgt (break if i > tgt)
     *     else rtn -1
     * -- if last < first then ary was cut (treat as two ordered lists)
     *  -- if (first < tgt) then search list from front until i == tgt (break if i > tgt || i < i-1)
     *  -- if (last > tgt) then search list from back until i == tgt (break if i < tgt || i > i-1)
     *  -- else rtn -1 (ie the tgt falls inside the split)
     *
     * So that looks like a branching nightmare in terms of writing clean code but I don't see any gotchas
     * (now watch this is one of those I have to look up b/c everything I write "times out")
     *
     * This is horrible code - if it passes I should take a second pass to clean it up
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
     * Memory Usage: 38.6 MB, less than 35.56% of Java online submissions for Search in Rotated Sorted Array.
     */
    public int search(int[] nums, int target) {
        // handle n == 1 case :: will see if this can be absorbed later
        if(nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }


        int i;
        // case: uncut ary
        if(nums[0] < nums[nums.length-1]) {
            // if tgt is at least as big as first, an no bigger than last, it is plausible to search
            if(nums[0] <= target && target <= nums[nums.length-1]) {
                i = 0;
                while(i < nums.length) {
                    if(nums[i] == target) {
                        return i;
                    } else if(nums[i] > target) {
                        return -1;
                    } else {
                        i++;
                    }
                }
                return -1;
            } else {
                return -1;
            }

        // case: cut ary (ie last < first), but since all el are uniq no need to explicitly ck
        } else {
            // case:
            if(nums[0] <= target) {
                i = 0;
                while(i < nums.length) {
                    if(nums[i] == target) {
                        return i;
                    } else if(nums[i] > target) {
                        return -1;
                    } else if(nums[i] < nums[0]) {
                        return -1;
                    } else {
                        i++;
                    }
                }
                return -1;
            } else if (target <= nums[nums.length-1]) {
                i = nums.length-1;
                while(i >= 0) {
                    if(nums[i] == target) {
                        return i;
                    } else if(nums[i] < target) {
                        return -1;
                    } else if(nums[i] > nums[nums.length-1]) {
                        return -1;
                    } else {
                        i--;
                    }
                }
                return -1;
            } else {
                return -1;
            }
        }
    }
}
