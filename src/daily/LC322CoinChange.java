package daily;

import java.util.*;

/**
 * keyword : dynamic programming
 * 无限背包问题
 */
public class LC322CoinChange {
    /**
     * 自顶向下的填表,理解起来更绕
     */
    class SolutionTopDown {
        public int coinChange(int[] coins, int amount) {
            if (amount < 1)
                return 0;
            return coinChange(coins, amount, new int[amount]);
        }

        private int coinChange(int[] coins, int rem, int[] count) {
            // 如果小于0了就不对了
            if (rem < 0)
                return -1;

            // 刚好，直接return
            if (rem == 0)
                return 0;

            // 如果当前值已经被记录过了，那就直接return
            if (count[rem - 1] != 0)
                return count[rem - 1];

            int min = Integer.MAX_VALUE;

            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min) {
                    min = 1 + res;
                }
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }
    }

    /**
     * 自低向上的填表，理解起来就是有点绕
     * 其实感觉自低向上都可以写成bfs，一会写一下
     */
    class SolutionBottomUp {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);

            // dp[i] 的意思是，当总金额为i时候，需要多少硬币
            dp[0] = 0;

            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    // coins[j]如果大于i就会出边界
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] == amount + 1 ? -1 : dp[amount];
        }
    }

    public static void main(String[] args) {
        SolutionBFS s = new SolutionBFS();
        System.out.println(s.coinChange(new int[]{1, 2, 5}, 11));

    }

    /**
     * bfs
     */
    static class SolutionBFS {

        public int coinChange(int[] coins, int amount) {
            Queue<int[]> queue = new ArrayDeque<>();

            Set<Integer> set = new HashSet<>();

            queue.offer(new int[] {amount, 0});
            while (!queue.isEmpty()) {
                int[] curPair = queue.poll();

                int leftAmount = curPair[0];
                int curCoins = curPair[1];

                if (leftAmount == 0) {
                    return curCoins;
                }

                // 这里使用了自顶向下的方法一点一点存了所有的可能性
                //TODO:如果是自底向上呢？还能work吗，
                for (int coin : coins) {
                    if (leftAmount - coin >= 0 && !set.contains(leftAmount - coin)) {
                        queue.offer(new int[]{leftAmount - coin, curCoins + 1});
                        set.add(leftAmount - coin);
                    }
                }
            }
            return -1;

        }
    }
}
