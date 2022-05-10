package facebook;

import java.util.Stack;

public class LC921MinimumAddtoMakeParentesesValid {
    class Solution {
        public int minAddToMakeValid(String s) {
            Stack<Character> stack = new Stack<>();
            int r = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push('(');
                } else {
                    if (stack.size() != 0 && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        r++;
                    }
                }
            }
            return stack.size() + r;
        }
    }
}
