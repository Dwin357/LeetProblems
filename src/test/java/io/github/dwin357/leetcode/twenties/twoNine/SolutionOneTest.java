package io.github.dwin357.leetcode.twenties.twoNine;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionOneTest {

    @Test
    public void example_1() {
        int top = 10;
        int btm = 3;
        int expected = 3;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int top = 7;
        int btm = -3;
        int expected = -2;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int top = 0;
        int btm = 1;
        int expected = 0;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        int top = 1;
        int btm = 1;
        int expected = 1;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void test_10() {
        int top = -2147483648;
        int btm = -1;
        int expected = 2147483647;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_a() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = -10;
        int btm = 1;
        int expected = -10;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_b() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = 10;
        int btm = -1;
        int expected = -10;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_c() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = 10;
        int btm = 1;
        int expected = 10;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_d() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = -10;
        int btm = -1;
        int expected = 10;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void test_16() {
        // time limit exceeded
        int top = 2147483647;
        int btm = 3;
        int expected = 715827882;
        SolutionOne tested = new SolutionOne();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }
}