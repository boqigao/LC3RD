package daily;

import java.util.*;

public class LC173BSTIterator {

    // with stack
    class BSTIterator {
        Deque<TreeNode> stack;

        private void leftMostInorder (TreeNode node) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
        }

        public BSTIterator(TreeNode root) {
            this.stack = new ArrayDeque<>();
            this.leftMostInorder(root);
        }

        public int next() {
            TreeNode topmostNode = stack.pop();
            /**
             *      4
             *     2 5
             * 这样的树，刚开始 4 ，2都压进去了，然后出2，出4，然后出4的时候又顺便把5压进去了
             */
            if (topmostNode.right != null) {
                leftMostInorder(topmostNode.right);
            }
            return topmostNode.val;
        }

        public boolean hasNext() {
            return this.stack.size() > 0;
        }
    }

    class BSTIterator2 {
        List<Integer> arr = new ArrayList<>();
        int pointer;
        int size;

        private void inorder(TreeNode node) {
            if (node == null) return;
            inorder(node.left);
            arr.add(node.val);
            inorder(node.right);
        }

        public BSTIterator2(TreeNode root) {
            pointer = -1;
            inorder(root);
            size = arr.size();
        }

        public int next() {
            pointer++;
            return arr.get(pointer);
        }

        public boolean hasNext() {
            if (pointer + 1 < size) {
                return true;
            } else {
                return false;
            }
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
