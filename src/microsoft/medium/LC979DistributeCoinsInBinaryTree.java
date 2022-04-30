package microsoft.medium;


public class LC979DistributeCoinsInBinaryTree {
    class Solution {
        int ans;
        public int distributeCoins(TreeNode root) {
            ans = 0;
            dfs(root);
            return ans;
        }

        public int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int l = dfs(node.left);
            int r = dfs(node.right);
            ans += Math.abs(l) + Math.abs(r);

            //这里最关键了，这个node要上报什么呢？
            // 他要上报，
            // 自己左边加右边 缺少/多余 了几个： 即 L + R
            // 还有自己本身 缺少/多余了几个: 即 node.val - 1 因为只要保持一个就行了
            return node.val - 1 + l + r;

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
