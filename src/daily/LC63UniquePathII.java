package daily;

/**
 * keyword: dp 因为只能往右或者往下
 */
public class LC63UniquePathII {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][] {{0,0,0}, {0,1,0},{0,0,0}};
        System.out.println(s.uniquePathsWithObstacles(grid));
    }
    static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int row = obstacleGrid.length;
            int col = obstacleGrid[0].length;

            int[][] dp = new int[row][col];
            if (obstacleGrid[0][0] == 1) {
                dp[0][0] = 0;
            } else {
                dp[0][0] = 1;
            }

            for (int i = 1; i < row; i++) {
                if (obstacleGrid[i][0] == 0 && dp[i-1][0] == 1) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = 0;
                }
            }

            for (int j = 1; j < col; j++) {
                if (obstacleGrid[0][j] == 0 && dp[0][j - 1] == 1) {
                    dp[0][j] = 1;
                } else {
                    dp[0][j] = 0;
                }
            }

            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }

            return dp[row - 1][col - 1];
        }
    }
}
