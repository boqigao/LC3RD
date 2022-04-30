package daily;

import java.util.*;

/**
 * dijkstra算法
 * 并查集
 * binary
 */
public class LC1631PathWithMinimumEffort {
    public static void main(String[] args) {
        SolutionUnionFind s = new SolutionUnionFind();
        int[][] heights = {{1,10,6,7,9,10,4,9}};
        System.out.println(s.minimumEffortPath(heights));
        SolutionBinaryBFS s1 = new SolutionBinaryBFS();
        System.out.println(s1.minimumEffortPath(heights));
    }

    /**
     * binary dfs
     */
    class SolutionBinaryDFS {
        public int minimumEffortPath(int[][] heights) {
            int left = 0;
            int right = 1000000;
            int result = right;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (dfsUtil(heights, mid)) {
                    result = Math.min(result, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean dfsUtil(int[][] heights, int mid) {
            int row = heights.length;
            int col = heights[0].length;
            boolean visited[][] = new boolean[row][col];
            return canReachDestinaton(0, 0, heights, visited, row, col, mid);
        }

        boolean canReachDestinaton(int x, int y, int[][] heights,
                                   boolean[][] visited, int row, int col, int mid) {
            if (x == row - 1 && y == col - 1) {
                return true;
            }
            visited[x][y] = true;
            for (int[] direction : directions) {
                int adjacentX = x + direction[0];
                int adjacentY = y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[x][y]);
                    if (currentDifference <= mid) {
                        if (canReachDestinaton(adjacentX, adjacentY, heights, visited, row, col, mid))
                            return true;
                    }
                }
            }
            return false;
        }

        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }
    }

    static class SolutionBinaryBFS {
        public int minimumEffortPath(int[][] heights) {
            int left = 0;
            int right = (int) 1e6;
            int result = right;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (canReachDestination(heights, mid)) {
                    result = Math.min(result, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        private boolean canReachDestination(int[][] heights, int k) {
            int row = heights.length;
            int col = heights[0].length;
            Deque<Cell> deque = new ArrayDeque<>();
            boolean[][] visited = new boolean[row][col];
            deque.add(new Cell(0, 0));
            visited[0][0] = true;

            while (!deque.isEmpty()) {
                Cell cur = deque.poll();
                if (cur.x == row - 1 && cur.y == col - 1) {
                    return true;
                }

                int x = cur.x;
                int y = cur.y;

                for (int[] dir : directions) {
                    int adjX = x + dir[0];
                    int adjY = y + dir[1];

                    if (cellIsValid(adjX, adjY, row, col) && !visited[adjX][adjY]) {
                        int curDiff = Math.abs(heights[adjX][adjY] - heights[x][y]);
                        if (curDiff <= k) {
                            visited[adjX][adjY] = true;
                            deque.offer(new Cell(adjX, adjY));
                        }
                    }
                }
            }
            return false;
        }

        private boolean cellIsValid(int x, int y, int row, int col) {
            return x >= 0 && x < row && y >= 0 && y < col;
        }

        class Cell {
            int x;
            int y;

            public Cell(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }

    //并查集 好理解
    static class SolutionUnionFind {

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            if (row == 1 && col == 1) {
                return 0;
            }
            UnionFind uf = new UnionFind(heights);

            List<Edge> edgeList = uf.edgeList;
            Collections.sort(edgeList, (e1, e2) -> e1.diff - e2.diff);

            // 从小diff开始找，类似于贪心算法，找到最初能和最后连起来了的话，就一定是最小的了！！
            for (int i = 0; i < edgeList.size(); i++) {
                int x = edgeList.get(i).x;
                int y = edgeList.get(i).y;
                uf.union(x, y);
                if (uf.find(0) == uf.find(row * col - 1)) {
                    return edgeList.get(i).diff;
                }
            }
            return -1;
        }

        class UnionFind {
            int[] root;
            int[] rank;
            List<Edge> edgeList;

            public UnionFind(int[][] heights) {
                int row = heights.length;
                int col = heights[0].length;

                root = new int[row * col];
                rank = new int[row * col];
                edgeList = new ArrayList<>();

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        // 这里只要计算i-1，j-1，不计算i+1 j+1是因为已经算了绝对值，都一样
                        if (i > 0) {
                            edgeList.add(new Edge(i * col + j, (i - 1) * col + j,
                                    Math.abs(heights[i][j] - heights[i - 1][j])));
                        }
                        if (j > 0) {
                            edgeList.add(new Edge(i * col + j, i * col + j - 1,
                                    Math.abs(heights[i][j] - heights[i][j - 1])));
                        }
                        root[i * col + j] = i * col + j;
                        rank[i * col + j] = i * col + j;
                    }
                }
            }

            public int find(int x) {
                if (root[x] == x) {
                    return x;
                }
                return root[x] = find(root[x]);
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);

                if (rootX == rootY) {
                    return;
                }

                if (rank[rootX] >= rank[rootY]) {
                    root[rootY] = rootX;
                    rank[rootX] += rank[rootY];
                } else {
                    root[rootX] = rootY;
                    rank[rootY] += rank[rootX];
                }
            }


        }

        class Edge {
            int x;
            int y;
            int diff;

            public Edge(int x, int y, int diff) {
                this.x = x;
                this.y = y;
                this.diff = diff;
            }
        }
    }

    //dijkstra 不好理解
    static class SolutionDijkstra {
        int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            // different matrix 用来记录到远点的diff
            int[][] diffMatrix = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    diffMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
            diffMatrix[0][0] = 0;
            PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.difference));

            boolean[][] visited = new boolean[row][col];
            queue.add(new Cell(0, 0, 0));


            // 这个部分基本上是dijkstra
            while (!queue.isEmpty()) {
                Cell curr = queue.poll();
                visited[curr.x][curr.y] = true;
                if (curr.x == row - 1 && curr.y == col - 1) {
                    return curr.difference;
                }

                for (int[] direction : directions) {
                    int adjX = curr.x + direction[0];
                    int adjY = curr.y + direction[1];
                    if (isValidCell(adjX, adjY, row, col) && !visited[adjX][adjY]) {
                        int curDiff = Math.abs(heights[adjX][adjY] - heights[curr.x][curr.y]);
                        int maxDiff = Math.max(curDiff, diffMatrix[curr.x][curr.y]);
                        if (diffMatrix[adjX][adjY] > maxDiff) {
                            diffMatrix[adjX][adjY] = maxDiff;
                            // cell 的里面只保存最开始到现在的最大值
                            queue.add(new Cell(adjX, adjX, maxDiff));
                        }
                    }
                }
            }
            return diffMatrix[row - 1][col - 1];
        }

        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x < row && y >= 0 && y < col;
        }

        class Cell {
            int x;
            int y;
            Integer difference;

            Cell(int x, int y, Integer difference) {
                this.x = x;
                this.y = y;
                this.difference = difference;
            }
        }
    }
}
