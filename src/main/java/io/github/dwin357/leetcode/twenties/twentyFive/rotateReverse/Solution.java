package io.github.dwin357.leetcode.twenties.twentyFive.rotateReverse;


public class Solution {
    /*
     * Note: this is actually my solution from 24.  It should also meet the challenge of working in O(k) space
     *
     * Runtime: 1 ms, faster than 32.21% of Java online submissions for Reverse Nodes in k-Group.
     * Memory Usage: 38.9 MB, less than 90.69% of Java online submissions for Reverse Nodes in k-Group.
     * ...vs last time
     * Better than 50.68% on speed
     * Better than 37.61% on memory
     *
     * So I lost a bunch on time, but gained a bunch on memory (I think I still prefer time all other being eq)
     * But I do believe I hit the stretch goal of a solution that works in O(k) memory
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        // add an anchor-node to the front, at the end we rtn anchor.next
        ListNode anchor = new ListNode();
        anchor.next = head;
        
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

