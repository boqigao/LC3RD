package amazon.medium;

public class LC926FlipStringToMonotoneIncreasing {
    public static void main(String[] args) {
        Solution2 s2 =new Solution2();
        System.out.println(s2.minFlipsMonoIncr("010110"));
    }

    static class Solution2 {
        public int minFlipsMonoIncr(String s) {

            if (s.length() <= 1) {
                return 0;
            }
            int[] dp = new int[s.length()];
            dp[0] = 0;
            int n = s.length();
            int oneCount = 0;
            if (s.charAt(0) == '1') {
                oneCount++;
            }
            for (int i = 1; i < n; i++) {
                if(s.charAt(i) == '1') {
                    // 已经是弄好了
                    dp[i] = dp[i - 1];
                    oneCount++;
                } else {
                    // 要么翻转这个0；
                    dp[i] = Math.min(dp[i - 1] + 1,
                            //，要么翻转全部的1
                            oneCount);
                }
            }
            return dp[n - 1];
        }
    }
    class Solution {
        public int minFlipsMonoIncr(String s) {
            if (s.length() <= 1) {
                return 0;
            }
            int dp = 0;
            int countOne = s.charAt(0) - '0';
            int n = s.length();
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == '0') {
                    int x = dp + 1;
                    dp = Math.min(x, countOne);
                } else {
                    countOne++;
                }
            }
            return dp;
        }
    }
}
