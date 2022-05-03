package facebook;

import java.util.Stack;

public class LC394DecodingString {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.decodeString("2[abc]3[cd]ef"));
    }
    static class Solution {
        public String decodeString(String s) {
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ']') {
                    StringBuilder tmpString = new StringBuilder();
                    while (!stack.isEmpty() && stack.peek() != '[') {
                        tmpString.insert(0, stack.pop());
                    }
                    // pop the '['
                    stack.pop();

                    // find the int
                    StringBuilder intString = new StringBuilder();
                    while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                        intString.insert(0, stack.pop());
                    }

                    int count = Integer.parseInt(intString.toString());
                    for (int j = 0; j < count; j++) {
                        for (int k = 0; k < tmpString.length(); k++) {
                            stack.push(tmpString.charAt(k));
                        }
                    }

                } else {
                    stack.push(c);
                }
            }
            StringBuilder ans = sb;
            while (!stack.isEmpty()) {
                sb.insert(0, stack.pop());
            }
            return ans.toString();
        }

    }
}
