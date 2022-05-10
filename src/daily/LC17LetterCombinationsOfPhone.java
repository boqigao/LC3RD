package daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17LetterCombinationsOfPhone {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.letterCombinations("23245"));
    }

    static class Solution {
        private List<String> ans;
        private Map<Character, char[]> map;

        public List<String> letterCombinations(String digits) {

            if (digits.length() == 0) {
                return null;
            }

            map = new HashMap<>();
            map.put('2', new char[]{'a', 'b', 'c'});
            map.put('3', new char[]{'d', 'e', 'f'});
            map.put('4', new char[]{'g', 'h', 'i'});
            map.put('5', new char[]{'j', 'k', 'l'});
            map.put('6', new char[]{'m', 'n', 'o'});
            map.put('7', new char[]{'p', 'q', 'r', 's'});
            map.put('8', new char[]{'t', 'u', 'v'});
            map.put('9', new char[]{'w', 'x', 'y', 'z'});

            char[] input = digits.toCharArray();
            ans = new ArrayList<>();
            ans.add("");
            for (int i = 0; i < input.length; i++) {
                appendChar(input[i]);
            }
            ans.remove("");
            return ans;
        }

        private void appendChar(char index) {
            List<String> tmp = new ArrayList<>();
            char[] next = map.get(index);

            for (int i = 0; i < ans.size(); i++) {
                for (int j = 0; j < next.length; j++) {
                    tmp.add(ans.get(i) + next[j]);
                }
            }
            ans = tmp;
        }
    }
}
