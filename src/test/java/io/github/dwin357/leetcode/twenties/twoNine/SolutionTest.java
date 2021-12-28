package io.github.dwin357.leetcode.twenties.twoNine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void visualization_workshop() {
        int z = 0; // zero
        int a = 1; // one
        int b = a << 1; // ten
        int c = a << 1 << 1; // a hundred
        int d = 1 << 10; // 10_000_000_000 ... that escalated quickly
        // so...  D is a 1 w/ 10 zeros, which I guess makes sense.  But I was expected c == d... so these are evaluated R -> L?
        int e = 1 << (1 << 1); // 100
        int f = (1 << 1) << 1; // 100
        // nope - doesn't seem to matter ... oh, but I forgot, I'm looking at the binary rep
        int g = Integer.MAX_VALUE; // 31 wide
        int h = g << 1;
        int i = g >> 1; // 30 wide
        int j = Integer.MIN_VALUE; // 32 wide
        int k = -2 << 1; // 32 wide
        int l = 3 << 1; // 6
        int m = 3 << 1 << 0; // 6  = 3 * 2^1 ... why isnt this 2^0 ???
        int n = 3 << 1 << 1; // 12 = 3 * 2^2
        int o = 3 << 1 << 2; // 24 = 3 * 2^3
        int p = 3 << 1 << 3; // 48 = 3 * 2^4
        int q = 3 << 2; // 12
        int r = 3 << 3; // 24
        int s = 3 << 4; // 48
        int t = 3 << 0; // 3, so this is like doing *1 (or more technically, 2^0)
        int u = 3 << 2 << 1; // 24 ... which along with cases:[o,r] suggests the shifting is transitive?
        int v = 3 << 2 << 2; // 48 ... which seems to confirm the transitive is not a special case around the 1
        int w = 3 << 2 >> 2; // 3[11]
        int x = 3 >> 2 << 2; // 0[0]
        int y = 3 >> 1 << 1; // 2[10] ... so it is mostly transitive, w/ the * that if you fall off the edge you come back as 0 regardless
                                    // also, turns out it is evaluated L -> R after all --so there is that
        int zz = Integer.MIN_VALUE << 1 >> 1; // 0[0] ... same if you fall off the L side of the map

        int aa = -2; // 32 wide: ends in 0 the rest 1's
        int bb = -3; // 32 wide: ends in 01 the rest 1's
        int cc = -1; // 32 wide: all 1's

        int dd = -1 >> 2; // 32 wide, all 1's ... ie the first 2 are still 1s (b/c the leading pos was 1 at start of shift)
        int ee = -1 >>> 2; // 30 wide, all 1's ... ie the first 2 are zeros
        int ff = -1 << 2; // 32 wide, last 2 pos are 0s :: (== -4)
//        int gg = -1 <<< 2; there is no such thing as "<<<" apparently


        int hh = ((1<<25)+(1<<18)+(1<<11)+(1<<4)); // 00000010_00000100_00001000_00010000 :: original
        int ii = Integer.reverse(hh);              // 00001000_00010000_00100000_01000000 :: mirror front to back
        int jj = Integer.reverseBytes(hh);         // 00010000_00001000_00000100_00000010 :: mirror in blocks of 8
        int kk = ~hh;                              // 11111101_11111011_11110111_11101111 :: "negation" flip all the bits to opposite
        int ll = jj & kk;                          // 00010000_00001000_00000100_00000010 :: "and" 1 only if both are 1 @ pos
        int mm = jj | kk;                          // 11111101_11111011_11110111_11101111 :: "or" 1 if either is 1 @ pos
        int nn = jj ^ kk;                          // 11101101_11110011_11110011_11101101 :: "xor" 1 only if both are dif @ pos
        int maskSize = 16;
        int oo = (1<< maskSize) -1;                // 00000000_00000000_11111111_11111111 :: a trick to generate a "mask" of leading zeros
        int pp = hh & oo;                          // 00000000_00000000_00001000_00010000 :: using the mask to truncate a result
        int qq = (~ff) +1; // a trick to change the sign of a number.  This works on everything except Integer.Min (for which it returns Integer.Min)
        int rr = -ff; // has the same behavior as above, including around Integer.Min :: I think it is a more expressive alias

        /*
         * So, to summarize:
         * - (X << t) == (X * 2^t)
         * - the division problem works by growing the bottom by as many powers of 2 as possible, removing that many "counts" & repeating (until no more bites can be taken)
         */

        System.out.printf("case:z i:%d asB:%s\n",z, Integer.toBinaryString(z));
        System.out.printf("case:a i:%d asB:%s\n",a, Integer.toBinaryString(a));
        System.out.printf("case:b i:%d asB:%s\n",b, Integer.toBinaryString(b));
        System.out.printf("case:c i:%d asB:%s\n",c, Integer.toBinaryString(c));
        System.out.printf("case:d i:%d asB:%s\n",d, Integer.toBinaryString(d));
        System.out.printf("case:e i:%d asB:%s\n",e, Integer.toBinaryString(e));
        System.out.printf("case:f i:%d asB:%s\n",f, Integer.toBinaryString(f));
        System.out.printf("case:g i:%d asB:%s\n",g, Integer.toBinaryString(g));
        System.out.printf("case:h i:%d asB:%s\n",h, Integer.toBinaryString(h));
        System.out.printf("case:i i:%d asB:%s\n",i, Integer.toBinaryString(i));
        System.out.printf("case:j i:%d asB:%s\n",j, Integer.toBinaryString(j));
        System.out.printf("case:k i:%d asB:%s\n",k, Integer.toBinaryString(k));
        System.out.printf("case:l i:%d asB:%s\n",l, Integer.toBinaryString(l));
        System.out.printf("case:m i:%d asB:%s\n",m, Integer.toBinaryString(m));
        System.out.printf("case:n i:%d asB:%s\n",n, Integer.toBinaryString(n));
        System.out.printf("case:o i:%d asB:%s\n",o, Integer.toBinaryString(o));
        System.out.printf("case:p i:%d asB:%s\n",p, Integer.toBinaryString(p));
        System.out.printf("case:q i:%d asB:%s\n",q, Integer.toBinaryString(q));
        System.out.printf("case:r i:%d asB:%s\n",r, Integer.toBinaryString(r));
        System.out.printf("case:s i:%d asB:%s\n",s, Integer.toBinaryString(s));
        System.out.printf("case:t i:%d asB:%s\n",t, Integer.toBinaryString(t));
        System.out.printf("case:u i:%d asB:%s\n",u, Integer.toBinaryString(u));
        System.out.printf("case:v i:%d asB:%s\n",v, Integer.toBinaryString(v));
        System.out.printf("case:w i:%d asB:%s\n",w, Integer.toBinaryString(w));
        System.out.printf("case:x i:%d asB:%s\n",x, Integer.toBinaryString(x));
        System.out.printf("case:y i:%d asB:%s\n",y, Integer.toBinaryString(y));
        System.out.printf("case:zz i:%d asB:%s\n",zz, Integer.toBinaryString(zz));
        System.out.printf("case:aa i:%d asB:%s\n",aa, Integer.toBinaryString(aa));
        System.out.printf("case:bb i:%d asB:%s\n",bb, Integer.toBinaryString(bb));
        System.out.printf("case:cc i:%d asB:%s\n",cc, Integer.toBinaryString(cc));
        System.out.printf("case:dd i:%d asB:%s\n",dd, Integer.toBinaryString(dd));
        System.out.printf("case:ee i:%d asB:%s\n",ee, Integer.toBinaryString(ee));
        System.out.printf("case:ff i:%d asB:%s\n",ff, Integer.toBinaryString(ff));
        System.out.printf("case:hh i:%d asB:%s\n",hh, Integer.toBinaryString(hh));
        System.out.printf("case:ii i:%d asB:%s\n",ii, Integer.toBinaryString(ii));
        System.out.printf("case:jj i:%d asB:%s\n",jj, Integer.toBinaryString(jj));
        System.out.printf("case:kk i:%d asB:%s\n",kk, Integer.toBinaryString(kk));
        System.out.printf("case:ll i:%d asB:%s\n",ll, Integer.toBinaryString(ll));
        System.out.printf("case:mm i:%d asB:%s\n",mm, Integer.toBinaryString(mm));
        System.out.printf("case:nn i:%d asB:%s\n",nn, Integer.toBinaryString(nn));
        System.out.printf("case:oo i:%d asB:%s\n",oo, Integer.toBinaryString(oo));
        System.out.printf("case:pp i:%d asB:%s\n",pp, Integer.toBinaryString(pp));
        System.out.printf("case:qq i:%d asB:%s\n",qq, Integer.toBinaryString(qq));
        System.out.printf("case:rr i:%d asB:%s\n",rr, Integer.toBinaryString(rr));
    }

    @Test
    public void example_1() {
        int top = 10;
        int btm = 3;
        int expected = 3;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void example_2() {
        int top = 7;
        int btm = -3;
        int expected = -2;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void example_3() {
        int top = 0;
        int btm = 1;
        int expected = 0;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void example_4() {
        int top = 1;
        int btm = 1;
        int expected = 1;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void test_10() {
        int top = -2147483648;
        int btm = -1;
        int expected = 2147483647;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_a() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = -10;
        int btm = 1;
        int expected = -10;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_b() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = 10;
        int btm = -1;
        int expected = -10;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_c() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = 10;
        int btm = 1;
        int expected = 10;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void edge_10_d() {
        // make sure we handle all the combos around the divide by 1 stuff
        int top = -10;
        int btm = -1;
        int expected = 10;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void test_16() {
        int top = 2147483647;
        int btm = 3;
        int expected = 715827882;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void test_624() {
        int top = Integer.MIN_VALUE;
        int btm = Integer.MIN_VALUE;
        int expected = 1;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }


    @Test
    public void test_12() {
        int top = Integer.MIN_VALUE;
        int btm = 1;
        int expected = Integer.MIN_VALUE;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }

    @Test
    public void test_14() {
        // time limit exceeded
        int top = Integer.MIN_VALUE;
        int btm = 2;
        int expected = -1073741824;
        Solution tested = new Solution();

        int actual = tested.divide(top,btm);

        assertEquals(expected,actual);
    }
}