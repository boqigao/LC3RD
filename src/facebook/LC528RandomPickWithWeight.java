package facebook;

import java.util.Arrays;

/**
 *  keywords: prefixsum
 */
public class LC528RandomPickWithWeight {
    class Solution {

        int[] prefixSum;
        int sum;
        public Solution(int[] w) {
            this.prefixSum = new int[w.length];
            this.sum = 0;
            for (int i = 0; i < w.length; i++) {
                this.sum += w[i];
                this.prefixSum[i] = this.sum;
            }
        }

        public int pickIndex() {
            double target = this.sum * Math.random();
            for (int i = 0; i < this.prefixSum.length; i++) {
                if (target < this.prefixSum[i]) {
                    return i;
                }
            }
            return -1;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
}
