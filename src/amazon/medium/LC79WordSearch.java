package amazon.medium;

public class LC79WordSearch {
    public static void main(String[] args) {
        char[][] chars = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String s = "ABCB";
        Solution solution = new Solution();
        System.out.println(solution.exist(chars, s));
    }

    static class Solution {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int row, col;

        public boolean exist(char[][] board, String word) {
            row = board.length;
            col = board[0].length;
            boolean ans = false;
            boolean[][] visited = new boolean[row][col];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    visited[i][j] = true;
                    ans = dfs(board, word, i, j);
                    if (ans) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int i, int j) {
            if (!isValid(i, j) || (word.length() >= 1 && word.charAt(0) != board[i][j])) {
                return false;
            }
            if (word.length() == 1 && word.charAt(0) == board[i][j]) {
                return true;
            }
            char c = board[i][j];
            board[i][j] = '#';
            int len = word.length();
            boolean ans;
            for (int[] dir : dirs) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                ans = dfs(board, word.substring(1, len), newI, newJ);
                if (ans) {
                    return true;
                }
            }
            board[i][j] = c;
            return false;
        }

        private boolean isValid(int i, int j) {
            return i >= 0 && j >= 0 && i < row && j < col;
        }
    }
}
