package mercari;

import java.util.Arrays;

public class Mer1 {
    private static int solve1(Integer[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        Arrays.sort(nums, (a, b)->b-a);
        int cur = 0;
        for(int i=0;i<nums.length;i++) {
            cur+=nums[i];
            if(2 * cur > sum)
                return cur;
        }
        return -1;
    }
}
