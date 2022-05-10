package amazon.hard;

import java.util.*;

public class LC127WordLadder {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Queue<String> queue = new LinkedList<>();
            Set<String> words = new HashSet<>(wordList);
            words.remove(beginWord);
            queue.add(beginWord);

            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    if (cur.equals(endWord)) {
                        return level;
                    }
                    List<String> neighbors = neighbors(cur);
                    for (String nei : neighbors) {
                        if (words.contains(nei)) {
                            words.remove(nei);
                            queue.add(nei);
                        }
                    }
                }
            }
            return 0;
        }

        public List<String> neighbors(String string) {
            char[] chars = string.toCharArray();
            List<String> result = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                char tmp = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {

                    chars[i] = c;
                    String neighbor = new String(chars);
                    result.add(neighbor);
                }
                chars[i] = tmp;
            }
            return result;
        }

        class Pair {
            String word;
            Integer dist;

            public Pair(String word, Integer dist) {
                this.word = word;
                this.dist = dist;
            }
        }
    }
}
