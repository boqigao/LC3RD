package amazon.hard;


import java.util.*;

public class LC472ConcatenateWords {
    class Solution {
        public List<String> findAllConcatenatedWordsInADict(String[] words) {

            Set<String> seenWords = new HashSet<>();
            Arrays.sort(words, new LengthComparator());
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if (canForm(words[i], seenWords)) {
                    ans.add(words[i]);
                }
                seenWords.add(words[i]);
            }
            return ans;
        }

        private boolean canForm(String s, Set<String> seenWords) {
            if (seenWords.isEmpty()) {
                return false;
            }
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && seenWords.contains(s.substring(j, i))) {
                        dp[i] = true;
                    }
                }
            }

            return dp[s.length()];
        }


        class LengthComparator implements Comparator<String> {

            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }

    }

}
