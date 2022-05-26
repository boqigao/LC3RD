package weekely;

import java.util.HashSet;
import java.util.Set;

public class LC694NumberOfDistinctIslands {
    class Solution {
        private int[][] grid;
        private boolean[][] visited;
        private StringBuffer currentIsland;

        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.visited = new boolean[grid.length][grid[0].length];
            Set<String> islands = new HashSet<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    currentIsland = new StringBuffer();
                    dfs(row, col, '0');
                    if (currentIsland.length() == 0) {
                        continue;
                    }
                    islands.add(currentIsland.toString());
                }
            }
            return islands.size();
        }

        private void dfs(int row, int col, char dir) {
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length)
                return;
            if (visited[row][col] || grid[row][col] == 0)
                return;
            visited[row][col] = true;
            currentIsland.append(dir);
            dfs(row + 1, col, 'D');
            dfs(row - 1, col, 'U');
            dfs(row, col + 1, 'R');
            dfs(row, col - 1, 'L');
            currentIsland.append('0');
        }
    }

    /**
     * forget the distinct island
     */
    class SolutionWrong {
        int row, col;
        boolean[][] visited;
        int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

        public int numDistinctIslands(int[][] grid) {
            row = grid.length;
            col = grid[0].length;

            visited = new boolean[row][col];
            int ans = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (!visited[i][j] && grid[i][j] == 1) {
                        ans++;
                        dfs(grid, i, j);
                    }
                }
            }
            return ans;
        }

        private void dfs(int[][] grid, int i, int j) {
            visited[i][j] = true;
            for (int[] dir : dirs) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                if (isValid(newI, newJ) && !visited[newI][newJ]) {
                    dfs(grid, newI, newJ);
                }
            }
        }

        private boolean isValid(int i, int j) {
            return i >= 0 && j >= 0 && i < row && j < col;
        }
    }
}
