/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dwin357.leetcode.ten.regEx;

/**
 *
 * @author dwin
 */
public class Solution {
        /*
    Idea - take the pattern-string & turn it into a pattern-obj
    The pattern obj will break the pattern into a linked list
    each node will hold
    - a char
    - a "any number" flag
    - a pointer to the next node
    - a pointer to the root node
    - when passed a char, the node will compare it to its element
    -- if element is not a star
        ck if arg matches element ? next : root
    -- if element is a star
        ck if arg matches element ? this : next.ck(el)
    
    UPDATE -- needed to add a method to ck if the pattern is "closed"
    so that it was not just matching on substrings

    UPDATE -- optional (ie *) node needs to check for each character from the
    current head to up to the first "non-matching" element
    This passes :: uses less memory than 99.02% other solutions
    */
    
    private static final int ASCI_STAR = 42;
    private static final int ASCI_DOT = 46;
    
    public boolean isMatch(String s, String p) {
    	PatternStep struct = buildStruct(p);
//    	display(struct);
    	ProcessState state = new ProcessState(s.toCharArray());
    	PatternStep result = struct.getNext(state);
    	return result != null;
	}
    
    private void display(PatternStep struct) {
    	StringBuilder sb = new StringBuilder();
    	while(struct != null) {
    		if(struct instanceof OptionalNode) {
    			sb.append("{ Optional el:");
    			sb.append(((OptionalNode)struct).el);
    			sb.append(" matchesAll?");
    			sb.append(((OptionalNode)struct).matchesAll);
    			sb.append("} ");
    		}
    		if(struct instanceof LiteralNode) {
    			sb.append("{ Literal el:");
    			sb.append(((LiteralNode)struct).el);
    			sb.append("} ");
    		}
    		if(struct instanceof FootNode) {
    			sb.append("{ FootNode }");
    		}
    		struct = struct.getNext();
    	}

    	System.out.println(sb.toString());
    }
    private PatternStep buildStruct(String ptrn) {
    	PatternStep previous = new FootNode();
        char[] pattern = ptrn.toCharArray();
    	for(int i=pattern.length -1; i>=0; i--) {
            if(ASCI_STAR == pattern[i]) {
                previous = new OptionalNode(pattern[i-1], previous);
                i = i-1;
                continue;
            }    		
            previous = new LiteralNode(pattern[i], previous);
    	}
    	return previous;
    }
    

    
    ////////////////////////////////
    ////    Data Structures    /////
    ////////////////////////////////
    
    private interface PatternStep {
    	public PatternStep getNext(ProcessState state);
    	public PatternStep getNext();
    }
    
    private class FootNode implements PatternStep {    	
    	@Override
    	public PatternStep getNext(ProcessState state) {
            boolean full = state.getPointer() == state.getLength();
            if(full) {
                return this;
            } else {
                return null;
            }
    	}
    	
    	@Override
    	public PatternStep getNext() {
    		return null;
    	}
    }
    
    private class OptionalNode implements PatternStep {
    	private PatternStep next;
    	private char el;
    	private boolean matchesAll;
    	
    	public OptionalNode(char element, PatternStep nxt) {
    		super();
    		this.next = nxt;
    		this.el = element;
    		this.matchesAll = (ASCI_DOT == element);
    	}
    	
    	@Override
    	public PatternStep getNext(ProcessState state) {
            ProcessState thread;
            PatternStep possible;
            char considered;
            try {
                // need to try it once w/ the optional node not taking a value
                thread = state.clone();
                possible = this.next.getNext(thread);
                if(possible != null) {
                    return possible;
                }
                
                // itterate through input, rtn if you have an ans, break if a char doesn't match
                for(int p = state.getPointer(); p < state.getLength(); p++) {
                   thread = state.clone();
                   thread.setPointer(p);
                   considered = thread.getNextElement();
                   if(matchesAll || el == considered) {
                       possible = this.next.getNext(thread);
                       if(possible != null) {
                           return possible;
                       }
                   } else {
                       break;
                   }
                }
            } catch (Exception e) {
                return null;
            }
            return null;
    	}
        
        
    	@Override
    	public PatternStep getNext() {
    		return this.next;
    	}
    }
    
    
    private class LiteralNode implements PatternStep {
    	private char el;
    	private boolean isWild;
    	private PatternStep next;
    	
    	public LiteralNode(char element, PatternStep nxt) {
    		super();
    		this.el = element;
    		this.next = nxt;
                this.isWild = (ASCI_DOT == element);
    	}
      	@Override
    	public PatternStep getNext(ProcessState state) {
            try {
                char considered = state.getNextElement();
                boolean isMatch = isWild || considered == el;
                if(isMatch) {
                    return next.getNext(state);
                } else {
                    return null;
                }                
            } catch (Exception e) {
                return null;
            }
    	}  	

    	@Override
    	public PatternStep getNext() {
    		return this.next;
    	}        
    }
    
    private class ProcessState {
        final private char[] considered;
    	private int pointer;
    	
    	private ProcessState(char[] input) {
    		super();
                this.considered = input;
    		this.pointer = 0;
    	}
        
        public char[] getConsidered() {
            return this.considered;
        }
        
        public char getNextElement() {
            char el = considered[pointer];
            this.pointer = pointer +1;
            return el;
        }

        public int getLength() {
            return this.considered.length;
        }

        public int getPointer() {
                return pointer;
        }

        public void setPointer(int ptr) {
            if(considered.length < ptr) {
                    throw new IllegalStateException("Required::Out of Bounds");				
            }
            this.pointer = ptr;
        }

        @Override
        public ProcessState clone() {
            ProcessState other = new ProcessState(getConsidered());
            other.pointer = this.pointer;
            return other;
        }
    }
    
}