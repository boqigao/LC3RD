package weekely;

import java.util.ArrayList;
import java.util.List;

public class LC1568BinarySearchTreeIteratorII {
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

    class BSTIterator {
        List<Integer> arr = new ArrayList<>();
        int size;
        int pointer;

        private void inorder(TreeNode node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            arr.add(node.val);
            inorder(node.right);
        }

        public BSTIterator(TreeNode root) {
            inorder(root);
            this.size = arr.size();
            this.pointer = -1;
        }

        public boolean hasNext() {
            return pointer + 1 < size;
        }

        public int next() {
            pointer++;
            return arr.get(pointer);
        }

        public boolean hasPrev() {
            return pointer - 1 >= 0;
        }

        public int prev() {
            pointer--;
            return arr.get(pointer);
        }
    }
}
