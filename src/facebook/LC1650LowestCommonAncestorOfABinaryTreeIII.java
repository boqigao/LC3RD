package facebook;

import java.util.HashSet;
import java.util.Set;

public class LC1650LowestCommonAncestorOfABinaryTreeIII {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    class Solution {
        public Node lowestCommonAncestor(Node p, Node q) {
            Set<Node> memo = new HashSet<>();
            while (p!=null) {
                memo.add(p);
                p = p.parent;
            }

            while (q != null) {
                if (memo.contains(q)) {
                    return q;
                }
                q = q.parent;
            }
            return q;
        }
    }
}
