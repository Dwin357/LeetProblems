package io.github.dwin357.leetcode.tens.oneEight;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SolutionOne {
    /*
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
         - 0 <= a, b, c, d < n
         - a, b, c, and d are distinct.
         - nums[a] + nums[b] + nums[c] + nums[d] == target
        You may return the answer in any order.
     *
     * yikes - that is kind of a mess.
     * Initial observations
     *  - (Setting aside the priming from the name "4sum") the presence of a tgt num to act as a pivot jumps out (aren't I glad I spilt blood mixing it up w/ that)
     *  - However, now that we have expanded to a set of 4, this interesting space seems to have opened up where we can maybe run a L & R pivot, & a "meta-pivot" between them
     *      ...honestly, it is kind of trippy
     *  - Instinct is tugging my sleeve that there is something in the fine detail work of elements needing to maintain distinct identity, but equivalent el
     * being allowed to show up in a result-set as long as there are multiple copies in the underlying ...but my mind doesn't have the focus to bring that into resolution.
     *
     * Ok - just for a place to start the super naive solution is a four-nested-for-loop
     * for(i=0; i<n-4; i++)
     *  for(j=i+1; j<n-3; j++)
     *   for(k=j+1; k<n-2; k++)
     *    for(l=k+1; l<n-1; l++)
     *
     * Furthermore - I know that under the right conditions (which these clearly seem designed to be) I can replace 2 for-loops w/ a march to the middle
     * and in this case that would be the k&l loops (since they are the most expensive) & leave the 2 outter loops (since they are the cheapest)
     *
     * But given that there are still 2 for-loops, it feels like I should be able to do this trick again -- but that doesn't work on the i & j loops
     *
     * And I think that is the key insight here - the for-loop works like a construction grader, pushing data in the one direction.
     * you can see it in the way the higher letters always stack to the left & bound the lower letters.
     * In contrast, the march to the middle acts like a vice (go figure), squeezing its subsidiaries together.
     *
     * Therefore, in order to do a nested-march, the outer-march would need to be i&l while the inner-march would be j&k
     *
     * And that just leaves the question of the pivots.
     * The inner pivot is easy, b/c at that point in time you are working with fixed values for i & l which just turns into a modified tgt
     * The outer pivot is trickier, b/c at that point j & k are effectively undefined, but this is where that vice idea comes in.
     * I resolve the outer pivot as if j & k did not exist (ie just pivot on the tgt) b/c at this stage I am not looking to solve the problem,
     * just rationally constrain the problem space so the next j+k run can go hunting for solutions w/in that modified & restricted problem space.
     *
     * I can feel there is something else here that keeps swimming away from my fingers, but I think this should be enough to work.
     *
     * [later] - so I found the something else (or at least A something else).  When the outer loop exactly matches the pivot, it creates this ambiguity
     * where either [l+1, r] or [l,r-1] could be valid ...but whichever one you pick, if you continue iteration you lose the other one.
     * So, what you need to do (I think) is supercede the normal pivot behavior to specifically check [l+1,r] and [l,r-1] before resuming normal pivot checks at [l+1,r-1].
     * Also, if either side of this cris-cross advances to a copy of that sides present value - that side can be ignored
     * ie for pivot-0 on [-3,-2, ... 2, 3, 3], position [0,n] represents a balanced pivot.  So normally that BOTH [0,n-1] & [1,n] would need to be ckd
     * as special edge cases, before resuming normal pivot processing at [1,n-1].  However, b/c [0,n-1] replicates [0,n], that side can be skipped and
     * a compress should eb applied to that side before continuing.
     *
     * Update: so it turns out all of this heart burn is (possibly) b/c I had the > sign backward on the
     * outer pivot evaluation...  which (even if this works) I think is the code telling me it is too complicated
     * and should be simplified if possible.
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort the list as a pre-req
        Arrays.sort(nums);

        // setup some reusable vars
        List<List<Integer>> collector = new LinkedList<>();
        int oL = 0; // outer left
        int oR = nums.length-1; // outer right
        int oSum; // outer sum
        int oPiv; // outer piviot
        boolean ccR = false; // cris-cross right flag
        boolean ccL = false; // cris-cross left flag
        boolean ccrs = false; // cris-cross reset flag
        int ccrR = -1; // cris-cross recover R index
        int ccrL = -1; // cris-cross recover L index
        int cciL = -1; // cris-cross index-cache L
        boolean runInner = true;
        int iL = oL +1; // inner left
        boolean ilm; // inner left move flag
        int iR=oR -1; // inner right
        boolean irm; // inner right move flag
        int iTgt; // inner target
        int iPiv; // inner pivot

        // while outters are far enough apart that inners can operate
        while(oL+2<oR) {

            // semi-reusable vars
            oSum = nums[oL] + nums[oR];
            iL = oL +1;
            iR = oR -1;
            iTgt = target - oSum;

            if(runInner) {
                // do inner stuff
                while(iL < iR) {
                    iPiv = nums[iL] + nums[iR] - iTgt;
                    ilm = false;
                    irm = false;

                    if(iPiv == 0) {
                        collector.add(Arrays.asList(nums[oL], nums[iL], nums[iR], nums[oR]));
                        ilm = true;
                        irm = true;
                    } else if(iPiv > 0) {
                        irm = true;
                    } else {
                        ilm = true;
                    }

                    // adv pointer & compress dupes on any side flagged for movement
                    if(ilm) {
                        do {
                            iL++;
                        } while(iL < iR && nums[iL] == nums[iL-1]);
                    }
                    if(irm) {
                        do {
                            iR--;
                        } while(iL < iR && nums[iR] == nums[iR+1]);
                    }
                }
            }
            runInner = true; // only ever skip 1 inner cycle (this only happens when the pivot

            // increment outers
            //// if we are in a cris-cross scenario, that takes precedence over the pivot
            if(ccL) {
                ccL = false; // only do left cris-cross once per flag
                cciL = oL; // bookmark current outer-left pointer location in cache
                ccrL = oL+1; // set recovery index to 1 fwd

                // if the future state is same as current stat, no need to eval this side; just advance recovery pointer
                if(ccrL<oR && nums[oL] == nums[ccrL]) {
                    // compress bookmark
                    do {
                        ccrL++;
                    } while(ccrL<oR && nums[ccrL] == nums[oL]);

                // if the future state is not same as current, run iteration with recover state
                } else {
                    oL = ccrL;
                    continue;
                }

            }
            if(ccR) {
                ccR = false; // only do right cris-cross once per flag
                ccrR = oR-1; // set recovery index to 1 back
                oL = cciL; // set left side back to its indexed value ...idexing this value might be a pain

                // if the future state is same as current stat, no need to eval this side; just advance recovery pointer
                if(oL<ccrR && nums[oR] == nums[ccrR]) {
                    // compress bookmark
                    do {
                        ccrR--;
                    } while(oL<ccrR && nums[oR] == nums[ccrR]);

                // if the future state is not same as current, run iteration with recover state
                } else {
                    oR = ccrR;
                    continue;
                }
            }
            if(ccrs) {
                ccrs = false; // only do cris-cross recovery once per flag
                oL = ccrL; // set outer L to recover value
                oR = ccrR; // set outer R to recovery value
                continue; // run new cycle
            }

            oPiv = oSum - target;
            //// if pivot balances, skip next inner-run &
            if(oPiv == 0) {
                runInner = false;
                ccL = true;
                ccR = true;
                ccrs = true;
                continue;
            ////if sum is low, left moves in
            } else if(oPiv < 0) {
                // advance, and compress duplicates if found -w/out over running partner
                do {
                    oL++;
                } while(oL<oR && nums[oL] == nums[oL-1]);
                //// otherwise, right moves in
            } else {
                // advance, and compress duplicates if found -w/out over running partner
                do {
                    oR--;
                } while(oL<oR && nums[oR] == nums[oR+1]);
            }
        }
        return collector;
    }
}
