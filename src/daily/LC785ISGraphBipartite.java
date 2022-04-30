package daily;

import java.util.Arrays;
import java.util.Stack;

public class LC785ISGraphBipartite {
    class Solution {
        public boolean isBipartite(int[][] graph) {
            if (graph.length <= 1) {
                return true;
            }
            int n = graph.length;
            int[] color = new int[n];
            Arrays.fill(color, -1);

            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < n; i++) {
                // not met before
                if (color[i] == -1) {
                    stack.clear();
                    stack.push(i);
                    color[i] = 0;

                    // bfs of i node
                    while (!stack.isEmpty()) {
                        Integer node = stack.pop();
                        for (int nei : graph[node]) {
                            if (color[nei] == -1) {
                                stack.push(nei);
                                color[nei] = color[node] ^ 1;
                            } else if (color[nei] == color[node]) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;


        }
    }
}
