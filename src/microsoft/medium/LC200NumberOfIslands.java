package microsoft.medium;

public class LC200NumberOfIslands {
    class Solution {
        public int numIslands(char[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            if (col == 0) {
                return 0;
            }

            int ans = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        ans++;
                        dfs(grid, i, j);
                    }
                }
            }
            return ans;
        }
        private void dfs(char[][] grid, int x, int y) {
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                return;
            }
            if (grid[x][y] == '2' || grid[x][y] == '0') {
                return;
            }


            grid[x][y] = '2';
            dfs(grid, x + 1, y);
            dfs(grid, x - 1, y);
            dfs(grid, x, y + 1);
            dfs(grid, x, y - 1);
        }
    }
}
