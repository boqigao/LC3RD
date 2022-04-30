package microsoft.medium;

import java.util.Stack;

public class LC1653MinimumDeletions {
    class Solution {
        public int minimumDeletions(String s) {
            int l = s.length();
            // dp保存的是s的[0,i]的子串的需要删除的数值
            // dp[0]是0， dp[1]是0，dp[l]是到l为止的数值
            int[] dp = new int[l + 1];
            // bcount是[0, i]之间的b的数字
            int bcount = 0;

            for (int i = 0; i < l; i++) {
                if (s.charAt(i) == 'a') {
                    // 第一种情况，保存a，那么说明之前的串是aaaaa，因此需要删除所有的b：bcount
                    // 第二种情况，删除a，那么之前的串是aaaabbb，因此需要删除之前所有的a和这个a
                    dp[i + 1] = Math.min(dp[i] + 1, bcount);
                } else {
                    // 如果是b，所以总是合法的，所以直接继承
                    dp[i + 1] = dp[i];
                    bcount++;
                }
            }
            return dp[l];
        }
    }

    class Solution2 {
        public int minimumDeletions(String s) {
            int cnt = 0;
            Stack<Character> stk = new Stack<>();

            for (char c : s.toCharArray()) {
                if (!stk.isEmpty() && stk.peek() == 'b' && c == 'a') {
                    stk.pop();
                    cnt++;
                } else {
                    stk.push(c);
                }
            }
            return cnt;
        }
    }
}
