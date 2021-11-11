package io.github.dwin357.leetcode.oneTwentyOne;

/*
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock.
 * Memory Usage: 52.3 MB, less than 62.29% of Java online submissions for Best Time to Buy and Sell Stock
 */

public class Solution {
    public int maxProfit(int[] prices) {

        int strikePrice = Integer.MAX_VALUE;
        int bestSpread = 0;
        int currentSpread;

        for(int price : prices) {
            // if price is a new low, reset min value & step fwd
            if(strikePrice > price) {
                strikePrice = price;
                continue;
            }

            // if (price - current-min) is greater than any previous spread, update cache
            currentSpread = price - strikePrice;
            if(currentSpread > bestSpread) {
                bestSpread = currentSpread;
            }

        }

        // return best cached result
        return bestSpread;
    }
}
