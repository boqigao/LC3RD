package smartnews;

/**
 * key word: prefix sum
 * <p>
 * 这道题核心很简单！
 * nums[i] 的前缀和就是 nums[0] + nums[1] + ... nums[i]
 * 所以我们令
 * pi = prefix sum of nums[i]
 * pj = prefix sum of nums[j]
 * <p>
 * 那么nums[i] 到 nums[j] 的subArray如果和能被k整除，必有
 * (pj - pi) % k == 0
 * 即:
 * pj % k - pi % k == 0 -> pj% k = pj % k
 */
public class LC974SubarraySumsDivisiblebyK {

    class Solution {
        public int subarraysDivByK(int[] nums, int k) {
            // memos: index 是余数, val 是出现次数
            int[] memos = new int[k];

            memos[0] = 1;
            int sum = 0;
            int count = 0;

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                int curModule = sum % k;
                // no minus index
                if (curModule < 0) {
                    curModule += k;
                }

                count += memos[curModule];
                memos[curModule]++;
            }

            return count;

        }
    }
}
