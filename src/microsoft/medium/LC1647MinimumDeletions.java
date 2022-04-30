package microsoft.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC1647MinimumDeletions {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minDeletions("ceabaacb"));
    }

    static class Solution {
        public int minDeletions(String s) {
            int[] bucket = new int[26];
            char[] sChars = s.toCharArray();

            for (char c : sChars) {
                bucket[c - 'a']++;
            }

            int sumOld = Arrays.stream(bucket).sum();
            int newSum = 0;
            Set<Integer> seenFreq = new HashSet<>();

            for (int i : bucket) {
                if (i == 0) {
                    continue;
                }
                while (seenFreq.contains(i) && i != 0) {
                    i--;
                }
                if (i != 0) {
                    seenFreq.add(i);
                    newSum += i;
                }
            }

            return sumOld - newSum;
        }
    }
}
