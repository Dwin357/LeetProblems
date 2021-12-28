package io.github.dwin357.leetcode.thirties.threeOne;

import java.util.Arrays;

public class Solution {
    /*
     * Credits Due: https://www.youtube.com/watch?v=quAS1iydq7U (Back To Back SWE)
     * So this guy is actually really good at explaining what is going on here in terms of the reasoning.
     *
     * To restate what he said in my own words - the 2 ideas you really want to lean on are a) sets, and b) base-x counting
     *
     * To start, all the elements in your ary define a set; and, just to be through in my definitions, any given order
     * of the ary defines a specific permutation of the elements w/in the set.
     * So, in this sense, as we tick through permutations we are maintaining a "set of unassigned elements" that we can
     * draw from when we want to assign an ary position and/or which we return elements to when we want to free up an
     * ary-pos.
     * Also, an important detail is that the items w/in the set of unassigned elements have an order relative to one another
     * ...I think this whole scheme breaks down if you take that away.
     *
     * Anyway - there is an (ordered) set of all elements, and a (similarly ordered) subset of all unassigned elements
     * -- gucci, moving on
     *
     * Next idea comes from base.X counting
     * when counting base.10: if I have 9, and I want to add 1 to it, I end up with 10 b/c the addition "overflows" the
     * ones position and increments the tens.  Similarly, if I have 101 in base.2 (ie 5) and I add 1 to it, I get 110 b/c
     * the "one"s position overflows into incrementing the "two"s value.
     * And, a natural extension of this idea is that digits which are more to the left of a number are "more powerful"
     * in determining its size than digits to the right.  IE if I told you you could set the nth digit of your salary
     * (ie the ones digit) to whatever you want - you would probably roll your eyes at me; but if I told you you could
     * set the (n-5)th digit (ie the hundred-thousands) to whatever you want that suddenly becomes a really good deal.
     *
     * So now that you have in mind those 2 ideas which you were already familiar with, here is where the twist comes in
     * but first a step sideways.
     *
     * When doing base.x counting, a different way to describe what you are doing is that for each digit you are
     * ACTUALLY picking an element from a "set of possible values" which is (0...x-1) BUT at each order of magnitude
     * this set refreshes itself; so the fact that you picked a 3 for the ones doesn't prevent you from picking a 3 for
     * the tens.
     *
     * And, if we look at counting in that light, it is a pretty simple jump to say, "those rules but...
     * - rather than the set refreshing at each order of magnitude/each digit, every position must draw from the same pool
     * &
     * - rather than the digit's set being defined as (0...base-1) it is defined as the initial collection
     *
     * Now as a trick that is kind of neat, associating as it does set permutations with base.x counting ... but if the
     * elements in our set are colors (for instance) which lack a linear order amongst themselves, this added formalism
     * doesn't really seem to do anything for us except as trick to pull out at parties to show how clever we are.
     * Really, the value of this work is only realized when we are able to meaningfully compare elements w/in the set
     * b/c under those circumstances we are able to arrange permutations w/in the set into a sort of "number line"...
     * which ... I actually have no idea why you would want to do that except you are working problem which calls for a
     * "NEXT permutation" and philosophically the number line (as the embodiment of a sequence) is the only way to arrive
     * at the true meaning behind the word next - LOL.
     * I say 42 angels can dance on the head of a pin, no more and no less, and I will fight you if you say otherwise.
     *
     * So, to lean on the counting idea: if I give you 359899 and ask "what is the next number", you know it is 359900
     * How did you work that out, b/c that will be the basis how we find the "next" perm.
     * > well, I went to the end of the number saw a 9.  I then imagined adding 1 to it, which would be 10.  B/c 10
     * overflows a single digit in base.10 I then went to the tens digit and repeated the process.  This again overflowed
     * so I repeated the process - this time getting 8+1 in the hundreds position.  Finally, I backfilled all the positions
     * that overflowed with their minimum possible value in order to allow the hundreds position to maintain its previous
     * order of magnitude.  The first 3 digits were untouched in my analysis b/c the process short-circuited before I
     * got to them.
     *
     * Now, to map this process onto what we do with counting we have to ans the Q, "what does it mean for a seq to be
     * at risk for overflowing" (ie like 99 is).  The ans (which I absolutely got from other people) is that "it is the
     * maximum value possible for the observed sequence".
     *
     * So, to follow this logic
     * - look at last digit (9) -> digit is max possible, move left
     * - look at next digit (9, but implicitly 99) -> digit is max possible, move left
     * - look at next digit (8, but again 899) -> digit is not max
     * -- advance digit 1 position (8 -> 9)
     * -- back fill everything before advancement w/ min value (ie 0s)
     *
     * So to bring all this together: given 6215430, what is the next biggest perm?
     * - look at 0, it is >= to all members in the sub-set [0] & therefore is max possible
     * - look at 3, it is >= 0 which means it is >= all members in the sub-set [0 3] & therefore is max possible
     * - look at 4, it is >= 3 which means it is >= all members in the sub-set [0 3 4] & therefore is max possible
     * - look at 5, it is >= 4 which means it is >= all members in the sub-set [0 3 4 5] & therefore is max possible
     * - look at 1, it is not >= 5 which means it is not >= all members in the sub-set [0 1 3 4 5] & therefor needs to be incremented
     * -- IMPORTANTLY: this means the values to the left of the 1 (ie 6 & 2) are not touched
     *  62????? [0 1 3 4 5]
     * -- to "increment" the 1, we need to assign this position to the next highest value in the sub-set (ie the 3)
     * IMPORTANTLY: this leaves the 0 in the set, b/c it is less than the 1, and we need to increment upwards
     *  623???? [0 1 4 5]
     * -- Finally, we back-fill with the remaining elements of the set in the minimum state (ie ascending order)
     *  CONVENIENTLY: b/c the nature of the search has ensured that everything behind the search is in decending order,
     *   if the 1 and the 3 are swapped then everything after the 1 just needs to be reversed to be in ascending order
     *
     * So, the algo is then
     * - process "number" from the back
     * - for each position starting at n-1
     *  -- if i-1 >= i then i--
     *  -- else
     *    --- loop processed part of the list for smallest element > i (I don't think this one gets the eq)
     *    --- transpose i w/ the smallest greater element id above
     *    --- break out of the for loop
     *  - re-using the i pointer, reverse the list from i to the end
     *     -- NOTE: if the for loop went all the way through & never broke, this reverses the whole list, otherwisee just the part after i
     *
     * Edge cases I can see
     * - my comparison stuff breaks on lists n=1 (which are valid)
     * - im having a hard time visualizing where eq are ok (overflow section I think) vs where they are not (transpose i think)
     * - if I have a list of 2 and I transpose the elements then reverse the list isn't that a wash
     * (but maybe I only reverse the tail of 1 in which case it is fine?)
     *
     * Well that seemed to work - which was nice b/c it was some work to get there...
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Permutation.
     * Memory Usage: 39.1 MB, less than 72.59% of Java online submissions for Next Permutation.
     */

    public void nextPermutation(int[] nums) {
        // find transpose index
        int i = nums.length - 2;
        int swap;
        int cache;
        while(i >= 0) {
            if(nums[i] >= nums[i+1]) {
                i--;
            } else {
                swap = nums.length-1;
                while(nums[swap] <= nums[i]) {
                    swap--;
                }
                cache = nums[i];
                nums[i] = nums[swap];
                nums[swap] = cache;
                break;
            }
        }

        swap = nums.length-1;
        i++;
        while(swap > i) {
            cache = nums[i];
            nums[i] = nums[swap];
            nums[swap] = cache;
            i++;
            swap--;
        }
    }
}
