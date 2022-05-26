package daily;

/**
 * keyword: dp problem
 */
public class LC647PalindromicSubstrings {

    class SolutionFindingFromCenter {
        public int countSubstrings(String s) {
            int ans = 0;

            for (int i = 0; i < s.length(); ++i) {
                // odd-length palindromes, single character center
                ans += countPalindromesAroundCenter(s, i, i);

                // even-length palindromes, consecutive characters center
                ans += countPalindromesAroundCenter(s, i, i + 1);
            }

            return ans;
        }

        private int countPalindromesAroundCenter(String ss, int lo, int hi) {
            int ans = 0;

            while (lo >= 0 && hi < ss.length()) {
                if (ss.charAt(lo) != ss.charAt(hi))
                    break;      // the first and last characters don't match!

                // expand around the center
                lo--;
                hi++;

                ans++;
            }

            return ans;
        }
    }

    /**
     * dp[i][j]表示从i到j的这段是不是回文字符串
     */
    class Solution {
        public int countSubstrings(String s) {
            int n = s.length();
            int ans = 0;

            if (n <= 0) {
                return 0;
            }

            boolean[][] dp = new boolean[n][n];


            // base case : 单个字符
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
                ans++;
            }

            // base case : 多个字符
            for (int i = 0; i < n - 1; i++) {
                dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
                ans += (dp[i][i + 1] ? 1 : 0);
            }


            // 从短到长，1,2已经都计算过了
            for (int len = 3; len <= n; len++) {
                for (int i = 0, j = i + len - 1; j < n; i++, j++) {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                    ans += (dp[i][j] ? 1 : 0);
                }
            }
            return ans;
        }
    }
}
