package daily;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC1209RemoveAllAdjacentDuplicatesInStringII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "";

        int k = 2;

        System.out.println(solution.removeDuplicates(s, k));
    }

    static class Solution {
        public String removeDuplicates(String s, int k) {
            StringBuilder sb = new StringBuilder();
            Stack<Integer> counts = new Stack<>();
            for (int i = 0; i < sb.length(); i++) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    counts.push(1);
                } else {
                    int incremented = counts.pop() + 1;

                    if (incremented == k) {
                        sb.delete(i - k + 1, i + 1);
                        i = i - k;
                    } else {
                        counts.push(incremented);
                    }
                }
            }
            return sb.toString();
        }
    }

    /**
     * 单纯的stack模拟，超时了 因为变成了O(n * k)吧。。
     * 我每次都pop出来，再push进去，这样明显不合理，其实只要简单的计数即可
     */
    static class SolutionTLE {
        public String removeDuplicates(String s, int k) {
            Stack<Character> stack = new Stack<>();
            Queue<Character> queue = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                int counter = k;
                if (stack.isEmpty()) {
                    stack.push(s.charAt(i));
                } else {
                    if (stack.peek() != s.charAt(i)) {
                        stack.push(s.charAt(i));
                    } else {
                        // top of stack same to current char

                        while (!stack.isEmpty() && s.charAt(i) == stack.peek() && counter >= 1) {
                            stack.pop();
                            counter--;

                        }

                        if (counter != 1) {
                            while (counter != k) {
                                stack.push(s.charAt(i));
                                counter++;
                            }
                            stack.push(s.charAt(i));
                        }

                    }
                }
            }
            StringBuilder sb = new StringBuilder();

            for (Character c : stack) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
}
