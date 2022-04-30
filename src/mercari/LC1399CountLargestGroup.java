package mercari;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LC1399CountLargestGroup {
    public static void main(String[] args) {
        Solution s= new Solution();
        s.countLargestGroup(13);
    }
    static class Solution {
        public int countLargestGroup(int n) {
            ArrayList<Integer> cnt = new ArrayList<>(Collections.nCopies(37, 0));
            for (int i = 1; i <= n; i++) {
                int sum = calcSumOfDigits(i);
                cnt.set(sum, cnt.get(sum) + 1);
            }

            System.out.println(Collections.max(cnt));
            return Collections.frequency(cnt, Collections.max(cnt));
        }

        private int calcSumOfDigits(int n) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n = n / 10;
            }
            return sum;
        }
    }
}
