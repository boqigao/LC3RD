package amazon.medium;

import java.util.Arrays;
import java.util.HashMap;

public class LC1679MaxNumberOfKSumPairs {

    /**
     * 这道题教会我们要活用 map.getOrDefault(key, 0) > 0这个用法来检查现在是否还有 val在里面
     */
    class SolutionLikeTwoSum {
        public int maxOperations(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                int current = nums[i];
                int complement = k - current;
                if (map.getOrDefault(complement, 0) > 0) {
                    // remove complement from the map
                    map.put(complement, map.get(complement) - 1);
                    count++;
                } else {
                    // add current element in the map
                    map.put(current, map.getOrDefault(current, 0) + 1);
                }
            }
            return count;
        }
    }

    /**
     * map 不是map [val, index] 而是map [val, frequency]
     */
    class SolutionHashMap {
        public int maxOperations(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }

            for (int i = 0; i < nums.length; i++) {
                int cur = nums[i];
                int need = k - cur;
                if (map.getOrDefault(cur, 0) > 0 && map.getOrDefault(need, 0) > 0) {
                    if ((cur == need) && map.get(cur) < 2) {
                        continue;
                    }

                    map.put(cur, map.get(cur) - 1);
                    map.put(cur, map.get(need) - 1);
                    count++;
                }
            }

            return count;
        }
    }

    class SolutionTwoPointers {
        /**
         * idea: two pointers?
         *
         * @param nums
         * @param k
         * @return
         */
        public int maxOperations(int[] nums, int k) {
            Arrays.sort(nums);
            int left = 0, right = nums.length - 1;
            int counter = 0;
            int j = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < k) {
                    left++;
                } else if (nums[left] + nums[right] > k) {
                    right--;
                } else {
                    left++;
                    right--;
                    counter++;
                }
            }
            return counter;
        }

        class Pair {
            int val;
            int index;

            public Pair(int val, int index) {
                this.val = val;
                this.index = index;
            }
        }
    }
}
