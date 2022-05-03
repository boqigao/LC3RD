package facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC227BasicCalculatorII {
    class Solution {
        int calculate(String s) {
            Stack<Integer> stack = new Stack<>();

            if (s == null || s.isEmpty())
                return 0;
            int len = s.length();
            int currentNum = 0;
            char operation = '+';
            for (int i = 0; i < len; i++) {
                char currentChar = s.charAt(i);
                if (Character.isDigit(currentChar)) {
                    currentNum = currentNum * 10 + currentChar - '0';
                }
                if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                    if (operation == '-') {
                        stack.push(-currentNum);
                    } else if (operation == '+') {
                        stack.push(currentNum);
                    } else if (operation == '*') {
                        stack.push(stack.pop() * currentNum);
                    } else if (operation =='/') {
                        stack.push(stack.pop() / currentNum);
                    }
                    operation = currentChar;
                    currentNum = 0;
                }
            }
            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }
    }

    /**
     * 做的太复杂了
     */
    class SolutionWrong {
        public int calculate(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '*' || c == '/') {
                    StringBuilder intSb1 = new StringBuilder();
                    while (!(stack.peek() == '+' || stack.peek() == '-')) {
                        intSb1.insert(0, stack.pop());
                    }
                    StringBuilder intSb2 = new StringBuilder();
                    // move to next
                    i++;
                    while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                        intSb2.append(s.charAt(i));
                        i++;
                    }
                    if (c == '*') {
                        String tmpRes = Long.parseLong(intSb1.toString()) * Long.parseLong(intSb2.toString()) + "";
                        for (int j = 0; j < tmpRes.length(); j++) {
                            stack.push(tmpRes.charAt(i));
                        }
                    } else {
                        String tmpRes = Long.parseLong(intSb1.toString()) / Long.parseLong(intSb2.toString()) + "";
                        for (int j = 0; j < tmpRes.length(); j++) {
                            stack.push(tmpRes.charAt(i));
                        }
                    }
                } else {
                    stack.push(c);
                }
            }
            List<Character> list = new ArrayList<>(stack);
            long cur = 0;
            for (int i = 0; i < list.size(); i++) {
                StringBuilder intSb1 = new StringBuilder();
                while (list.get(i + 1) != '+' & list.get(i + 1) != '-') {
                    intSb1.append(i);
                    i++;
                }
                int sb1 = Integer.parseInt(intSb1.toString());
                i++;
                if (list.get(i) == '+') {
                    StringBuilder intSb2 = new StringBuilder();
                    while (list.get(i + 1) != '+' & list.get(i + 1) != '-') {
                        intSb1.append(i);
                        i++;
                    }
                    cur = sb1 + Integer.parseInt(intSb2.toString());
                } else {

                }
            }

            return (int) cur;
        }
    }
}
