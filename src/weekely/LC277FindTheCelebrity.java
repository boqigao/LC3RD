package weekely;

public class LC277FindTheCelebrity {

    /**
     * 每当我们使用一次knows我们就知道，某一个人一定不是celebrity
     */
    public class SolutionLogicalDeduction {
        private int n;

        public int findCelebrity(int n) {
            this.n = n;

            int celebrityCandidate = 0;

            for (int i = 0; i < n; i++) {
                if (knows(celebrityCandidate, i)) {
                    celebrityCandidate = i;
                }
            }
            if (isCelebrity(celebrityCandidate)) {
                return celebrityCandidate;
            }
            return -1;
        }

        private boolean isCelebrity(int i) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (knows(i, j) || !knows(j, i)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 这算是暴力？ n^2的解法
     */
    public class Solution {
        public int findCelebrity(int n) {
            /**
             * in[i] how many people know i
             */
            int[] in = new int[n];

            /**
             * out[i] i know how many people
             */
            int[] out = new int[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (knows(i, j)) {
                        in[j]++;
                        out[i]++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                if (in[i] == n && out[i] == 1) {
                    return i;
                }
            }

            return -1;
        }
    }

    boolean knows(int a, int b) {
        return true;
    }
}
