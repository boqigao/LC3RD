package facebook;

public class LC236LowestCommonAncestorOfABinaryTree {

    class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        TreeNode ans;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            traverse(root, p, q);
            return this.ans;
        }

        public boolean traverse(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return false;
            }

            int left = traverse(root.left, p, q) ? 1 : 0;
            int right = traverse(root.right, p, q) ? 1 : 0;

            int mid = (root == q || root == p) ? 1 : 0;

            if (left + right + mid >= 2) {
                this.ans = root;
            }

            return (left + right + mid) > 1;
        }
    }
}
