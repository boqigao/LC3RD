package amazon.medium;

import java.util.Arrays;
import java.util.Stack;

public class LC456132Pattern {
    class SolutionBinarySearch {
        public boolean find132pattern(int[] nums) {
            if (nums.length < 3)
                return false;
            int[] min = new int[nums.length];
            min[0] = nums[0];
            for (int i = 1; i < nums.length; i++)
                min[i] = Math.min(min[i - 1], nums[i]);
            for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
                if (nums[j] > min[j]) {
                    k = Arrays.binarySearch(nums, k, nums.length, min[j] + 1);
                    if (k < 0)
                        k = -1 - k;
                    if (k < nums.length && nums[k] < nums[j])
                        return true;
                    nums[--k] = nums[j];
                }
            }
            return false;
        }
    }

    class SolutionStack {
        public boolean find132pattern(int[] nums) {
            int len = nums.length;
            int[] min = new int[len];

            min[0] = nums[0];
            for (int i = 1; i < len; i++) {
                min[i] = Math.min(nums[i], min[i - 1]);
            }

            Stack<Integer> stack = new Stack<>();

            for (int j = len - 1; j >= 0; j--) {
                // 这个if确保了j大于i
                if (nums[j] > min[j]) {
                    // 这个确保了栈顶元素大于i
                    while (!stack.isEmpty() && stack.peek() <= min[j]) {
                        stack.pop();
                    }
                    // 这个确保了栈顶元素小于j
                    if (!stack.isEmpty() && stack.peek() < nums[j]) {
                        return true;
                    }
                    stack.push(nums[j]);
                }
            }

            return false;
        }
    }
}
