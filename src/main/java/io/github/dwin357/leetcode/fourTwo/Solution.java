package io.github.dwin357.leetcode.fourTwo;

public class Solution {
    /*
     * Meditation: So this is the initial scenario I thought of (see meditation in oneOne.Solution)
     * where volume is accumulated between "anchors" which flow outwards from the global-max
     * However, the twist is A) elements have a material width & (as a corollary) B) "subsumed" elements
     * diminish the volume.
     *
     * - Loop the list to find global max (maybe w/ optimization)
     * - initialize a modified linked list starting at element.0
     *  -- holds height
     *  -- holds "anchor" (ie the next link in the link list)
     *  -- holds volume
     *  - node has  an "append" method which either
     *  -- decrements volume & returns self (in the case of submerged blocks)
     *   or
     *  -- creates anchor, links to anchor, calculates final volume, & returns anchor (in case of an anchor)
     *  - Then feed every num from element.0 through the split into this append
     *  - Finally, in order to sum just tick through all the anchors and aggregate their totals
     *
     *  - Then initialize another starting at element.n appending through split for the other side
     *
     *  - The total can then be found by adding these two together
     *
     * Also - another observation - it is now valid for the ary to be a single element...
     * not sure how relevant that difference is
     *
     * Runtime: 2 ms, faster than 33.20% of Java online submissions for Trapping Rain Water.
     * Memory Usage: 39.7 MB, less than 30.29% of Java online submissions for Trapping Rain Water.
     */
    public int trap(int[] height) {
        // split on max value
        int split = findSplit(height);

        // sum left hand side
        Node end = new Node(height[0]);
        Node n = end;
        for(int i=1; i < split; i++) {
            n = n.append(height[i]);
        }
        int leftHandSum = end.sum();

        // sum right hand side
        end = new Node(height[height.length-1]);
        n = end;
        for(int i=height.length-2; i > split; i--) {
            n = n.append(height[i]);
        }

        return leftHandSum + end.sum();
    }

    public class Node {
        public int base;
        public int height;
        public int displacement;
        public Node anchor;

        public Node(int height) {
            this.height = height;
            this.base = 0;
            this.displacement = 0;
        }

        public Node append(int height) {
            // existing is taller
            if(this.height > height) {
                // subsume appended
                this.base++;
                this.displacement+=height;
                return this;
            } else {
                // anchor to appended
                this.anchor = new Node(height);
                return this.anchor;
            }
        }

        public int sum() {
            int attached = anchor == null ? 0 : anchor.sum();
            return attached + (height * base) - displacement;
        }
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
