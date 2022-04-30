package microsoft.medium;

import java.util.*;

public class LC151ReverseWords {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reverseWords("a good   example"));
    }
    static class Solution {
        public String reverseWords(String s) {
            List<String> wordList = Arrays.asList(s.split("\\s+"));
            Collections.reverse(wordList);
            return String.join(" ", wordList);
        }
    }

    static class Solution2 {
        public String reverseWords(String s) {
            List<String> wordList = Arrays.asList(s.split("\\s+"));
            Collections.reverse(wordList);
            return String.join(" ", wordList);
        }
    }
}
