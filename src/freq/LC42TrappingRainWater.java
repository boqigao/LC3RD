package freq;

import java.util.Stack;

/**
 * keyword：单调栈, 双数组
 * monotonic stack
 */
public class LC42TrappingRainWater {
    public static void main(String[] args) {
        SolutionTwoPointers s = new SolutionTwoPointers();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(s.trap(height));

    }

    static class SolutionTwoPointers {
        public int trap(int[] height) {
            if (height == null || height.length <= 2) {
                return 0;
            }
            int left = 0;
            int right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            int ans = 0;
            while (left < right) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                }
                if (height[right] > rightMax) {
                    rightMax = height[right];
                }

                if (leftMax < rightMax) {
                    ans += leftMax - height[left];
                    left++;
                } else {
                    ans += rightMax - height[right];
                    right--;
                }
            }
            return ans;
        }
    }

    /**
     * 单调栈
     */
    static class SolutionStack {
        public int trap(int[] height) {
            if (height == null || height.length <= 2) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();

            int ans = 0;
            for (int i = 0; i < height.length; i++) {
                while (stack.size() != 0 && height[i] > height[stack.peek()]) {
                    int prev = height[stack.peek()];
                    while (stack.size() != 0 && height[stack.peek()] == prev) {
                        stack.pop();
                    }
                    if (stack.size() != 0) {
                        ans += (Math.min(height[i], height[stack.peek()]) - prev) * (i - stack.peek() - 1);
                    }
                }
                stack.push(i);
            }

            return ans;
        }
    }
}
