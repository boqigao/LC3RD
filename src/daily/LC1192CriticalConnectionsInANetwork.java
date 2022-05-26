package daily;

import java.util.*;

/**
 * keyword: dfs
 * Tarjan's algorithm
 * <p>
 * Thus, the problem simply boils down to finding all the cycles in the graph and discarding all the edges belonging to such
 * cycles. If we do that, we will only be left with edges that are critical connections in the graph.
 */
public class LC1192CriticalConnectionsInANetwork {

    class Solution {
        private Map<Integer, List<Integer>> graph;

        // rank of a node
        private Map<Integer, Integer> rank;

        private Set<String> connectedEdges = new HashSet<>();

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            this.formGraph(n, connections);
            this.dfs(0, 0);
            List<List<Integer>> res = new ArrayList<>();

            for (String s : connectedEdges) {
                String[] strs = s.split("_");
                Integer i1 = Integer.parseInt(strs[0]);
                Integer i2 = Integer.parseInt(strs[1]);
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i1);
                tmp.add(i2);
                res.add(tmp);
            }
            return res;

        }

        private int dfs(int node, int discoveryRank) {
            if (this.rank.get(node) != null) {
                return this.rank.get(node);
            }

            this.rank.put(node, discoveryRank);

            int minRank = discoveryRank + 1;

            for (int neighbor : this.graph.get(node)) {

                Integer neiRank = this.rank.get(neighbor);
                if (neiRank != null && neiRank == discoveryRank - 1) {
                    continue;
                }

                int recursiveRank = this.dfs(neighbor, discoveryRank + 1);

                // 有环
                if (recursiveRank <= discoveryRank) {
                    int sortedU = Math.min(node, neighbor);
                    int sortedV = Math.max(node, neighbor);

                    this.connectedEdges.remove(sortedU + "_" + sortedV);
                }

                minRank = Math.min(minRank, recursiveRank);
            }
            return minRank;
        }

        private void formGraph(int n, List<List<Integer>> connections) {
            this.graph = new HashMap<>();
            this.rank = new HashMap<>();

            // Default rank for unvisited nodes is "null"
            for (int i = 0; i < n; i++) {
                this.graph.put(i, new ArrayList<>());
                this.rank.put(i, null);
            }

            for (List<Integer> edge : connections) {
                int u = edge.get(0);
                int v = edge.get(1);
                this.graph.get(u).add(v);
                this.graph.get(v).add(u);

                int sortedU = Math.min(u, v);
                int sortedV = Math.max(u, v);
                connectedEdges.add(sortedU + "_" + sortedV);
            }
        }
    }
}
