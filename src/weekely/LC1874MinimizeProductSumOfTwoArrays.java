package weekely;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC1874MinimizeProductSumOfTwoArrays {
    /**
     * not a good idea because nums2 is also sorted
     */
    class Solution {
        public int minProductSum(int[] nums1, int[] nums2) {
            // Sort nums1 and nums2 in ascending order.
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            // Initialize sum as 0.
            int ans = 0;
            int n = nums2.length;

            // Iterate over two sorted arrays and calculate the
            // cumulative product sum.
            for (int i = 0; i < n; ++i) {
                // Since we also sort nums2 in ascending order,
                // we need to iterate over nums2 in reverse order.
                ans += nums1[i] * nums2[n - 1 - i];
            }

            return ans;
        }
    }

    class Solution2 {
        public int minProductSum(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            PriorityQueue<Integer> pq =
                    new PriorityQueue<>(Comparator.reverseOrder());
            for (int num : nums2) {
                pq.offer(num);
            }
            int ans = 0;
            for (int i = 0; i < nums1.length; i++) {
                ans += nums1[i] * pq.poll();
            }
            return ans;

        }
    }
}
