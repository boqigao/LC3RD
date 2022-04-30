package microsoft.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC545BoundaryOfBinaryTree {
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // 先收集左边界
        TreeNode node = root.left;
        while (node != null) {
            if (!isLeaf(node)) {
                res.add(node.val);
            }
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }


        // 添加所有的儿子（通常意义上的底边）, 注意我们并没有坐过类似于 root = root.left
        addLeaves(res, root);

        // 添加右边界，因为为了不加上儿子，所以用一个stack
        node = root.right;

        Stack<Integer> s = new Stack<>();
        node = root.right;
        while (node != null) {
            if (!isLeaf(node)) {
                s.push(node.val);
            }
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        while (!s.isEmpty()) {
            res.add(s.pop());
        }
        return res;
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
