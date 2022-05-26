package daily;

import java.util.LinkedList;
import java.util.Queue;

/**
 * key words: bfs
 */
public class LC117PopulationNextRightPointersInEachNodeII {

    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            Queue<Node> queue = new LinkedList<>();

            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node cur = queue.poll();
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null){
                        queue.offer(cur.right);
                    }

                    if (i == size - 1) {
                        cur.next = null;
                    } else {
                        cur.next = queue.peek();
                    }
                }
            }
            return root;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;
}
