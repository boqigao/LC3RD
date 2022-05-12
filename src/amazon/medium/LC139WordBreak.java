package amazon.medium;

import java.util.*;

/**
 * keywords: dp and bfs
 */
public class LC139WordBreak {
    /**
     * dp
     *
     * dp[i]代表的是从 0 ~ i为止的数字是否能被字典化
     */
    class Solution2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> set = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            // 啥也没有的时候是一定可以被字典化的
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    /**
                     * dp[j] : 到j为止的的字符串是否能字典化
                     * 并且 sub(i,j)可以被字典化
                     */
                    if (dp[j] && set.contains(s.substring(j, i))) {
                        dp[i] = true;
                    }
                }
            }
            return dp[s.length()];
        }
    }

    /**
     * Bfs
     * leetcode [leet code]
     * 5 入队， 8 入队，结束
     */
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {

            // queue to store existing words
            Queue<Integer> queue = new LinkedList<>();
            Set<String> set = new HashSet<>(wordDict);
            int len = s.length();
            boolean[] visited = new boolean[len];
            queue.offer(0);

            while (!queue.isEmpty()) {
                int start = queue.poll();

                if (visited[start]) {
                    continue;
                }
                for (int end = start + 1; end <= len; end++) {
                    if (set.contains(s.substring(start, end))) {
                        queue.offer(end);
                        if (end == len) {
                            return true;
                        }
                    }
                }
                visited[start] = true;
            }
            return false;
        }
    }
}
