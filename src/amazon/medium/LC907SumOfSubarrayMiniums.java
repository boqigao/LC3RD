package amazon.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 单调栈
 */
public class LC907SumOfSubarrayMiniums {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.sumSubarrayMins(new int[]{2, 9, 7, 8, 3, 4, 6, 1}));
        Solution2 s2 = new Solution2();
        System.out.println(s2.sumSubarrayMins(new int[]{2, 9, 7, 8, 3, 4, 6, 1}));

    }

    static class Solution2 {
        public int sumSubarrayMins(int[] nums) {
            // left[i]用来存储nums[i]时候的左边最小，right[i]用来存储nums[i]这个数字的右边最小
            int len = nums.length;
            int[] left = new int[len];
            int[] right = new int[len];
            for (int i = 0; i < len; i++) {
                left[i] = i + 1;
                right[i] = len - i;
            }

            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    stack.pop();
                }
                // 现在stack里面所有的数字都小于当前的nums[i]，所以第一个左边最小就是stack.peek, 那么距离就是 i- stack.peek
                left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
                stack.push(i);
            }
            stack.clear();

            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    // 现在nums[i]小于stack顶端的数字，而且是第一个小于的数字，所以stack顶端的数字的右边最小就是当前的i，那么距离就是i - 顶端
                    right[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }

            long ans = 0;
            System.out.println("S2: left: " + Arrays.toString(left));
            System.out.println("S2: right: " + Arrays.toString(right));
            System.out.println("----------------------------");
            long mod = (int) 1e9 + 7;
            for (int i = 0; i < len; i++) {
                ans = (ans + (long) nums[i] * left[i] * right[i]) % mod;
            }
            return (int) ans;
        }
    }

    static class Solution {
        public int sumSubarrayMins(int[] A) {
            int len = A.length;
            Stack<Integer> stack = new Stack<>();
            int[] left = new int[len];
            int[] right = new int[len];
            for (int i = 0; i < A.length; i++) {
                left[i] = i + 1;
                right[i] = len - i;
            }
            // previous less element
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
                stack.push(i);
            }
            //next less element
            stack = new Stack<>();
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                    right[stack.peek()] = i - stack.peek();//8的右边最小是3，所以距离为1
                    stack.pop();
                }
                stack.push(i);
            }
            System.out.println("S1: left: " + Arrays.toString(left));
            System.out.println("S1: right: " + Arrays.toString(right));
            System.out.println("----------------------------");
            long ans = 0;
            long mod = (int) 1e9 + 7;
            for (int i = 0; i < len; i++) {
                ans = (ans + (long) A[i] * left[i] * right[i]) % mod;
            }
            return (int) ans;
        }
    }
}
