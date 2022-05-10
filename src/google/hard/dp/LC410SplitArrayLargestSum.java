package google.hard.dp;

import java.util.Arrays;

/**
 * A dp or binary solution
 * dp solution is extremely difficult to understand
 */
public class LC410SplitArrayLargestSum {

    class SolutionBinarySearch {
        public int splitArray(int[] nums, int m) {
            int sum = Arrays.stream(nums).sum();
            int maxElement = Arrays.stream(nums).max().getAsInt();

            int left = maxElement;
            int right = sum;
            int minimumLargestSplitSum = 0;

            while (left <= right) {
                // a middle value
                int maxSumAllowed = left + (right - left) / 2;

                if (minimumSubarraysRequired(nums, maxSumAllowed) <= m) {
                    right = maxSumAllowed - 1;
                    minimumLargestSplitSum = maxSumAllowed;
                } else {
                    left = maxSumAllowed + 1;
                }
            }
            return minimumLargestSplitSum;

        }

        private int minimumSubarraysRequired(int[] nums, int maxSumAllowed) {
            int currentSum = 0;
            int splitsRequired = 0;

            for (int element : nums) {
                if (currentSum + element <= maxSumAllowed) {
                    currentSum += element;
                } else {
                    currentSum = element;
                    splitsRequired++;
                }
            }

            return splitsRequired + 1;
        }
    }
}
