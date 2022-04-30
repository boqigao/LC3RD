package amazon.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class LC1249MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minRemoveToMakeValid("lee(t(c)o)de)"));
    }
    static class Solution {
        public String minRemoveToMakeValid(String s) {
            Stack<Integer> parStack = new Stack<>();
            StringBuilder sb = new StringBuilder(s);
            List<Integer> shouldDelete = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c != '(' && c != ')') {
                    continue;
                } else if (c == '(') {
                    parStack.push(i);
                } else {
                    if (parStack.size() > 0) {
                        parStack.pop();
                    } else {
                        shouldDelete.add(i);
                    }
                }
            }
            shouldDelete.addAll(parStack);
            Collections.sort(shouldDelete);

            for (int i = shouldDelete.size() - 1; i >=0; i--) {
                sb.deleteCharAt(shouldDelete.get(i));
            }
            return sb.toString();
        }
    }
}
