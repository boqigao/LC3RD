package codility.traverse;

public class BinaryGap {
    class Solution {
        public int solution(int N) {
            // write your code in Java SE 8
            String binaryString = Integer.toBinaryString(N);
            int ans = 0;

            int left = -1, right;
            for (int i = 0; i < binaryString.length(); i++) {
                if (binaryString.charAt(i) == '1') {
                    if (left != -1) {
                        right = i;
                        ans = Math.max(ans, right - left - 1);
                    }
                    left = i;
                }
            }

            return ans;
        }
    }
}
