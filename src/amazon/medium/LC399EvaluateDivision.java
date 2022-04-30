package amazon.medium;

import java.util.*;

/**
 * dfs or union find
 */
public class LC399EvaluateDivision {

    class SolutionUnionFind {

    }

    /**
     * Simple dfs with visited Set
     */
    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, List<Node>> map = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                String vertex1 = equations.get(i).get(0);
                String vertex2 = equations.get(i).get(1);
                // val = vertex1 / vertex2
                double val = values[i];

                if (!map.containsKey(vertex1)) {
                    map.put(vertex1, new ArrayList<>());
                }
                map.get(vertex1).add(new Node(vertex2, val));
                map.get(vertex1).add(new Node(vertex1, 1.0));

                if (!map.containsKey(vertex2)) {
                    map.put(vertex2, new ArrayList<>());
                }
                map.get(vertex2).add(new Node(vertex1, 1.0 / val));
                map.get(vertex2).add(new Node(vertex2, 1.0));
            }

            Set<String> visited = new HashSet<>();
            double[] res = new double[queries.size()];

            for (int i = 0; i < queries.size(); i++) {
                visited.clear();
                String start = queries.get(i).get(0);
                String end = queries.get(i).get(1);
                res[i] = dfs(start, end, map, visited);
            }
            return res;
        }

        private double dfs(String start, String end, Map<String, List<Node>> map, Set<String> visited) {
            if ((!(map.containsKey(start))) || (!map.containsKey(end))) {
                return -1.0;
            }
            if (start.equals(end)) {
                return 1.0;
            }
            visited.add(start);
            for (Node to : map.get(start)) {
                if (!visited.contains(to.to)) {
                    double ans = dfs(to.to, end, map, visited);
                    if (ans != -1.0) {
                        return ans * to.val;
                    }
                }
            }
            return -1;
        }
    }

    class Node {
        String to;
        double val;

        Node(String to, double val) {
            this.to = to;
            this.val = val;
        }
    }
}
