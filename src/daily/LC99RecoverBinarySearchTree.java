package daily;

import java.util.ArrayDeque;

public class LC99RecoverBinarySearchTree {

    // 递归
    class Solution2 {
        TreeNode x;
        TreeNode y;
        TreeNode pre;
        public void findNodes(TreeNode root) {
            if (root == null) return;
            findNodes(root.left);
            if (pre != null && root.val < pre.val) {
                y = root;
                if (x == null) {
                    x = pre;
                } else {
                    return;
                }
            }
            pre = root;
            findNodes(root.right);
        }
        public void recoverTree(TreeNode root) {
            findNodes(root);
            swap(x, y);
        }


        public void swap(TreeNode x, TreeNode y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }
    // 用栈实现迭代
    class Solution {
        public void recoverTree(TreeNode root) {
            ArrayDeque<TreeNode> stack = new ArrayDeque<>();

            TreeNode x = null;
            TreeNode y = null;
            TreeNode pre = null;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();
                if (pre != null && root.val < pre.val) {
                    y = root;
                    if (x == null) {
                        x = pre;
                    } else {
                        break;
                    }
                }

                // pre网上移动
                pre = root;
                // root往下移
                root = root.right;
            }

            swap(x, y);
        }

        public void swap(TreeNode x, TreeNode y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
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
