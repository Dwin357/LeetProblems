package io.github.dwin357.leetcode.twenties.twoFour;

public class Solution {
    /*
     * 1 -> 2 -> 3 -> 4
     * ...
     * 2 -> 1 -> 4 -> 3
     *
     * For problem 24
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
     * Memory Usage: 38.8 MB, less than 5.70% of Java online submissions for Swap Nodes in Pairs.
     */
    public ListNode swapPairs(ListNode head) {
        // add an anchor-node to the front, at the end we rtn anchor.next
        ListNode anchor = new ListNode();
        anchor.next = head;

        // in the fancier version of this, k is passed in; for now it is just 2
        int k = 2;
        // make a reusable frame that we will use to do the shuffle
        ListNode[] frame = new ListNode[k+2];
        ListNode fill = anchor;
        int i;

        // Look Ma - I'm using a named outer loop!!!
        outerLoop: while(true) {
            // fill the frame starting from the fill node
            // NOTE: it is ok if the last node is null, but nothing before that
            i=0;
            while(i < frame.length) {
                if(fill == null && i < frame.length -1) {
                    break outerLoop;
                }
                frame[i] = fill;
                fill = fill != null ? fill.next : null;
                i++;
            }

            // shuffle the nodes w/in the frame
            // NOTE: the dark-magic for the index swap came from looking at the 2 & 3 node "folds"
            //  writing down all the needed xfrm, ordering them by the R side of the eq and see pattern
            //  From-index = (to-index + 1) % (frame.length -1) ...not sure the deeper meaning of this?
            i = 1;
            while(i < frame.length){
                frame[(i+1) % (frame.length - 1)].next = frame[i];
                i++;
            }

            // advance the fill-node k positions from start of the frame
            // NOTE: if this advances us past the end of the list, we are done
            i = 0;
            fill = frame[0];
            while(i < k){
                if(fill == null) {
                    break outerLoop;
                }
                fill = fill.next;
                i++;
            }
        }

        return anchor.next;
    }
}
