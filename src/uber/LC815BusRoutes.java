package uber;

import java.util.*;

/**
 * key words:
 * intersect of two array
 * bfs
 * graph
 *
 * 有权图：Map<Integer, List<Node>>
 * 无向图: Map<Integer, List<Integer>>
 *
 */
public class LC815BusRoutes {

    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if (source == target)
                return 0;
            int len = routes.length;
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                Arrays.sort(routes[i]);
                // a graph contains routes as vertices
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    // if the routes intersect
                    if (intersect(routes[i], routes[j])) {
                        // in the graph, two vertices are connected
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }


            // set the source into queue, set the target into targetList
            // reason: a source or target may in different routes
            Queue<Point> queue = new ArrayDeque<>();
            List<Integer> targetRoutes = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (Arrays.binarySearch(routes[i], source) >= 0) {
                    queue.offer(new Point(i, 0));
                }
                if (Arrays.binarySearch(routes[i], target) >= 0) {
                    targetRoutes.add(i);
                }
            }

            Set<Integer> visited = new HashSet<>();


            // using bfs
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
                int routeId = cur.routeID;
                int curDepth = cur.curDepth;

                if (targetRoutes.contains(routeId)) {
                    return curDepth + 1;
                }

                for (int neigh : graph.get(routeId)) {
                    if (!visited.contains(neigh)) {
                        visited.add(neigh);
                        queue.add(new Point(neigh, curDepth + 1));
                    }
                }
            }
            // no connected
            return -1;

        }

        private boolean intersect(int[] routeA, int[] routeB) {
            int i = 0, j = 0;
            while (i < routeA.length && j < routeB.length) {
                if (routeA[i] == routeB[j]) {
                    return true;
                } else {
                    if (routeA[i] > routeB[j]) {
                        j++;
                    } else {
                        i++;
                    }
                }
            }
            return false;
        }


    }

    class Point {
        int routeID;
        int curDepth;

        Point(int routeID, int curDepth) {
            this.routeID = routeID;
            this.curDepth = curDepth;
        }
    }
}
