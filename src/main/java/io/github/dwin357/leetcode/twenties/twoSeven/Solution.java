package io.github.dwin357.leetcode.twenties.twoSeven;

public class Solution {
    /*
     * So this looks like it will basically be like 26 (except I guess this will be the simpler impl)
     * Have separate read & write pointers; anytime you hit the tgt value, adv read w/out doing write
     * in the end you rtn the write pointer value
     *
     * It actually took longer to write the tests for this than the code.
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
     * Memory Usage: 39 MB, less than 21.58% of Java online submissions for Remove Element.
     *
     * Ok, I need to ask...  How the F*** do 79% of other solutions use less memory than me?!?
     * I have literally declared only 2 variables???  Where is the fat.  Even if I go down to one index
     * var and keep a relative pointer off of that, I can't get away from having 2 pointers
     *
     * ...I guess I can use shorts, since nums.length is never more than 100; how much a dif does that make?
     *
     * (using shorts instead of ints)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
     * Memory Usage: 37.5 MB, less than 71.88% of Java online submissions for Remove Element.
     * ...I feel kind of stupid now...  but I also feel like I won which is kind of nice.
     */
    public int removeElement(int[] nums, int val) {
        short read = 0;
        short write =0;
        while(read < nums.length) {
            if(nums[read] != val) {
                nums[write] = nums[read];
                write++;
            }
            read++;
        }

        return write;
    }
}
