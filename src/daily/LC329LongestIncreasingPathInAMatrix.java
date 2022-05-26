package daily;

import java.util.ArrayList;
import java.util.List;

public class LC329LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        SolutionDP s = new SolutionDP();
        int[][] input = new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 2, 1}};
        System.out.println(s.longestIncreasingPath(input));
    }

    /**
     * 拓扑排序，peeling onion
     */
    static class SolutionDP {
        private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;

        public int longestIncreasingPath(int[][] grid) {
            int m = grid.length;
            if (m == 0)
                return 0;
            int n = grid[0].length;
            // padding the matrix with zero as boundaries
            // assuming all positive integer, otherwise use INT_MIN as boundaries
            int[][] matrix = new int[m + 2][n + 2];
            for (int i = 0; i < m; ++i)
                System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);

            // calculate outdegrees
            int[][] outdegree = new int[m + 2][n + 2];
            for (int i = 1; i <= m; ++i)
                for (int j = 1; j <= n; ++j)
                    for (int[] d : dir)
                        //只要 周围有一个比他大的，就++
                        if (matrix[i][j] < matrix[i + d[0]][j + d[1]])
                            outdegree[i][j]++;

            // find leaves who have zero out degree as the initial level
            n += 2;
            m += 2;
            List<int[]> leaves = new ArrayList<>();
            for (int i = 1; i < m - 1; ++i)
                for (int j = 1; j < n - 1; ++j)
                    // 从local maximum开始计算
                    if (outdegree[i][j] == 0)
                        leaves.add(new int[]{i, j});

            // remove leaves level by level in topological order
            int height = 0;
            while (!leaves.isEmpty()) {
                // 所以说这道题的核心就是找到多少层相邻的localmaximum
                // 与其说是剥洋葱，不如叫做削减山峰，一层一层把山峰从顶端削平
                height++;
                List<int[]> newLeaves = new ArrayList<>();
                // leaves 之前是local maximum
                for (int[] node : leaves) {
                    for (int[] d : dir) {
                        // node[0], node[1] 代表的是localmaximum，就是所有邻居都小于等于他
                        // node[x], node[y] 代表的是他的邻居
                        int x = node[0] + d[0], y = node[1] + d[1];
                        // 他的值如果比这个邻居大
                        if (matrix[node[0]][node[1]] > matrix[x][y]) {
                            // 那么这个邻居的值--，因为已经去除了一个比（当前邻居）大的节点
                            outdegree[x][y]--;
                            // 如果这个邻居的值也变成0了，那么说明这个邻居周围剩下的节点都比他小了，
                            if (outdegree[x][y] == 0) {
                                // 那么当前邻居也变成了 下一个local maximum
                                newLeaves.add(new int[]{x, y});
                            }
                        }
                    }
                }
                leaves = newLeaves;
            }
            return height;
        }
    }

    class SolutionDFS {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int m, n;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0)
                return 0;
            m = matrix.length;
            n = matrix[0].length;
            int[][] cache = new int[m][n];

            int ans = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, dfs(matrix, i, j, cache));
                }
            }
            return ans;
        }

        private int dfs(int[][] matrix, int i, int j, int[][] cache) {
            if (cache[i][j] != 0)
                return cache[i][j];
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                    cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
                }
            }
            // ++ because this is a valid solution
            cache[i][j]++;
            return cache[i][j];
        }
    }
}
