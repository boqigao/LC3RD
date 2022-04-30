package microsoft.medium;

import java.util.ArrayList;
import java.util.List;

public class LC54SpiralMatrixOrder {
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix[0].length == 0) {
                return new ArrayList<>();
            }
            int r_max = matrix.length - 1;
            int c_max = matrix[0].length - 1;
            int r_min = 0;
            int c_min = 0;

            List<Integer> list = new ArrayList<>();
            while (r_min <= r_max && c_min <= c_max) {

                for (int i = c_min; i <= c_max; i++) {
                    list.add(matrix[r_min][i]);
                }

                for (int i = r_min + 1; i <= r_max; i++) {
                    list.add(matrix[i][c_max]);
                }

                if (r_min != r_max) {
                    for (int i = c_max - 1; i >= c_min; i--) {
                        list.add(matrix[r_max][i]);
                    }
                }

                if (c_min != c_max) {
                    for (int i = r_max - 1; i > r_min; i--) {
                        list.add(matrix[i][c_min]);
                    }
                }

                r_min++;
                c_min++;
                r_max--;
                c_max--;
            }
            return list;
        }
    }
}
