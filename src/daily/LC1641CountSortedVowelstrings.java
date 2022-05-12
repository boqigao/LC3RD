package daily;

import java.util.Arrays;

public class LC1641CountSortedVowelstrings {
    public static void main(String[] args) {

    }

    class SolutionMath {
        public int countVowelStrings(int n) {
            return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
        }
    }

    static class SolutionDpTopDown {
        public int countVowelStrings(int n) {
            int[][] dp = new int[n + 1][5];

            Arrays.fill(dp[0], 1);

            for (int i = 1; i < n + 1; i++) {
                // 下一行的a的总数，指的是上一行所有的string之前都能加新的a，e，。。所以是上一行的所有sum！
                // 这个理解起来更舒服一点
                for (int k = 0; k < 5; k++) {
                    int sum = 0;
                    for (int j = k; j < 5; j++) {
                        sum += dp[i-1][j];
                    }
                    dp[i][k] = sum;
                }
            }
            return dp[n][0];
        }
    }


    /**
     * dp[n][k] means the number of strings constructed by at most k different characters.
     * <p>
     * If k = 1, use only u
     * If k = 2, use only o,u
     * If k = 3, use only i,o,u
     * If k = 4, use only e,i,o,u
     * If k = 5, use only a,e,i,o,u
     */
    static class SolutionDpSpaceOnBottomUp {
        public int countVowelStrings(int n) {
            int[][] dp = new int[n + 1][5];

            Arrays.fill(dp[0], 1);
            for (int i = 0; i < n + 1; i++) {
                dp[i][4] = 1;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 3; j >= 0; j--) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j + 1];
                }
            }

            return dp[n][0];
        }
    }

    class SolutionDPSpaceO1 {
        public int countVowelStrings(int n) {
//                a  e  i  o  u
//     initialy: {1, 1, 1, 1, 1}
//      n == 1 : {5, 4, 3, 2, 1}
//      n == 2 : {15,10,6, 3, 1}
//      n == 3 : {35,20,10,4, 1}
//
            int[] permutation = {1, 1, 1, 1, 1};

            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = permutation.length - 1; j >= 0; j--) {
                    permutation[j] = permutation[j] + sum;
                    sum = permutation[j];
                }
            }

            return permutation[0];
        }
    }
}
