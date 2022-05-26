package daily;

import java.util.*;

public class LC743NetworkDelayTime {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] input = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};

        System.out.println(s.networkDelayTime(input, 4, 2));
    }

    static class Solution {
        public int networkDelayTime(int[][] times, int n, int k) {
            Map<Integer, List<Node>> graph = new HashMap<>();

            for (int i = 0; i < times.length; i++) {
                Node cur = new Node(times[i][1], times[i][2]);
                if (graph.get(times[i][0]) == null) {
                    List<Node> newList = new ArrayList<>();
                    newList.add(cur);
                    graph.put(times[i][0], newList);
                } else {
                    graph.get(times[i][0]).add(cur);
                }
            }

            int[] signalReceivedAt = new int[n + 1];
            Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

            dijkstra(signalReceivedAt, k, graph);
            signalReceivedAt[0] = Integer.MIN_VALUE;
            int res = Arrays.stream(signalReceivedAt).max().getAsInt();

            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private void dijkstra(int[] signalReceivedAt, int source, Map<Integer, List<Node>> graph) {
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.timeCost - b.timeCost);

            pq.offer(new Node(source, 0));

            signalReceivedAt[source] = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int curNodeId = cur.nodeId;
                int curTimeCost = cur.timeCost;

                // 已经访问过了，当前距离更远
                if (curTimeCost > signalReceivedAt[curNodeId]) {
                    continue;
                }

                // 图里没这个点
                if (!graph.containsKey(curNodeId)) {
                    continue;
                }

                for (Node neighNode : graph.get(curNodeId)) {
                    int neighTimeCost = neighNode.timeCost;
                    int neighNodeId = neighNode.nodeId;

                    // signalReceiveTime 存储着当前最近的路径
                    // 如果比较大，那么说明当前的路径更短，所以将其更新
                    if (signalReceivedAt[neighNodeId] > curTimeCost + neighTimeCost) {
                        signalReceivedAt[neighNodeId] = curTimeCost + neighTimeCost;
                        pq.offer(new Node(neighNodeId, signalReceivedAt[neighNodeId]));
                    }
                }
            }
        }
    }

    /**
     * Node存储着某个节点到邻居节点的id和距离
     */
    static class Node {
        int nodeId;
        int timeCost;

        public Node(int nodeId, int timeCost) {
            this.nodeId = nodeId;
            this.timeCost = timeCost;
        }
    }
}
