package hacker;

import java.util.ArrayDeque;

public class KuoHao {
    public static void main(String[] args) {
        System.out.println(isBalanced("{}[]()"));
    }

    public static String isBalanced(String s) {
        // Write your code here
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                stack.push(s.charAt(i));
            } else {
                if (c == '}'){
                    if (stack.size() == 0 || stack.peek() != '{') {
                        return "NO";
                    } else {
                        stack.pop();
                    }
                } else if (c == ']') {
                    if (stack.size() == 0 || stack.peek() != '[') {
                        return "NO";
                    } else {
                        stack.pop();
                    }
                } else {
                    if (stack.size() == 0 || stack.peek() != '(') {
                        return "NO";
                    } else {
                        stack.pop();
                    }
                }
            }
        }
        if(stack.size() == 0) {
            return "YES";}
        return "NO";
    }
}
