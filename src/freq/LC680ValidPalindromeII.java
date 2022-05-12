package freq;

/**
 * key words: trick
 */
public class LC680ValidPalindromeII {
    class Solution {
        public boolean validPalindrome(String s) {
            if (s.length() == 1) {
                return true;
            }
            int i = 0;
            int j = s.length() - 1;

            while (s.charAt(i) == s.charAt(j) && i <= j) {
                i++;
                j--;
            }

            // currently, we should delete s.charAt(i) or s.charAt(j)
            // let's firstly try to delete s.charAt(i)
            int iMemo = i;
            int jMemo = j;
            boolean ans = true;
            i++;
            for (; i <= j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    ans = false;
                    break;
                }
            }

            if (ans)
                return true;

            ans = true;
            // let's try s.charAt(j);
            i = iMemo;
            j = jMemo;
            j--;
            for (; i <= j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    ans = false;
                    break;
                }
            }
            if (ans)
                return true;
            return false;
        }
    }
}
