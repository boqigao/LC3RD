package line;

/**
 * 找出3个最小值，必然有两个不接，记录其index
 */
public class MinTwoSumOfNoneAdjacentFactor {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minSum(new int[]{5, 2, 4, 6, 3, 7}));
    }

    static class Solution {
        public int minSum(int[] nums) {
            // let i <= j <= k
            int len = nums.length;
            int i = Integer.MAX_VALUE, j = Integer.MAX_VALUE, k =
                    Integer.MAX_VALUE;
            int index_i = -1, index_j = -1, index_k = -1;

            for (int p = 0; p < len; p++) {
                int cur = nums[p];

                if (i > cur) {
                    k = j;
                    index_k = index_j;
                    j = i;
                    index_j = index_i;
                    i = cur;
                    index_i = p;
                } else if (j > cur) {
                    k = j;
                    index_k = index_j;
                    j = cur;
                    index_j = p;
                } else if (k > cur) {
                    k = cur;
                    index_k = p;
                }
            }

            if (index_i != index_j + 1 && index_i != index_j - 1) {
                return i + j;
            }

            if (index_i != index_k + 1 && index_i != index_k - 1) {
                return i + k;
            }

            return j + k;

        }
    }
}
