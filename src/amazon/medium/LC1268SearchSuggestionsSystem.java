package amazon.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create a Trie from the given products input.
 * Iterate each character of the searchWord adding it to the prefix to
 * search for.
 * After adding the current character to the prefix traverse the trie
 * pointer to the node representing prefix.
 * Now traverse the tree from curr pointer in a preorder fashion and record
 * whenever we encounter a complete word.
 * Limit the result to 3 and return dfs once reached this limit.
 * Add the words to the final result.
 */
public class LC1268SearchSuggestionsSystem {
    /**
     * A dictionary tree problem
     * Trie: 前缀树
     */
    class Solution {
        public List<List<String>> suggestedProducts(String[] products,
                                                    String searchWord) {
            Trie trie = new Trie();

            List<List<String>> ans = new ArrayList<>();
            for (String w : products)
                trie.insert(w);

            for (int i = 1; i <= searchWord.length(); i++) {
                List<String> curRes = trie.getWordsStartingWith(searchWord.substring(0, i));
                ans.add(curRes);
            }
            return ans;
        }
    }

    class Trie {
        class Node {
            boolean isWord = false;
            List<Node> children = Arrays.asList(new Node[26]);
        }

        Node root, curr;
        List<String> resultBuffer;

        /**
         * word最开始一般就是一个prefix，然后慢慢增加新的'c'
         * @param curr
         * @param word
         */
        void dfsWithPrefix(Node curr, String word) {
            if (resultBuffer.size() == 3) {
                return;
            }
            if (curr.isWord) {
                resultBuffer.add(word);
            }

            for (char c = 'a'; c <= 'z'; c++) {
                if (curr.children.get(c - 'a') != null) {
                    dfsWithPrefix(curr.children.get(c - 'a'), word + c);
                }
            }
        }

        Trie() {
            root = new Node();
        }

        void insert(String s) {
            // Points curr to the root of trie
            curr = root;

            for (char c : s.toCharArray()) {
                if (curr.children.get(c - 'a') == null) {
                    curr.children.set(c - 'a', new Node());
                }
                curr = curr.children.get(c - 'a');
            }

            // Mark this node as a completed word
            curr.isWord = true;
        }

        List<String> getWordsStartingWith(String prefix) {
            curr = root;
            resultBuffer = new ArrayList<>();

            for (char c : prefix.toCharArray()) {
                if (curr.children.get(c - 'a') == null) {
                    return resultBuffer;
                }
                curr = curr.children.get(c - 'a');
            }
            dfsWithPrefix(curr, prefix);
            return resultBuffer;
        }
    }

}
