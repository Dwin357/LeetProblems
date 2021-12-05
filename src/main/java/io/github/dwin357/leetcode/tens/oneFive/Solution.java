package io.github.dwin357.leetcode.tens.oneFive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /*
     * So I finally called uncle and went online to see someone solve this problem.
     * Credit where credit is due, I looked at Nick White's solve here: https://www.youtube.com/watch?v=qJSPYnS35SE.
     *
     * There were 2 big take aways from that.
     * 1) he talked about doing this as a modified version of his 2-sum solution (see problem one)
     * ...which it totally fair and makes me feel a bit sheepish when I say it out loud.
     *
     * However, it turns out his 2-sum solve and mine have nothing to do with each other (the advantage and/or curse of minimal formal training)
     * So the core apparently of his 2sum, which he modified for 3sum, was this "2 pointer thing.
     * It has taken me a lot of frowny-faces and a run to wrap my head around it, but it is kind of a neat trick under the right circumstances.
     * So...
     * 2) Two pointers is a technique for self-joining a list which works under particular conditions.  As such
     *  - when conditions are met, it can replace a nested-for-loop<O(n^2)> with 2 converging pointers processing the list from opposite sides<O(n)>
     *  which on its own is stupid powerful, but this leads into the conditions under which it has to operate
     *
     * So easy limitations first
     * - list needs to be sorted :: fine
     * - an element needs to maintain a uniq ident in the context of the self-join (ie can't join el to itself) :: sure
     * but then there is the big one
     * - the product of the join must not be strictly combinatorial, and should instead provide meaningful info along the axis of the sort
     * Example:
     *  you have a set of numbers [1,2,3,4] & you want to self join the list to arrive at a pair whose product is 6
     *  start at 1 & 4; they multiply to 4 (too low); need to move lower pointer up
     *  next  at 2 & 4; they multiply to 8 (too hight); need to move higher pointer down
     *  next  at 2 & 3; they multiply to 6 (on the money); profit
     *
     * So clearly this works, but a key part of it working is being able to decide which pointer you need to move for the next iteration
     * Example:
     *  you have a set of letters [a,b,c,d] & you want to self join the list to arrive at a pair who combine to bc
     *  start at a & d; they combine to ad... which clearly is wrong, but wrong in which direction.  Which pointer do I need to move?
     *
     * Or a more salient example:
     *  - if I were to loop ary and create a map, I spend an O(n) operation (+memory) to replace future O(n) search-passes w/ an O(k) lookup
     *  - And I naievly thought, "great of the triple-for-loop solution, replace the inner loop w/ the lookup cache & the 2 outter loops w/ a double pointer"
     *     In theory this would reduce a naieve O(n^3) to a 2*O(n) ...but in practice when the 2 pointer goes to the cache to see if there is a perfect thrid
     *     the only response they get is a bool y/n, and in either case it is not clear which pointer needs to move next (it is strictly combinatorial)
     *
     * Meditation: the person I saw do this basically had an outter for-loop and they then ran the 2-pointer solution inside
     * the for loop.  I'll first just try to see if I can recreate that approach for practice & baseline.
     *
     * Runtime: 20 ms, faster than 71.59% of Java online submissions for 3Sum.
     * Memory Usage: 43 MB, less than 69.04% of Java online submissions for 3Sum.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // switch to linked list, b/c it turns out these things can be quite big and I don't want arraylist resizing itself
        List<List<Integer>> collector = new LinkedList<>();

        // need to sort incoming for the trick to work
        Arrays.sort(nums);

        // some reusable var; recycle kids
        int leftPointer;
        int rightPointer;
        int positionSum;
        int lastIndex = nums.length-1;

        // for each element (which becomes the pivot point)
        for(int i=0; i<nums.length-2; i++) {
            // he did a thing I thought was clever, rather than dealing w/ dupes using the set-stuff I was doing
            // he took advantage of the sorted nature of the underlying to do a look-back ck & skip repeated el
            if(i !=0 && nums[i] == nums[i-1]) {
                continue;
            }

            leftPointer = i+1;
            rightPointer = lastIndex;
            while(rightPointer > leftPointer) {
                // this is pivot point at the heart of the solution
                positionSum = nums[i] + nums[leftPointer] + nums[rightPointer];

                // the conditional logic surrounding the piviot
                if( positionSum == 0 ) {
                    collector.add(Arrays.asList(nums[i], nums[leftPointer], nums[rightPointer]));
                    // tick both in on match
                    rightPointer--;
                    leftPointer++;

                    // and move past dups if you are ever on them
                    while(rightPointer != lastIndex &&
                            rightPointer > leftPointer &&
                            nums[rightPointer] == nums[rightPointer+1]) {
                        rightPointer--;
                    }
                    // don't need to guard index out of bounds b/c i will always be behind left pointer
                    while (leftPointer < rightPointer &&
                            nums[leftPointer] == nums[leftPointer-1]) {
                        leftPointer++;
                    }

                } else if(positionSum > 0) {
                    // if high, lower high num
                    rightPointer--;
                } else {
                    // otherwise it is low, raise the low num
                    leftPointer++;
                }


            }
        }

        return collector;
    }
}
