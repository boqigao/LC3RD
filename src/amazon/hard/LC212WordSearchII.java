package amazon.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC212WordSearchII {
    class Solution {
        char[][] _board = null;
        List<String> res = new ArrayList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public List<String> findWords(char[][] board, String[] words) {
            // construct the trie
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newChild = new TrieNode();
                        node.children.put(letter, newChild);
                        node = newChild;
                    }
                }
                //已经走到word的根部了
                node.word = word;
            }
            this._board = board;
            for (int row = 0; row < board.length; ++row) {
                for (int col = 0; col < board[row].length; ++col) {
                    if (root.children.containsKey(board[row][col])) {
                        backTracking(row, col, root);
                    }
                }
            }

            return this.res;

        }

        private void backTracking(int row, int col, TrieNode parent) {
            Character letter = this._board[row][col];
            TrieNode currNode = parent.children.get(letter);

            if (currNode.word != null) {
                this.res.add(currNode.word);
                currNode.word = null;
            }
            this._board[row][col] = '#';
            for (int i = 0; i < 4; i++) {
                int newRow = row + dirs[i][0];
                int newCol = col + dirs[i][1];

                if (newRow < 0 || newCol < 0 || newRow >= this._board.length || newCol >= this._board[0].length) {
                    continue;
                }
                if (currNode.children.containsKey(this._board[newRow][newCol])) {
                    backTracking(newRow, newCol, currNode);
                }
                backTracking(newRow, newCol, currNode);
            }
            this._board[row][col] = letter;

            if (currNode.children.isEmpty()) {
                parent.children.remove(letter);
            }

        }

        class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            String word = null;

            public TrieNode() {

            }
        }
    }
}
