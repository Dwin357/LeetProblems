package io.github.dwin357.leetcode.thirties.threeSeven;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void example_1() {
        char[][] given = new char[][] {
            new char[]{'5','3','.','.','7','.','.','.','.'},
            new char[]{'6','.','.','1','9','5','.','.','.'},
            new char[]{'.','9','8','.','.','.','.','6','.'},
            new char[]{'8','.','.','.','6','.','.','.','3'},
            new char[]{'4','.','.','8','.','3','.','.','1'},
            new char[]{'7','.','.','.','2','.','.','.','6'},
            new char[]{'.','6','.','.','.','.','2','8','.'},
            new char[]{'.','.','.','4','1','9','.','.','5'},
            new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        char[][] expected = new char[][] {
            new char[]{'5','3','4','6','7','8','9','1','2'},
            new char[]{'6','7','2','1','9','5','3','4','8'},
            new char[]{'1','9','8','3','4','2','5','6','7'},
            new char[]{'8','5','9','7','6','1','4','2','3'},
            new char[]{'4','2','6','8','5','3','7','9','1'},
            new char[]{'7','1','3','9','2','4','8','5','6'},
            new char[]{'9','6','1','5','3','7','2','8','4'},
            new char[]{'2','8','7','4','1','9','6','3','5'},
            new char[]{'3','4','5','2','8','6','1','7','9'}
        };

        Solution tested = new Solution();
        tested.solveSudoku(given);

        assertBoardsEqual(expected,given);
    }

    @Test
    public void validate_validate() {
        char[][] given = new char[][] {
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        int row = 8;
        int col = 7;
        boolean expected = true;

        boolean actual = Solution.validate(given,row,col);

        assertEquals(expected,actual);
    }

    @Test
    public void validate_invalid_box() {
        char[][] given = new char[][] {
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','7'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        int row = 8;
        int col = 7;
        boolean expected = false;

        boolean actual = Solution.validate(given,row,col);

        assertEquals(expected,actual);
    }

    @Test
    public void validate_invalid_col() {
        char[][] given = new char[][] {
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','7','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        int row = 8;
        int col = 7;
        boolean expected = false;

        boolean actual = Solution.validate(given,row,col);

        assertEquals(expected,actual);
    }

    @Test
    public void validate_invalid_row() {
        char[][] given = new char[][] {
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','7','8','.','.','7','9'}
        };

        int row = 8;
        int col = 7;
        boolean expected = false;

        boolean actual = Solution.validate(given,row,col);

        assertEquals(expected,actual);
    }

    public void assertBoardsEqual(char[][] expected, char[][] actual) {
        for(int row=0; row < expected.length; row++) {
            assertArrayEquals("row<"+row+">", expected[row], actual[row]);
        }
    }
}