package daily;

import java.util.Arrays;
import java.util.Stack;

public class LC581ShortestUnsortedContinuousSubarray {

    public static void main(String[] args) {
        int[] test = {2, 6, 4, 8, 10, 9, 15};
        Solution s = new Solution();
        System.out.println(s.findUnsortedSubarray(test));
    }

    /**
     * a simple sort solution
     */
    class SolutionSort {
        public int findUnsortedSubarray(int[] nums) {
            int[] sortedNum = nums.clone();
            Arrays.sort(sortedNum);
            int start = sortedNum.length, end = 0;
            for (int i = 0; i < sortedNum.length; i++) {
                if (nums[i] != sortedNum[i]) {
                    start = Math.min(start, i);
                    end = Math.max(end, i);
                }
            }
            return (end - start >= 0 ? end - start + 1 : 0);
        }
    }

    // 一瞬间看上去感觉是个binary search问题，或者是一个单调栈问题，感觉用栈能解决？

    /**
     * 的确是一个单调栈问题
     */
    static class Solution {
        public int findUnsortedSubarray(int[] nums) {
            Stack<Integer> stack = new Stack<Integer>();
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                    l = Math.min(l, stack.pop());
                stack.push(i);
            }
            stack.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                    r = Math.max(r, stack.pop());
                stack.push(i);
            }
            return r - l > 0 ? r - l + 1 : 0;
        }
    }
}
