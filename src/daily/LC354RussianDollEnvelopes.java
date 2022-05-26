package daily;

import java.util.Arrays;
import java.util.Comparator;

public class LC354RussianDollEnvelopes {
    static class Solution {
        public static void main(String[] args) {
            int[] nums = new int[]{4,5,6,1,2,3,4};
            lengthOfLIS(nums);
        }

        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, new EnvelopeComparator());

            int[] secondDim = new int[envelopes.length];
            // 从大到小拍
            for (int i = 0; i < envelopes.length; i++) {
                secondDim[i] = envelopes[i][1];
            }
            return lengthOfLIS(secondDim);
        }

        // lis: longest increasing sequence
        public static int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                /**
                 * The index of the search key, if it is contained in the array;
                 *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
                 *         <i>insertion point</i> is defined as the point at which the
                 *         key would be inserted into the array: the index of the first
                 *         element greater than the key, or {@code a.length} if all
                 *         elements in the array are less than the specified key.
                 */
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = - (i+ 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }

    static class EnvelopeComparator implements Comparator<int[]> {

        // 从小到大排长，长相等的话从大到小排宽
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        }
    }
}
