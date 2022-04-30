package microsoft.medium;

import java.util.Arrays;

public class LC462MinimumMovesToEqualArrayElements {
    public static void main(String[] args) {
        Solution s= new Solution();
        System.out.println(s.minMoves2(new int[]{1,10,2,9}));
    }
    static class Solution {
        public int minMoves2Wrong(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int sum = Arrays.stream(nums).sum();
            int avg =(int) Math.floor((double) sum / nums.length);
            int ans = 0;
            for (int num : nums) {
                ans += Math.abs(num - avg);
            }
            return ans;
        }

        // 应该用中位数
        public int minMoves2(int[] nums) {
            Arrays.sort(nums);
            int sum = 0;
            for (int num : nums) {
                sum += Math.abs(nums[nums.length / 2] - num);
            }
            return sum;
        }
    }
}
