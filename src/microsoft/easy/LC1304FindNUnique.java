package microsoft.easy;

public class LC1304FindNUnique {
    class Solution {
        public int[] sumZero(int n) {
            int[] ans = new int[n];
            if(n % 2 == 0) {
                for (int i = 1; i < n; i+=2) {
                    ans[i - 1] = i;
                    ans[i] = -i;
                }
            } else {
                ans[0] = 0;
                for (int i = 2; i < n; i+=2) {
                    ans[i - 1] = i;
                    ans[i] = -i;
                }
            }
            return ans;
        }
    }
}
