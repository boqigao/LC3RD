package amazon.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC696CountBinaryStrings {
    public static void main(String[] args) {
        Solution s = new Solution();
        {
            System.out.println(s.countBinarySubstrings("00110011"));
        }
    }

    class Solution2 {
        public int countBinarySubstrings(String s) {

            int count = 0;
            int consecutiveCount = 1;
            int lastConsecutiveCount = 0;

            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != s.charAt(i - 1)) {
                    lastConsecutiveCount = consecutiveCount;
                    consecutiveCount = 1;
                    count++;
                } else {
                    consecutiveCount++;
                    if (consecutiveCount <= lastConsecutiveCount)
                        count++;
                }
            }

            return count;

        }
    }

    static class Solution {
        public int countBinarySubstrings(String s) {
            Set<String> set = new HashSet<>();

            for (int i = 0; i < s.length(); i++) {
                char i_c = s.charAt(i);
                if (i < s.length() - 1) {
                    for (int j = i + 1; j < s.length(); j++) {
                        char j_c = s.charAt(j);
                        set.add(s.substring(i, j));
                        if (i_c != j_c && j < s.length() - 1 && j_c != s.charAt(j + 1)) {
                            i = j;
                            break;
                        }
                    }
                }
            }
            System.out.println(Arrays.toString(set.toArray()));
            return set.size();
        }
    }
}
