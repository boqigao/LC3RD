package amazon.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LC1151MiniumSwapsToGroupAll1Together {
    public static void main(String[] args) {
        Solution s= new Solution();
        System.out.println(s.minSwaps(new int[]{1,0,1,0,1,0,0,1,1,0,1}));
    }

    //Solution using deque
    static class Solution2 {
        public int minSwaps(int[] data) {
            int ones = Arrays.stream(data).sum();

            int cnt_ones = 0;
            int max_ones = 0;
            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < data.length; i++) {
                deque.addLast(data[i]);
                cnt_ones += data[i];
                if (deque.size() > ones) {
                    cnt_ones -= deque.removeFirst();
                }
                max_ones = Math.max(max_ones, cnt_ones);
            }

            return ones - max_ones;
        }
    }

    // two point approach
    // totalOnes - maxOnes in a subArray
    static class Solution {
        public int minSwaps(int[] data) {
            int totalOnes = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i] == 1) {
                    totalOnes++;
                }
            }

            int curOnesInWindow = 0;
            int maxOnesInWindow = 0;
            int right = 0, left = 0;
            while (right < data.length) {
                curOnesInWindow += data[right];
                right++;
                if (right - left > totalOnes) {
                    curOnesInWindow -= data[left];
                    left++;
                }
                maxOnesInWindow = Math.max(curOnesInWindow, maxOnesInWindow);
            }
            return totalOnes - maxOnesInWindow;
        }
    }
}
