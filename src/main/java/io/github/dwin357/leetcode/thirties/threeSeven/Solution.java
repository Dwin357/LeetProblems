package io.github.dwin357.leetcode.thirties.threeSeven;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /*
     * Meditation: so in a lot of ways, this is problem is my white-whale.  When I was in my bootcamp,
     * this was the problem they gave us as are "semester one final" (which no one even came close to solving),
     * and again a couple years ago when I thought I was all big & bad and I was going to go through and only
     * do the hard problems on leetcode ... this was one I couldn't make it through.
     *
     * Third time is the charm I suppose.
     *
     * Surveying the problem now, what jumps out at me...
     * 1) I'm pretty sure you could (semi) brute force it.
     * As a first pass, I am imagining a tree graph where each node is characterized by
     * - a board state
     * - a validity boolean
     * - a "next unresolved" pointer
     * - maybe(?) a resolved boolean (could also make the next pointer null / -1)
     * =======
     * when a node is initialized
     * - it runs a validity check on the received board state (which it uses to set boolean)
     * - looks for next unresolved position to set unresolved-pointer (& possibly boolean)
     * Then, if it is valid, it spawns 9 more nodes replacing the "next pointer" w/ each possible digit
     * ...this tree branches super fast (x9 at each node) but in principal every branch but the correct one
     * would terminate for invalidity, and you solve the problem in what is essentially a recursive way.
     *
     * Now, a weakness of this approach is that is replicating a lot of extra state.  If 5 characters are
     * filled in on the given, those 5 char will be recreated at every node as the board is recreated & elaborated on.
     * In light of this, we could be more space efficient by instead having a single board and a "change-log"
     * which would be a stack of [row,col,val].  The control flow would be
     * - check if board is complete
     * -- if complete, stop : else...
     * check if board state is valid
     * -- if valid, go to first open position and guess 1 + register this guess w/ the change-log by pushing [row,col,val] onto the stack
     * -- if not valid...
     * check value of most recent guess (ie top of change log)
     * -- if value < 9, value++ (in both change-log & board) & reset to top of loop
     * -- if value == 9, reset value to '.' on board, pop item off change log, and try to increment (new) top of log
     * NOTE: if previous is also already 9, keep rolling back until you reach something you can increment
     *
     * This ends up feeling very "depth-first-search" to me...  but I think it should be pretty straight fwd to code
     *
     * Another part of me wonders if could short-circuit some of this brute-force work w/ heuristics the way human's solve
     * sudoku ... but maybe leave that for a second iteration
     *
     * ...So I'm working through & an interesting observation came to me
     * If I have 2 stacks 1 with the blanks & 1 with the guesses ... then I can move items back and forth
     * between the stacks, and I'm done whenever the blank stack is empty
     * Neat!
     *
     * Runtime: 31 ms, faster than 10.25% of Java online submissions for Sudoku Solver.
     * Memory Usage: 38.5 MB, less than 28.64% of Java online submissions for Sudoku Solver.
     */

    private static final int BOX_EDGE = 3;
    private static final int EDGE = 9;
    private static final char BLANK = '.';
    private static final char ROLLBACK_VALUE = '9';
    private static final Map<Character,Character> INCREMENT_TABLE;
    static {
        INCREMENT_TABLE = new HashMap<>();
        INCREMENT_TABLE.put('.', '1');
        INCREMENT_TABLE.put('1', '2');
        INCREMENT_TABLE.put('2', '3');
        INCREMENT_TABLE.put('3', '4');
        INCREMENT_TABLE.put('4', '5');
        INCREMENT_TABLE.put('5', '6');
        INCREMENT_TABLE.put('6', '7');
        INCREMENT_TABLE.put('7', '8');
        INCREMENT_TABLE.put('8', '9');
    }

    public void solveSudoku(char[][] board) {
        Deque<Move> blanks = new ArrayDeque();
        Deque<Move> guesses = new ArrayDeque();

        // regiester all the blanks
        for(int row=0; row < EDGE; row++) {
            for(int col=0; col < EDGE; col++){
                if(board[row][col] == BLANK) {
                    blanks.push(new Move(row,col));
                }
            }
        }

        boolean valid = true;
        Move active;
        // process breaks (ie puzzle solved) when board is in a valid state & all blanks are filled in
        while(!valid || !blanks.isEmpty()) {

            // get the next move based on if board is in a valid state
            active = valid ? blanks.pop() : guesses.pop();

            // if moves need to be rolled-back, do that first1
            while(active.val == ROLLBACK_VALUE) {
                // rollback the active guess
                active.val = BLANK;
                board[active.row][active.col] = BLANK;
                blanks.push(active);
                // set active guess to the next
                active = guesses.pop();
            }

            // increment the active guess, update board, & log it w/ the guesses
            active.val = INCREMENT_TABLE.get(active.val);
            board[active.row][active.col] = active.val;
            guesses.push(active);

            // validate board state in anticipation of next cycle
            valid = validate(board, active.row,active.col);
        }
    }

    


    public static boolean validate(char[][] board, int row, int col) {
        // check for no matching digits in the column
        for(int r=0; r < EDGE; r++) {
            if(r != row && board[row][col] == board[r][col]) {
                return false;
            }
        }
        // check for no matching digits in the row
        for(int c=0; c < EDGE; c++) {
            if(c != col && board[row][col] == board[row][c]) {
                return false;
            }
        }
        // check for no matching digits in the box
        int rowOffset = (row / BOX_EDGE) * BOX_EDGE;
        int colOffset = (col / BOX_EDGE) * BOX_EDGE;
        for(int br=0; br<BOX_EDGE; br++) {
            for(int bc=0; bc<BOX_EDGE; bc++) {
                if(board[row][col] == board[br+rowOffset][bc+colOffset]) { // ck for match value, on own line
                    if(! (br+rowOffset == row && bc+colOffset == col)) { // ck to skip reference space, on own line for readability
                        return false;
                    }
                }
            }
        }
        // if it isn't disqualified, it is valid by default
        return true;
    }

    private class Move {
        public final int row;
        public final int col;
        public char val;

        private Move(int row, int col) {
            this.row = row;
            this.col = col;
            this.val = BLANK;
        }
    }
}
