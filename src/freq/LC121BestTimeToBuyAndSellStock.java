package freq;

/**
 * key words, stock, dp,
 */
public class LC121BestTimeToBuyAndSellStock {
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0){
                return 0;
            }
            int len = prices.length;
            int[][] dp = new int[len + 1][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            // dp[i][0]的意思是在第i天卖出，dp[i][1]的意思是在第i天买入
            for (int i = 1; i < len; i++) {
                // 要么休息，要么卖
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] +  prices[i]);
                // 要么休息，要么买， 同时因为只能买一次，所以要么前面买，要么这天买
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }
            return dp[len - 1][0];
        }
    }
}
