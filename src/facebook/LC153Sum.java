package facebook;

import java.util.*;

public class LC153Sum {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
    }
    static class Solution {
        List<List<Integer>> ans;

        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            ans = new ArrayList<>();

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
                twoSum(i, set, nums);
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
            return ans;
        }

        public void twoSum(int i, Set<Integer> seen, int[] nums) {
            seen.clear();
            for (int j = i + 1; j < nums.length; j++) {
                int want = 0 - nums[j] - nums[i];
                if (seen.contains(want)) {
                    ans.add(List.of(new Integer[]{nums[i], nums[j], want}));
                    while (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                        j++;
                    }
                }

                seen.add(nums[j]);
            }
        }
    }
}
