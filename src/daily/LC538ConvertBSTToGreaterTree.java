package daily;

public class LC538ConvertBSTToGreaterTree {
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

    /**
     * 最笨的办法，需要2次遍历，第一次遍历计算总和，第二次遍历计算每个值
     */
    class Solution {
        int sum;
        int used;

        public TreeNode convertBST(TreeNode root) {
            // idea： 先算一个sum
            // 在中序遍历 给每个数加 sum - used

            // 计算sum
            sum = 0;
            traverse(root);
            System.out.println(sum);

            // 给每个值增加used
            used = 0;
            TreeNode ans = root;
            changeVal(root);
            return ans;
        }

        private void changeVal(TreeNode node) {
            if (node.left != null) {
                changeVal(node.left);
            }
            int tmp = node.val;
            node.val = sum - used;
            used = tmp + used;
            if (node.right != null) {
                changeVal(node.right);
            }

        }

        private void traverse(TreeNode node) {
            if (node == null) {
                return;
            }
            sum += node.val;

            traverse(node.left);
            traverse(node.right);
        }
    }

    /**
     * 第二个方法：从右往左算即可，刚开始sum是0，那么最大的节点就是他本身，最小的节点就是所有的和
     */
    class Solution2 {
        private int sum = 0;
        public TreeNode convertBST(TreeNode root) {
            if (root != null) {
                convertBST(root.right);
                // 最大的node就是自己不变，这样会好理解一点
                sum += root.val;
                root.val = sum;
                convertBST(root.left);
            }
            return root;
        }

    }
}
