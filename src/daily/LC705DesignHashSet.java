package daily;

public class LC705DesignHashSet {
    class MyHashSet {
        private Bucket[] bucketArray;
        private int keyRange;

        public MyHashSet() {
            this.keyRange = 769;
            this.bucketArray = new Bucket[this.keyRange];
            for (int i = 0; i < this.keyRange; i++) {
                this.bucketArray[i] = new Bucket();
            }
        }

        protected int _hash(int key) {
            return (key % this.keyRange);
        }

        public void add(int key) {
            int bucketIndex = this._hash(key);
            this.bucketArray[bucketIndex].insert(key);
        }

        public void remove(int key) {
            int bucketIndex = this._hash(key);
            this.bucketArray[bucketIndex].delete(key);
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int bucketIndex = this._hash(key);
            return this.bucketArray[bucketIndex].exists(key);
        }
    }

    class Bucket {
        private BST tree;

        public Bucket() {
            tree = new BST();
        }

        public void insert(Integer key) {
            this.tree.root = this.tree.insertNode(this.tree.root, key);
        }

        public void delete(Integer key) {
            this.tree.root = this.tree.deleteNode(this.tree.root, key);
        }

        public boolean exists(Integer key) {
            TreeNode node = this.tree.searchBST(this.tree.root, key);
            return (node != null);
        }
    }

    class BST {
        TreeNode root = null;

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null || root.val == val) {
                return root;
            }
            if (val > root.val) {
                return searchBST(root.right, val);
            } else {
                return searchBST(root.left, val);
            }
        }

        public TreeNode insertNode(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            if (val > root.val) {
                root.right = insertNode(root.right, val);
            } else if (root.val == val) {
                return root;
            } else {
                root.left = insertNode(root.left, val);
            }
            return root;
        }

        /*
         * One step right and then always left
         */
        public int successor(TreeNode root) {
            root = root.right;
            while (root.left != null)
                root = root.left;
            return root.val;
        }

        /*
         * One step left and then always right
         */
        public int predecessor(TreeNode root) {
            root = root.left;
            while (root.right != null)
                root = root.right;
            return root.val;
        }

        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }

            // delete from the right tree
            if (key > root.val) {
                root.right = deleteNode(root.right, key);
            } else if (key < root.val) {
                root.left = deleteNode(root.left, key);
            } else {
                // the node is a leaf
                if (root.left == null && root.right == null) {
                    root = null;
                    // the node has right a right child
                } else if (root.right != null) {
                    root.val = successor(root);
                    root.right = deleteNode(root.right, root.val);
                } else {
                    root.val = predecessor(root);
                    root.left = deleteNode(root.left, root.val);
                }
            }
            return root;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
