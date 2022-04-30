package microsoft.medium;

/**
 * case 1: node有右子树，那就是右子树的最左边的节点
 * case 2： node没有右子树， 那就是这个node的祖先A，但是这个node必须是A的左子树的第一个祖先
 */
public class LC285InorderSuccessorBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution2 {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            // node has right sub tree
            if (p.right != null) {
                TreeNode leftMost = p.right;
                while (leftMost.left != null) {
                    leftMost = leftMost.left;
                }
                return leftMost;
            } else {
                TreeNode successor = null;
                TreeNode ancestor = root;

                while (ancestor != p) {
                    if (p.val < ancestor.val) {
                        successor = ancestor;
                        ancestor = ancestor.left;
                    } else {
                        ancestor = ancestor.right;
                    }
                }
                return successor;
            }
        }
    }

    class Solution {
        private TreeNode prvious;
        private TreeNode inorderSuccessorNode;

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (p.right != null) {
                // first case
                TreeNode leftMost = p.right;

                while (leftMost.left != null) {
                    leftMost = leftMost.left;
                }

                this.inorderSuccessorNode = leftMost;
            } else {
                // second case

            }

            return this.inorderSuccessorNode;
        }

        private void inorderCase2 (TreeNode node, TreeNode p) {
            if (node == null) {
                return;
            }

            this.inorderCase2(node.left, p);

            // 查看 【previous】这个node是不是node的inorder predecessor
            // 目标节点是不是previous， inorderSuccessor是否存在
            if (this.prvious == p && this.inorderSuccessorNode == null) {
                this.inorderSuccessorNode = node;
                return;
            }

            this.prvious = node;

            this.inorderCase2(node.right, p);

        }
    }
}
