package daily;

/**
 * 这种情况不用太深入考虑边界问题
 */
public class LC59SpiralMatrix {
    class Solution {
        public int[][] generateMatrix(int n) {
            int[][] ans = new int[n][n];
            fillMatrix(ans, 0, 0, 1, 'r');
            return ans;
        }

        public void fillMatrix(int[][] ans, int i, int j, int cur, char dir) {
            // 越界了：结束
            if (i < 0 || j < 0 || i >= ans.length || j >= ans.length) return;

            // 填到了已经有的地方：结束
            if (ans[i][j] != 0) return;

            ans[i][j] = cur;

            // 这一步很重要，就是说如果是朝上的话就只能接着调这个函数
            if (dir == 'u') {
                fillMatrix(ans, i - 1, j, cur + 1, 'u');
            }
            fillMatrix(ans, i, j + 1, cur + 1, 'r');
            // 这里可行的原因是，当走到l的时候，d要么越界了，要么已经填了
            fillMatrix(ans, i + 1, j, cur + 1, 'd');
            fillMatrix(ans, i, j - 1, cur + 1, 'l');
            fillMatrix(ans, i - 1, j, cur + 1, 'u');
        }
    }

    class Solution2 {
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int col_start = 0, col_end = n - 1;
            int row_start = 0, row_end = n - 1;
            int element = 1;
            while(col_start <= col_end && row_start <= row_end) {
                //top
                for(int j = col_start; j <= col_end; j++) {
                    matrix[row_start][j] = element++;
                }

                // right side
                for(int i = row_start + 1; i <= row_end; i++) {
                    matrix[i][col_end] = element++;
                }

                //bottom side
                for(int j = col_end - 1; j >= col_start; j--) {
                    matrix[row_end][j] = element++;
                }

                //left side
                for(int i = row_end - 1; i > row_start; i--) {
                    matrix[i][col_start] = element++;
                }

                col_start++;
                col_end--;
                row_start++;
                row_end--;
            }
            return matrix;
        }
    }
}
