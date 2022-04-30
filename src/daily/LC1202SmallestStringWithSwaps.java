package daily;

import java.util.*;

/**
 * 这是一个图的问题
 * <p>
 * 比如[ab][bc]这种pair存在的话，实际上ac可以互换，就类似于昨天的并查集问题{@link LC1584MinCostToConnectAllPoints}.
 * 比如[ab][bc][cd]的话就是abcd四个节点的连通子图
 * <p>
 * 这道题可以拆分成四步
 * 1. 用初始的pair数据生成图
 * 2. 找到图中的联通子图
 * 3. 对于每一个连通子图，将其升序排序
 * 4. 生成最小的string
 */
public class LC1202SmallestStringWithSwaps {

    // union Find Solution
    class SolutionUnionFind {

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            UnionFind uf = new UnionFind(s.length());

            // 联通所有子联通分量
            for (List<Integer> edge : pairs) {
                int start = edge.get(0);
                int end = edge.get(1);

                uf.union(start, end);
            }

            Map<Integer, List<Integer>> rootToComponent = new HashMap<>();

            // group the vertices that are in the same component
            for (int vertex = 0; vertex < s.length(); vertex++) {
                int root = uf.find(vertex);

                rootToComponent.putIfAbsent(root, new ArrayList<>());
                rootToComponent.get(root).add(vertex);
            }

            // string to store the answer
            char[] ans = new char[s.length()];

            for (List<Integer> indices : rootToComponent.values()) {
                List<Character> characters = new ArrayList<>();
                for (int index : indices) {
                    characters.add(s.charAt(index));
                }

                Collections.sort(characters);
                for (int i = 0; i < indices.size(); i++) {
                    ans[indices.get(i)] = characters.get(i);
                }
            }
            return new String(ans);
        }

        class UnionFind {
            private int[] group;
            private int[] rank;

            public UnionFind(int size) {
                group = new int[size];
                rank = new int[size];
                for (int i = 0; i < size; i++) {
                    group[i] = i;
                    rank[i] = i;
                }
            }

            public int find(int node) {
                if (group[node] == node) {
                    return node;
                }
                return group[node] = find(group[node]);
            }

            public void union(int node1, int node2) {
                int root1 = find(node1);
                int root2 = find(node2);

                if (root1 != root2) {
                    if (rank[root1] >= rank[root2]) {
                        group[root2] = root1;
                        rank[root1] += rank[root2];
                    } else {
                        group[root1] = root2;
                        rank[root2] += rank[root1];
                    }
                }
            }
        }

    }

    // Dfs solution
    class SolutionDFS {
        // Maximum number of vertices
        final static int N = 100001;
        boolean[] visited = new boolean[N];
        List<Integer>[] adj = new ArrayList[N];

        /**
         * 这个函数是为了和某个未访问过的节点的所有联通节点
         *
         * @param s
         * @param vertex
         * @param characters
         * @param indices
         */
        private void DFS(String s, int vertex, List<Character> characters, List<Integer> indices) {
            characters.add(s.charAt(vertex));
            indices.add(vertex);
            visited[vertex] = true;

            // visit all the neighbor nodes
            for (int adj : adj[vertex]) {
                if (!visited[adj]) {
                    DFS(s, adj, characters, indices);
                }
            }
        }


        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            for (int i = 0; i < s.length(); i++) {
                adj[i] = new ArrayList<>();
            }
            // Build the adjacency list
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int dest = edge.get(1);

                // undirected edge
                adj[source].add(dest);
                adj[dest].add(source);
            }

            char[] answer = new char[s.length()];

            for (int vertex = 0; vertex < s.length(); vertex++) {
                // if not covered in the DFS yet
                if (!visited[vertex]) {
                    List<Character> characters = new ArrayList<>();
                    List<Integer> indices = new ArrayList<>();

                    // 访问过之后，和当前节点所有联通的节点都会被加到character和indices中
                    DFS(s, vertex, characters, indices);

                    Collections.sort(characters);
                    Collections.sort(indices);

                    // store the sorted characters corresponding to the index
                    for (int index = 0; index < characters.size(); index++) {
                        // 把重排过后的放在相应位置
                        answer[indices.get(index)] = characters.get(index);
                    }
                }
            }
            return new String(answer);

        }
    }
}
