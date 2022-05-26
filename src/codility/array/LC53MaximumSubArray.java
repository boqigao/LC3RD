package codility.array;

public class LC53MaximumSubArray {
    class Solution {
        public int maxSubArray(int[] A) {
            int currentSubarray = A[0];
            int maxSubarray = A[0];

            for (int i = 1; i < A.length; i++) {
                int num = A[i];

                currentSubarray = Math.max(num, currentSubarray + num);
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
            return maxSubarray;
        }
    }
}
