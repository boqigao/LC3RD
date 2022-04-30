package line;

import java.util.Arrays;

/**
 * After sorted array, considering three last elements: (A[n-1], A[n-2],
 * A[n-3]), if A[n-2] + A[n-3] is not > A[n - 1] then A[n-1] will never be
 * in any three tuple that is legal triangle. So, we will remove it from our
 * array. And to correspond with A[n - 2]...
 */
public class LC976LargestPerimeterTrangle {
    class Solution {
        public int largestPerimeter(int[] nums) {
            Arrays.sort(nums);
            for (int i = nums.length - 1; i >= 2; i--) {
                if (nums[i] < nums[i - 1] + nums[i - 2]) {
                    return nums[i] + nums[i - 1] + nums[i - 2];
                }
            }
            return 0;
        }
    }
}
