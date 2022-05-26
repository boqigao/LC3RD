package smartnews;

import java.util.HashMap;
import java.util.Map;

/**
 * key word: prefix sum
 */
public class LC523ContinuousSubArraySum {
    public static void main(String[] args) {
        SolutionWrong s = new SolutionWrong();
        System.out.println(s.checkSubarraySum(new int[]{1, 2, 3}, 5));
    }

    class SolutionRight {
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>() {{
                put(0, -1);
            }};
            ;
            int runningSum = 0;
            for (int i = 0; i < nums.length; i++) {
                runningSum += nums[i];
                if (k != 0)
                    runningSum %= k;
                Integer prev = map.get(runningSum);
                if (prev != null) {
                    if (i - prev > 1)
                        return true;
                } else
                    map.put(runningSum, i);
            }
            return false;
        }
    }

    static class SolutionWrong {
        public boolean checkSubarraySum(int[] nums, int k) {
            int[] memo = new int[k];
            memo[0] = -1;
            int res = 0;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                int curModule = sum % k;
                if (curModule < 0) {
                    curModule += k;
                }
                if (memo[curModule] != 0) {
                    if (i - memo[curModule] >= 1)
                        return true;
                } else {
                    // 这里错在i可能等于0
                    if (i == 0) {
                        memo[curModule] = -1;
                    } else {
                        memo[curModule] = i;
                    }
                }
            }
            return false;
        }
    }
}
