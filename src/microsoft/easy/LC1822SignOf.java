package microsoft.easy;

public class LC1822SignOf {
    class Solution {
        public int arraySign(int[] nums) {
            int counter = 0;
            for (int i : nums) {
                if(i == 0) {
                    return 0;
                } else if (i < 0) {
                    counter++;
                }
            }
            if (counter % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
