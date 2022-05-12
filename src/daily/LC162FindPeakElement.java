package daily;

public class LC162FindPeakElement {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findPeakElement(new int[]{1, 2, 3, 1}));
    }

    static class Solution {
        public int findPeakElement(int[] nums) {
            int l = 0;
            int r = nums.length - 1;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums[mid + 1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return r;
        }
    }
}
