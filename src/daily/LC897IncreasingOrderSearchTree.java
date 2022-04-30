package daily;

public class LC897IncreasingOrderSearchTree {

    static class TreeNode {
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

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode left = new TreeNode(1);
        TreeNode root = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        s.increasingBST(root);
    }

    static class Solution {
        TreeNode cur;
        TreeNode ans;

        public TreeNode increasingBST(TreeNode root) {
            ans = new TreeNode(0);
            // cur和ans是同一个object，所以cur.right追加了，那么ans.right也会追加
            cur = ans;
            inorder(root);
            return ans.right;

        }

        public void inorder(TreeNode node) {
            if (node == null) return;
            inorder(node.left);
            node.left = null;
            cur.right = node;
            cur = node;
            inorder(node.right);
        }
    }

    class Solution2 {
        private TreeNode head;

        public TreeNode increasingBST(TreeNode root) {
            head = null;
            increasingBSTRecur(root);
            return head;
        }

        private void increasingBSTRecur(TreeNode root) {
            if (root == null) return;
            increasingBSTRecur(root.right);
            TreeNode tmp = root.left; // make left child null and save it before making null
            root.left = null;
            root.right = head;
            head = root;
            increasingBSTRecur(tmp);
        }
    }
}
