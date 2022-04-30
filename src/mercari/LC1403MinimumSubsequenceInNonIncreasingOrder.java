package mercari;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1403MinimumSubsequenceInNonIncreasingOrder {
    class Solution {
        public List<Integer> minSubsequence(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            Arrays.sort(nums);
            int curSum = 0, curLeft = 0;
            List<Integer> res = new ArrayList<>();
            for (int i = nums.length - 1; i >= 0; i--) {
                int cur = nums[i];
                res.add(cur);
                curSum += cur;
                curLeft = sum - curSum;
                if (curSum > curLeft) {
                    return res;
                }
            }
            return res;
        }
    }
}
