package daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KthSmallestElementInBST {

    public static class TreeNode {
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
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t3.left =t1;
        t1.right = t2;
        t3.right = t4;
        System.out.println(s.kthSmallest(t3, 1));
    }

    /**
     * Entire inorder traversal
     */
    static class Solution {
        public int kthSmallest(TreeNode root, int k) {
            List<Integer> list = new ArrayList<>();
            inorder(root, list);
            return list.get(k - 1);
        }

        private TreeNode inorder(TreeNode node, List<Integer> list) {
            if (node == null) {
                return null;
            }
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
            return null;
        }
    }

    /**
     * using stack to traverse only necessary
     */
    static class Solution2 {
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();

            while (true) {
                while (root!=null) {
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();
                k--;
                if (k==0) {
                    return root.val;
                }
                root = root.right;
            }

        }
    }
}
