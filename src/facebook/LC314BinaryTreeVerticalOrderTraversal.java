package facebook;

import java.util.*;

public class LC314BinaryTreeVerticalOrderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public List<List<Integer>> verticalOrderWithoutSort(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Queue<Pair> queue = new ArrayDeque<>();
            int column = 0;
            List<List<Integer>> ans = new ArrayList<>();
            Map<Integer, List<Integer>> map = new HashMap<>();

            queue.offer(new Pair(root, column));
            int minColumn = 0, maxColumn = 0;
            while(!queue.isEmpty()) {
                Pair pair = queue.poll();
                TreeNode curNode = pair.node;
                int curColumn = pair.column;

                if (curNode != null) {
                    // add the val to the corresponding columnList
                    if (!map.containsKey(curColumn)) {
                        map.put(curColumn, new ArrayList<>());
                    }
                    map.get(curColumn).add(curNode.val);

                    minColumn = Math.min(minColumn, curColumn);
                    maxColumn = Math.max(maxColumn, curColumn);

                    queue.offer(new Pair(curNode.left, curColumn - 1));
                    queue.offer(new Pair(curNode.right, curColumn + 1));
                }
            }

            for (int i = minColumn; i <= maxColumn; i++) {
                ans.add(map.get(i));
            }

            return ans;
        }
        public List<List<Integer>> verticalOrder(TreeNode root) {
            Queue<Pair> queue = new ArrayDeque<>();
            int column = 0;
            List<List<Integer>> ans = new ArrayList<>();
            Map<Integer, List<Integer>> map = new HashMap<>();

            queue.offer(new Pair(root, column));

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                TreeNode curNode = pair.node;
                int curColumn = pair.column;

                if (curNode != null) {
                    // add the val to the corresponding columnList
                    if (!map.containsKey(curColumn)) {
                        map.put(curColumn, new ArrayList<>());
                    }
                    map.get(curColumn).add(curNode.val);

                    queue.offer(new Pair(curNode.left, curColumn - 1));
                    queue.offer(new Pair(curNode.right, curColumn + 1));
                }
            }
            List<Integer> sortedKey = new ArrayList<Integer>(map.keySet());
            Collections.sort(sortedKey);

            for (int k : sortedKey) {
                ans.add(map.get(k));
            }
            return ans;
        }
    }

    class Pair {
        TreeNode node;
        Integer column;

        Pair(TreeNode node, Integer column) {
            this.node = node;
            this.column = column;
        }
    }
}
