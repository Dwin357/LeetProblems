/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.twenties.twentyFive.rotateReverse;

import io.github.dwin357.leetcode.utils.ListNode;

/**
 *
 * @author dwin
 */
/**
Example:
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:
 - Only constant extra memory is allowed.
 - You may not alter the values in the list's nodes, only nodes itself may be changed.

/////////////
/*
from :: a -> b -> c -> d -> e -> f -> g

b & f :: expected a-> b -> f -> e -> d -> c -> g

// cut at the 2 locations
preLCut = b -> *
postLCut(head) = c -> d -> e -> f -> *
preRCut(onDeck) = f -> *
postLCut = g -> ...

///// start loop
// break if head is null

// set onDeck as head.next
preLCut = b -> *
head = c -> d -> e -> f -> *
onDeck = d -> e -> f -> *
postLCut = g -> ...

// set head.next = postLCut
preLCut = b -> *
head = c -> g -> ...
onDeck = d -> e -> f -> *
postLCut = g -> ...        

// transfer head to post cut
preLCut = b -> *
head = c -> g -> ...
onDeck = d -> e -> f -> *
postLCut = c -> g -> ...   

// transfer onDeck to head
preLCut = b -> *
head = d -> e -> f -> *
onDeck = d -> e -> f -> *
postLCut = c -> g -> ...                   
//// end loop

///// start loop 2
// break if head is null
continue

// set onDeck as head.next
preLCut = b -> *
head = d -> e -> f -> *
onDeck = e -> f -> *
postLCut = c-> g -> ...

// set head.next = postLCut
preLCut = b -> *
head = d -> c-> g -> ...
onDeck = e -> f -> *
postLCut = c-> g -> ...       

// transfer head to post cut
preLCut = b -> *
head = d -> c-> g -> ...
onDeck = e -> f -> *
postLCut = d -> c-> g -> ...   

// transfer onDeck to head
preLCut = b -> *
head = e -> f -> *
onDeck = e -> f -> *
postLCut = d -> c-> g -> ...                   
//// end loop 2   

///// start loop 3
// break if head is null
continue

// set onDeck as head.next
preLCut = b -> *
head = e -> f -> *
onDeck = f -> *
postLCut = d -> c-> g -> ...

// set head.next = postLCut
preLCut = b -> *
head = e -> d -> c-> g -> ...
onDeck = f -> *
postLCut = d -> c-> g -> ...       

// transfer head to post cut
preLCut = b -> *
head = e -> d -> c-> g -> ...
onDeck = f -> *
postLCut = e -> d -> c-> g -> ...   

// transfer onDeck to head
preLCut = b -> *
head = f -> *
onDeck = f -> *
postLCut = e -> d -> c-> g -> ...                   
//// end loop 3        

///// start loop 4
// break if head is null
continue

// set onDeck as head.next
preLCut = b -> *
head = f -> *
onDeck = *
postLCut = e -> d -> c-> g -> ...

// set head.next = postLCut
preLCut = b -> *
head = f -> e -> d -> c-> g -> ...
onDeck = *
postLCut = e -> d -> c-> g -> ...       

// transfer head to post cut
preLCut = b -> *
head = f -> e -> d -> c-> g -> ...
onDeck = *
postLCut = f -> e -> d -> c-> g -> ...   

// transfer onDeck to head
preLCut = b -> *
head = *
onDeck = *
postLCut = f -> e -> d -> c-> g -> ...                   
//// end loop 4        

///// start loop 5
// break if head is null
break
preLCut = b -> *
head = *
onDeck = *
postLCut = f -> e -> d -> c-> g -> ...                   
//// end loop 5  

// set preLCut.next to postLCut
preLCut = b -> f -> e -> d -> c -> g -> ...
head = *
onDeck = *
postLCut = f -> e -> d -> c -> g -> ...


* Accepted...
* Better than 50.68% on speed
* Better than 37.61% on memory
* 
* Pretty lack-luster results, but I really didn't have any clever ideas
* on this one.
*/
public class OldSolution {
    public ListNode reverseKGroup(ListNode rightHandle, int k) {
        if(k==1){
            return rightHandle;
        }
        
        ListNode anchor = extend(rightHandle, -1);
        ListNode leftHandle = anchor;
        int counter = 0;
        
        while(rightHandle != null) {            
            rightHandle = rightHandle.next;     
            counter++;
            
            if(counter % (k-1) == 0) {
                try {            
                    leftHandle = rotateAndReverse(leftHandle, rightHandle);
                    rightHandle = leftHandle.next;                    
                } catch (NullPointerException e) {
                    return anchor.next;
                }

            }
        }
        
        return anchor.next;
    }
    
    private ListNode rotateAndReverse(ListNode preL, ListNode preR) {
        ListNode postL = preL.next;
        ListNode postR = preR.next;
        ListNode rtn = postL;
        preR.next = null;
        preL.next = null; // not strictly needed, but easier to visual snipping the segment
            
        while(postL != null) {
            preR = postL.next; // at this point, pre-right is used as a stash var for "on deck"
            postL.next = postR;
            postR = postL;
            postL = preR;
        }
        
        preL.next = postR;
        return rtn;
    }
    
    private ListNode extend(ListNode nxt, int val) {
        ListNode nwHead = new ListNode(val);
        nwHead.next = nxt;
        return nwHead;
    }
}
