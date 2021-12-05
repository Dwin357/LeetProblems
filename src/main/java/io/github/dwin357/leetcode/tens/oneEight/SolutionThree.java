package io.github.dwin357.leetcode.tens.oneEight;

import java.util.*;

public class SolutionThree {
    /*
     * Game Plan: as last time, but every time the outer pivot balances, we walk in one direction and push
     * the other direction to a stack that need to be resolved before resolution
     *
     * [later]
     * So this doesn't actually work.  The issue is best summed up as follows
     * given:{-5,-4,-3,-2,-1,0,0,1,2,3,4,5} & tgt 0
     * imagine that we have just completed the set for
     *  i:-5 j:(i<?<k) k:(j<?<l) l:4
     *
     * The next thing we evaluate is the pivot of the outers to decide if we increment the left outer or right outer
     *
     * The way this does it, is b/c -5 and 4 combine to -1 (which is less than the pivot 0) we increment the -5 to -4
     * ...and this results in missing the [-5,0,2,3] solution.
     *
     * Now, as a happy accident, the stack impl means that when I reach these decision points I am allowed to choose
     * "both" when trying to decide which path I want to venture down in terms of incrementing left vs right.
     *
     * Now if I always choose both, my intuition says that results in basically a nested-for-loop by other means...
     * But is it possible to prune the paths at least sometimes - which would still result in some savings (in theory).
     *
     * Taking the example given above (i:-5,l:4)
     * - I want to advance down the (i:-5,l:3) path if-and-only-if (i:-5,j:1,k:2,l:3) is >= pivot
     * By the same token, I want to advance down the (i:-4,l:4) path if-and-only-if (i:-4,j:-3,k:-2,l:4) <= pivot
     *
     * And finally, given that we are now communicating w/ ourselves using the stack as a msg-bus, I think we can get
     * rid of the while loop for the outer pointers and just use the while-loop for the stack being empty
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort the list as a pre-req
        Arrays.sort(nums);

        // setup some reusable vars
        Set<List<Integer>> collector = new HashSet<>();
        int n = nums.length-1;
        int oL; // outer left
        int oR; // outer right
        int oSum; // outer sum
        int oPiv; // outer piviot
        int iL; // inner left
        boolean ilm; // inner left move flag
        int iR; // inner right
        boolean irm; // inner right move flag
        int iTgt; // inner target
        int iPiv; // inner pivot


        Deque<Quintuple> pathsNotTaken = new ArrayDeque<>();
        pathsNotTaken.push(new Quintuple(0,n)); // set starting values in cache

        Quintuple nextPath;
        while(!pathsNotTaken.isEmpty()) {
            nextPath = pathsNotTaken.pop();
            oL = nextPath.ol;
            oR = nextPath.or;

            while(oL+2<oR) {
                // semi-reusable vars
                oSum = nums[oL] + nums[oR]; // a + d
                iTgt = target - oSum; // T - a -d
                iL = oL + 1;
                iR = oR - 1;

                // do inner stuff
                while (iL < iR) {
                    iPiv = nums[iL] + nums[iR] - iTgt; // b + c - T + a + d
                    ilm = false;
                    irm = false;

                    if (iPiv == 0) {
                        collector.add(Arrays.asList(nums[oL], nums[iL], nums[iR], nums[oR]));
                        ilm = true;
                        irm = true;
                    } else if (iPiv > 0) {
                        irm = true;
                    } else {
                        ilm = true;
                    }

                    // adv pointer & compress dupes on any side flagged for movement
                    if (ilm) {
                        do {
                            iL++;
                        } while (iL < iR && nums[iL] == nums[iL - 1]);
                    }
                    if (irm) {
                        do {
                            iR--;
                        } while (iL < iR && nums[iR] == nums[iR + 1]);
                    }
                }


                //// adv outer pointers (either based on pivot || cache)
                oPiv = oSum - target; // a + d -T
                // if outer pivot balances
                if(oPiv == 0) {

                    // cache advance right values
                    pathsNotTaken.push(new Quintuple(oL,oR -1)); // set starting values

                    // check advance left path
                    oL++;

                ////if sum is low, left moves in
                } else if(oPiv < 0) {
                    // advance, and compress duplicates if found -w/out over running partner
                    oL++;

                //// otherwise, right moves in
                } else {
                    // advance
                    oR--;
                }

                // compress left
                while(oL<oR && oL != 0 && nums[oL] == nums[oL-1]) {
                    oL++;
                }
                // compress right
                while(oL<oR && oR+1 < n && nums[oR] == nums[oR+1]) {
                    oR--;
                }
            }
        }
        return new LinkedList<>(collector);
    }

    private class Quintuple {
        public final int ol;
        public final int or;

        private Quintuple(int ol, int or) {
            this.ol = ol;
            this.or = or;
        }
    }
}
