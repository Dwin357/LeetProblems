package io.github.dwin357.leetcode.tens.oneEight;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SolutionTwo {

    /*
     * To recap
     * - initial solution was to run my march-to-the-middle 2x, once for the first and last loops & once for the 3rd & 4th loops
     * --But! this ran into some issues around the question of, when the outer loops balance on the pivot, there are 2 valid follow-ups
     * (ie push in the right only || push in the left only), and both of these need to separately checked & cleared before "normal"
     * processing can be resumed w/ both of them being advanced in 1 step.
     *
     * - So I bubblegum & duck-taped my way through a solution (adding probably way too much complexity) which reserves pointers in a cache
     * and then has a bunch of flags which determine if we are addressing a "criss-cross" scenario...
     * And it worked, scenario 207 passes ...But! a use case I was previously passing (scenario 158) is no failing.
     * And what is worse, all that complexity I slathered onto the solution to tease apart in my mind where the trouble is coming from.
     *
     * - However, writing this has given me an idea...  the march to the middle pivot trick is clearly powerful, it is doing a lot of work
     * for me here.  At the same time it this weakness (at least when applied recursively, but I suspect maybe always) of being ambigious
     * when it reaches a balanced pivot.  Now the original idea of criss-cross checking (l+1,r)&(l,r-1) before ending on (l+1,r-1) was
     * based on the observation that the inner element on 3sum when matching increments both pointers inward.  And, while logically it
     * seems like that reasoning should also apply to the outer pointers, as evidenced by the complexity I added to acheive that result
     * I had to perform major-major surgery to cover this edge case --and so maybe surgery cut something I didn't want to cut?  And, if
     * hospital infection rates have taught me anything it is that smaller surgeries are usually better, so what if we just tested (l+1,r)
     * and then reset to (l,r-1) for normal testing?
     *
     * Given: -3,-2,-1,0,0,1,2,3 & tgt:0
     * i:-3 j:-2 k:2 l:3 -> matches, capture
     * i:-3 j:-1 k:1 l:3 -> matches, capture
     * i:-3 j:0 k:0 l:3 -> matches, capture
     * outer pivot balanced : resolve l+1,r
     * i:-2 j:-1 k:2 l:3 -> inner-pivot 2, r-1
     * i:-2 j:-1 k:1 l:3 -> inner-pivot 1, r-1
     * i:-2 j:-1 k:0 l:3 -> matches, capture
     * rather than outer loop adv by pivot, flips to (l,r-1)
     * i:-3 j:-2 k:1 l:2 -> inner-pivot -2, l+1
     * i:-3 j:-1 k:1 l:2 -> inner-pivot -1, l+1
     * i:-3 j:0 k:1 l:2 -> matches, capture
     * adv outer by normal pivot: -1, l+1
     * i:-2 j:-1 k:1 l:2 -> matches, capture
     * i:-2 j:0 k:0 l:2 -> matches, capture
     * outer pivot balanced : resolve l+1,r
     * i:-1 j:0 k:1 l:2 -> inner-pivot 2, r-1
     * i:-1 j:0 k:0 l:2 -> inner-pivot 1, no solutions
     * rather than outer loop adv by pivot, flips to (l,r-1)
     * i:-2 j:-1 k:0 l:1 -> inner-pivot -2, l+1
     * i:-2 j:0 k:0 l:1 -> inner-pivot -1, no solutions
     * adv outer by normal pivot: -1, l+1
     * i:-1 j:0 k:0 l:1 -> matches, capture
     * no further compression possible, end
     * ...so the simplified algo works on the one edge case
     *
     * Given: -3,-1,0,2,4,5 & tgt:0
     * i:-3 j:-1 k:4 l:5 -> inner-pivot 5, r-1
     * i:-3 j:-1 k:2 l:5 -> inner-pivot 3, r-1
     * i:-3 j:-1 k:0 l:5 -> inner-pivot 1, no solutions
     * outer pivot 2, r-1
     * i:-3 j:-1 k:2 l:4 -> inner-pivot 2, r-1
     * i:-3 j:-1 k:0 l:4 -> matches, capture
     * outer pivot 1, r-1
     * i:-3 j:-1 k:0 l:2 -> inner-pivot -1, no solutions
     * no further compression possible, end
     *
     * Interestingly, the outer pivot never balanced in this so my original solution
     * "should" have resolved correctly...  which re-enforces the point that my
     * logic is on point and it was the complexity in my impl which created some kind
     * of unintended cross-interference...
     *
     * trying again with the simplified one -- lets see if we can get it to dance like I want.
     *
     * So it turns out, when rebuilding this from the ground up, I discovered the sign was backwards for my
     * outers when trying to decide which side I want to advance based upon my pivot & by correct that error
     * my other class now passes all known test cases
     *
     * However, the presence of that error speaks to the too much complexity-statement the code is trying to
     * express -- and for that reason alone I think this simplified impl is worthwhile
     *
     * Hmm... this is wrong,
     * for {-5,-4,-3,-2,-1,0,0,1,2,3,4,5} tgt 0 I missed [-5, 0, 2, 3], [-3, -2, 0, 5]
     *
     * On some level the problem is clear (I think)...
     * - b/c I am only considering 1 step down a "road not taken" & then restoring from cache
     * solutions which lie along that road not taken, but are more that 1 step away get missed
     * - And based on that analysis the obvious thing to do is just walk a given path to its conclusion,
     * then recover the path not taken from and do the same
     * - But as a practical matter it is possible for the outers to balance at multiple points along a path
     * so rather than a cache you would need some sort of stack that you are pushing the decision alt
     * to & only when you have exhausted the stack are you resolved.
     *
     * ...and with all the fwd & back, fwd & back, pushing & popping the stack 2 things jump out at me
     * 1) is this really more efficient than a pair of for-loops (...I mean probably, but it might be good to ck)
     * 2) this stack business 100% feels like I am doing a depth first search on a tree...
     * -- and I don't know what to do with that information, or really even how it is possible, but there it is?!?
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort the list as a pre-req
        Arrays.sort(nums);

        // setup some reusable vars
        List<List<Integer>> collector = new LinkedList<>();
        int n = nums.length-1;
        int oL = 0; // outer left
        int oR = n; // outer right
        int oSum; // outer sum
        int oPiv; // outer piviot
        int iL; // inner left
        boolean ilm; // inner left move flag
        int iR; // inner right
        boolean irm; // inner right move flag
        int iTgt; // inner target
        int iPiv; // inner pivot

        int coL=-1; // cached outer left
        int coR=-1; // cached outer right
        boolean ca = false; // cache active flag


        while(oL+2<oR) {
            // semi-reusable vars
            oSum = nums[oL] + nums[oR]; // a + d
            iL = oL + 1;
            iR = oR - 1;
            iTgt = target - oSum; // T - a -d

            // do inner stuff
            while (iL < iR) {
                iPiv = nums[iL] + nums[iR] - iTgt; // b + c - T + a + d
                ilm = false;
                irm = false;
//                System.out.printf("oL<%d>:%d iL<%d>:%d iR<%d>:%d oR<%d>:%d \n", oL, nums[oL], iL, nums[iL], iR, nums[iR], oR, nums[oR]);

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
            // if cache is active, that takes priority over pivot
            if (ca) {
                oL = coL;
                coL = -1; // safety precaution to make sure these aren't used
                oR = coR;
                coR = -1; // safety precaution to make sure these aren't used
                ca = false;

            // if pivot balances, for now; treat as left
            } else if(oPiv == 0) {

                // cache advance left values
                coL = oL;
                coR = oR -1;
                ca = true;

                // check advance right path
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



        return collector;
    }
}
