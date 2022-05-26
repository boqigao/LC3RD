package daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC1302DeepestLeavesSum {
    class Solution {
        public int deepestLeavesSum(TreeNode root) {
            List<Integer> res = new ArrayList<>();

            Queue<TreeNode> queue = new LinkedList<>();

            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                int curSum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    curSum += cur.val;

                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }

                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }

                res.add(curSum);
            }
            return res.get(res.size() - 1);
        }
    }

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
}
