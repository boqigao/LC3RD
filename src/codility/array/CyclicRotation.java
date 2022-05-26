package codility.array;

public class CyclicRotation {
    class Solution {
        public int[] solution(int[] A, int K) {
            // write your code in Java SE 8

            if (A.length == 0) {
                return A;
            }
            int len = A.length;
            int[] ans = new int[len];
            int numToShift = K % len;

            for (int i = 0; i < len; i++) {
                int newPlace = i + numToShift;
                if (newPlace >= len) {
                    newPlace -= len;
                }
                ans[newPlace] = A[i];
            }

            return ans;
        }
    }
}
