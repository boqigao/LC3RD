package daily;

public class LC905SortArrayByParity {
    class Solution {
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        public int[] sortArrayByParity(int[] nums) {
            int lastEven = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] % 2 == 0) {
                    swap(nums,i, lastEven);
                    lastEven++;
                }

            }
            return nums;
        }
    }

    class SolutionQuick {
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        public int[] sortArrayByParity(int[] nums) {
            int i = 0;
            int j = nums.length - 1;
            while (i < j) {
                if (nums[i] % 2 == 1 && nums[j] % 2 == 0) {
                    swap(nums, i, j);
                }
                if (nums[i] % 2 == 0) {
                    i++;
                }
                if (nums[j] == 1) {
                    j--;
                }
            }
            return nums;
        }
    }
}
