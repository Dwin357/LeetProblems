/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.problemTwentyThree;

/**
 *
 * @author dwin
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /*
    Problem: Merge k sorted linked lists and return it as one sorted list. 
    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6
    /////////////////////////////////////////////////////
    So naieve solution that I see here is set A as the return, 
    merge A & B, merge AB & C, merge ABC & D ...    
    However, I think this is a bit of a trap.  The prompt says that each
    linked list is sorted, but makes no promises that they are balanced.

    The trouble with this though, is that I don't think it really takes good
    advantage of the fact that the linked lists come pre-sorted.  
    
    Worst case scenario, total number of nodes N equals total number of rows K
    (ie you are essentially given an unsorted list, each "linked list" is just 1 node)
    
    Under this scenario, I believe you will need to do (n-1)^2 compares to build
    your output (b/c you will need to compare every node to every other node at
    some point in order to determine its position.
    
    Walking this logic out, if we increased every list to size 2, that would save
    us 1 compare per row (b/c each of those linked list pairs is sorted) for a total
    of k compares.  If we increased it to size 3, that saves us 3 compares per row (ab, bc, ac)
    So we see that the general formula seems to be
    (n-1)^2 - k(row-size factorial)
    And that seems pretty good, I mean the square is more powerful than the factorial
    but at least you are getting some discount
    
    Also important note, I don't think you gain anything here by trying to do the
    merge in a balanced way (ie A & B, C & D, AB & CD, etc), because the driving
    factor seems to be the number of node to node compares.  
    
    The alternative I see is put the heads of the linked lists into a priority queue.
    which can insert and remove in log(k) time for a total of (k+n)log(k)
    
    This makes better use of the fact that the lists are already sorted
    because I am excluding eveything but the list head from my ordering.

    --Solution worked, ran faster than 82.22% of other solutions
    */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }
        
        PriorityQueue dataStruct = new PriorityQueue(lists);
        if(dataStruct.isEmpty()) {
            return null;
        }
        ListNode root = dataStruct.getNext();
        ListNode last = root;
        while(!dataStruct.isEmpty()) {
            last.next = dataStruct.getNext();
            last = last.next;
        }
        return root;
    }
    
    
    private class PriorityQueue {
        private ListNode[] keys;
        public int len;
        
        public PriorityQueue(ListNode[] input) {
            keys = new ListNode[input.length + 1];
            len = 0;
            for(int i=0; i < input.length; i++) {
                if(input[i] != null) {
                    insert(input[i]);                    
                }
            }
        }
        
        public ListNode getNext() {
            ListNode el = keys[1];
            ListNode nxt = el.next;
            if(nxt != null) {
                keys[1] = nxt;
            } else {
                exchange(1, len);
                keys[len] = null;
                len--;
            }
            sink(1);
            return el;
        }

        public boolean isEmpty() {
            return len == 0;
        }
        
        private void insert(ListNode node) {
            len++;
            keys[len] = node;
            swim(len);
        }
        
        private void exchange(int i, int j) {
            ListNode stash = keys[i];
            keys[i] = keys[j];
            keys[j] = stash;
        }
        
        private boolean less(int i, int j) {
            return keys[i].val > keys[j].val;
        }
        
        private void sink(int i) {
            while (2*i <= len) {
                int j = 2*i;
                if(j < len && less(j, j+1)) {
                    j++;
                }
                if(!less(i, j)) {
                    break;
                }
                exchange(i, j);
                i = j;
            }
        }
        
        private void swim(int i) {
            while(i > 1 && less(i/2, i)) {
                exchange(i/2, i);
                i = i/2;
            }
        }
    }
}
