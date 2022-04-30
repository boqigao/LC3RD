package amazon.medium;

import java.util.ArrayDeque;

/**
 * see LC907
 * 907calculate the minimum of a sub array, all we need to do is to calculate the maximum of these sub array and
 * ans = max - min
 */
public class LC2104SumOfSubArrayRanges {

    class Solution2 {

        public long subArrayRanges(int[] nums) {
            // calculate maximum
            int len = nums.length;
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            int[] left = new int[len];
            int[] right = new int[len];

            for (int i = 0; i < len; i++) {
                left[i] = i + 1;
                right[i] = len - i;
            }

            // find the first left element which larger than nums[i]
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
                stack.push(i);
            }

            stack.clear();
            // find the first right element which larger than nums[i]
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    right[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }

            long ans = 0;

            // cal the max of all subarray
            for (int i = 0; i < len; i++) {
                ans += (long) nums[i] * left[i] * right[i];
            }

            stack.clear();

            for (int i = 0; i < len; i++) {
                left[i] = i + 1;
                right[i] = len - i;
            }

            // find the first left element which smaller than nums[i
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
                stack.push(i);
            }

            stack.clear();
            // find the first right element which smaller than nums[i]
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    right[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }

            for (int i = 0; i < len; i++) {
                ans -= (long) nums[i] * left[i] * right[i];
            }

            return ans;

        }
    }

    /**
     * a stupid method
     */
    class Solution {
        public long subArrayRanges(int[] nums) {
            long res = 0;
            for (int i = 0; i < nums.length; i++) {
                int max = nums[i];
                int min = nums[i];
                for (int j = 0; j < nums.length; j++) {
                    max = Math.max(max, nums[j]);
                    min = Math.min(min, nums[j]);
                    res += max - min;
                }
            }
            return res;
        }
    }
}
