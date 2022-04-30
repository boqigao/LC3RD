package amazon.medium;

import java.util.HashMap;

/**
 * 这道题还是妙用哈希表,参考two sum
 */
public class LC560SubarraySumEqualsK {
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int count = 0, sum = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            // map的意思是这个sum出现了几次
            map.put(0, 1);

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                /**
                 * sum - k 的意思是，现在所有的和 sum， 目标 是 k 剩下的值就是 diff
                 * 所以 k + diff = sum
                 * k = sum - diff， 所以有几个diff(即sum- k，)就有几个k
                 */
                if (map.containsKey(sum - k)) {
                    count += map.get(sum - k);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }
}
