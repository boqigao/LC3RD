package daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

//k ey words: 并查集，图

/**
 * key words 最小生成树，minimum spanning tree
 *
 * prim
 * kruskal
  */
public class LC1584MinCostToConnectAllPoints {

    class SolutionPrimOptimized {
        class Solution {
            public int minCostConnectPoints(int[][] points) {
                int n = points.length;
                int mstCost = 0;
                int edgesUsed = 0;

                // Track nodes which are visited.
                boolean[] inMST = new boolean[n];

                int[] minDist = new int[n];
                minDist[0] = 0;

                for (int i = 1; i < n; ++i) {
                    minDist[i] = Integer.MAX_VALUE;
                }

                while (edgesUsed < n) {
                    int currMinEdge = Integer.MAX_VALUE;
                    int currNode = -1;

                    // Pick least weight node which is not in MST.
                    for (int node = 0; node < n; ++node) {
                        if (!inMST[node] && currMinEdge > minDist[node]) {
                            currMinEdge = minDist[node];
                            currNode = node;
                        }
                    }

                    mstCost += currMinEdge;
                    edgesUsed++;
                    inMST[currNode] = true;

                    // Update adjacent nodes of current node.
                    for (int nextNode = 0; nextNode < n; ++nextNode) {
                        int weight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                                Math.abs(points[currNode][1] - points[nextNode][1]);

                        if (!inMST[nextNode] && minDist[nextNode] > weight) {
                            minDist[nextNode] = weight;
                        }
                    }
                }

                return mstCost;
            }
        }
    }

    class SolutionPrim {
        public int minCostConnectPoints(int[][] points) {
            if (points == null || points.length == 0) {
                return 0;
            }
            int size = points.length;
            int res = 0;
            int count = size - 1;
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));

            boolean[] visited = new boolean[size];
            // 把所有和0有关的边都加进去
            int[] coordinate1 = points[0];
            for (int j = 1; j < size; j++) {
                // Calculate the distance between two coordinates.
                int[] coordinate2 = points[j];
                int cost = Math.abs(coordinate1[0] - coordinate2[0]) +
                        Math.abs(coordinate1[1] - coordinate2[1]);
                Edge edge = new Edge(0, j, cost);
                pq.add(edge);
            }
            visited[0] = true;

            while (!pq.isEmpty() && count > 0) {
                Edge edge = pq.poll();
                int point1 = edge.point1;
                int point2 = edge.point2;
                int cost = edge.cost;

                if (!visited[point2]) {
                    res += cost;
                    visited[point2] = true;
                    for (int j = 1; j < size; j++) {
                        if (!visited[j]) {
                            int dist = Math.abs(points[j][0] - points[point2][0]) + Math.abs(points[j][1] - points[point2][1]);
                            pq.add(new Edge(point2, j, dist));
                        }
                    }
                    // 追加了新边才算，不然不能减count
                    count--;
                }
            }
            return res;
        }

        class Edge {
            int point1;
            int point2;
            int cost;


            Edge(int point1, int point2, int cost) {
                this.point1 = point1;
                this.point2 = point2;
                this.cost = cost;
            }
        }

    }

    /**
     * Kruskal Algorithm
     */
    class SolutionKruskal {
        public int minCostConnectPoints(int[][] points) {
            // kruskal 最小生成树 minimum spanning tree
            int n = points.length;
            ArrayList<int[]> allEdges = new ArrayList<>();

            for (int curr = 0; curr < n; curr++) {
                for (int next = curr + 1; next < n; next++) {
                    int weight = Math.abs(points[curr][0] - points[next][0]) + Math.abs(points[curr][1] - points[next][1]);
                    int[] currEdge = {weight, curr, next};
                    allEdges.add(currEdge);
                }
            }

            // sort all edges in increasing order
            Collections.sort(allEdges, Comparator.comparingInt(a -> a[0]));

            UnionFind uf = new UnionFind(n);
            int mstCost = 0;
            int edgesUsed = 0;

            for (int i = 0; i < allEdges.size() && edgesUsed < n - 1; i++) {
                int node1 = allEdges.get(i)[1];
                int node2 = allEdges.get(i)[2];
                int weight = allEdges.get(i)[0];

                if (uf.union(node1, node2)) {
                    mstCost += weight;
                    edgesUsed++;
                }
            }
            return mstCost;

        }

        class UnionFind {
            // array to store the root
            public int[] group;
            // array to store the rank of each node
            public int[] rank;

            public UnionFind(int size) {
                group = new int[size];
                rank = new int[size];
                for (int i = 0; i < size; i++) {
                    group[i] = i;
                }
            }

            public int find(int node) {
                if (group[node] != node) {
                    // update group[node] at the same time
                    group[node] = find(group[node]);
                }
                return group[node];
            }

            public boolean union(int node1, int node2) {
                int group1 = find(node1);
                int group2 = find(node2);

                // node 1 and node 2 already belong to same group
                if (group1 == group2) {
                    return false;
                }

                // follows the large rank
                if (rank[group1] > rank[group2]) {
                    group[group2] = group1;
                } else if (rank[group1] < rank[group2]) {
                    group[group1] = group2;
                } else {
                    // if all the same, group 1 will be merged into group 2
                    group[group1] = group2;
                    rank[group2] += 1;
                }
                return true;
            }
        }

        class Edge {
            int point1;
            int point2;
            int cost;


            Edge(int point1, int point2, int cost) {
                this.point1 = point1;
                this.point2 = point2;
                this.cost = cost;
            }
        }
    }

    // 暴力解法，对于每一个点，计算其他所有点的曼哈顿距离
    // 错误解法！会导致这类问题：  a-b.. c-d 但是这两个子图互相不连接
    class SolutionError {
        public int minCostConnectPoints(int[][] points) {
            if (points.length <= 1) {
                return 0;
            }
            int ans = 0;
            int numPoints = points.length;
            int[] connected = new int[numPoints];
            int i, j;
            for (i = 0; i < points.length; i++) {
                if (connected[i] == 1) {
                    continue;
                }
                int minDist = Integer.MAX_VALUE;
                int connectedTo = -1;
                for (j = 0; j < points.length; j++) {
                    if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                        continue;
                    }
                    int dist = calcManhatDist(points[i], points[j]);
                    if (minDist > dist) {
                        minDist = dist;
                        connectedTo = j;
                    }
                }
                connected[i] = 1;
                connected[connectedTo] = 1;
                ans += minDist;
            }
            return ans;
        }

        private int calcManhatDist(int[] point1, int[] point2) {
            return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
        }
    }
}
