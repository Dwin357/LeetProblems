package io.github.dwin357.leetcode.tens.oneOne;

public class Solution {
    /*
     * Meditation: first thought is to find [global-max; global-max-runner-up] & use them b/c this will maximize height
     * But that only maximizes in the 1 direction while ignoring width...
     *
     * So the next idea is,
     *  - seed w/ first 2 values to create an area
     *  - at each step, calculate new area & keep the better of new || current
     *  ...but this has the issue of forgetting about "potentiality" of previously considered items
     *  by which I mean [1,2,1] has an area of 2 (between the 2 ones) and the elevation of the 2 "above the water line"
     *  is wasted potential.  But [1,2,1,2] has an area of 4 (between the 2 twos) & is using that previously wasted potential.
     *  so then, when processing linearly & assuming both of these are snapshots of the same array, how do we "remember" the
     *  first 2 when we are processing the second 1 ... especially when there is no fixed num of how many of these there will be?
     *
     *  So my next idea is a graph (b/c of course it is).
     *  - the global max points to nothing (will need to work out what to do if this isn't a unique value)
     *  - every other node tries to point to the global max, but can be "blocked" by a taller intervening node
     *  - if a node is blocked by an intervening node, that intervening node becomes the given nodes "anchor"
     *  - if a node is not blocked, the global max becomes the given nodes "anchor"
     *  - the height of a given node x distance to its anchor = theoretical contribution of a node
     *  - if a node traverses other nodes on it way to its anchor, it "subsumes" them & their actual contrib is 0
     *  ...so this ends up looking like a tree w/ the global max as the root & every level representing an anchor
     *  w/ potentially multiple children branching off of it.
     *
     *  However... this data structure (as I have described it) actually describes how much water the shape overall holds
     *  if you just threw a bucket of water on top and filled the whole thing.
     *
     *  So, what if we keep the same tree structure: global max as the root & nodes branching out according to their anchors
     *  (interesting sub thought... if there are multiple maximums as long as nothing taller than global max & global)
     *   then the value of any given node is just its own height times its distance from the root (and actually anchors go away)
     *  but actually that is not the full story b/c you need to compare everything on the right-hand side of the max w/ everything on the left hand side
     *
     *  So, actually actually,
     *  - loop the list to find the global max (if multiple any work, but the more slanted the resulting split the better)
     *  - at global max, split list into right side and left side (make sure global max is included in both grp to account for the case where optimal is one handed)
     *  - run a nested for loop to visit every combination of two lists
     *  - at each combination calculate lesser-of(A or B) X (a-to-max + b-to-max)
     *  - keep a running best of these calculations which is returned as the ans
     *  -- optimize the global-max search to prefer items close to ends of list
     *  -- <best-case> O(n) to loop list and find max + O(n-1) to calculate best one hand solution (when max is at end of ary)
     *  -- <worst-case> O(n) to loop list and find max + O( (0.5n+1)^2) to calculate best combination (when max is dead center)
     *
     *  --- ya, that actually seems like it is the correct answer
     */

    /*
     * with the optimization of pushing splits to the end
     *  Runtime: 1619 ms, faster than 5.01% of Java online submissions for Container With Most Water.
     *  Memory Usage: 52.7 MB, less than 73.55% of Java online submissions for Container With Most Water.
     *
     * without the optimization of pushing splits to the end
     *  Runtime: 2148 ms, faster than 5.01% of Java online submissions for Container With Most Water.
     *  Memory Usage: 52.3 MB, less than 95.29% of Java online submissions for Container With Most Water
     */

    public int maxArea(int[] heights) {
        // split at global max
        int split = -1;
        int highScore = -1;
        for(int k=0; k<heights.length; k++) {
            if(heights[k] > highScore) {
                split=k;
                highScore=heights[k];
            }
        }

        // split at global max
//        int split = findSplit(heights);

        int currentWinner = -1;
        int contestant;
        int base;
        int height;

        // loop left hand side (including split position)
        for(int i=0; i<=split; i++) {
            // loop right hand side (including split) to creat combination
            for(int j=split; j<heights.length; j++) {
                // contestant height is the shorter of the pair
                height = Math.min(heights[i], heights[j]);
                // contestant base is the sum of their respective distances from split
                // simplified from (split - i) + (j - split)
                base = j - i;
                // contestant area is product of base & height
                contestant = height * base;
                // update current winner if contestant beats old winner
                if(contestant > currentWinner) {
                    currentWinner = contestant;
                }
            }
        }
        return currentWinner;
    }

    private int findSplit(int[] heights) {
        int currentWinner = -1;
        int highScore = -1;
        int winnerTieBreak = -1;
        int contestantTieBreak;
        int halfList = heights.length / 2;
        for(int i=0; i<heights.length; i++) {
            // if current winner beats contestant, move on
            if(highScore > heights[i]) {
                continue;
            }
            // if contestant beats current winner, they are new winner
            if(highScore < heights[i]) {
                highScore = heights[i];
                currentWinner = i;
                winnerTieBreak = Math.abs(i - halfList);
                continue;
            }
            // if neither of the above, it means heights[i]==highScore, so do tie-break check
            // tiebreak value will be n/2 at start and end of list, going to 0 in middle; prefer a higher value
            contestantTieBreak = Math.abs(i - halfList);
            if(contestantTieBreak > winnerTieBreak) {
                currentWinner = i;
                winnerTieBreak = contestantTieBreak;
            }
        }
        return currentWinner;
    }
}
