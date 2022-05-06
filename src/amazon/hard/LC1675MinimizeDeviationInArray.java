package amazon.hard;

import java.util.Collections;
import java.util.PriorityQueue;

public class LC1675MinimizeDeviationInArray {
    // 有很多种做法，做会一种pq应该可以了

    class Solution {
        public int minimumDeviation(int[] nums) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int minimum = Integer.MAX_VALUE;
            for (int num : nums) {
                if (num % 2 == 0) {
                    pq.offer(num);
                    minimum = Math.min(minimum, num);
                } else {
                    pq.offer(num * 2);
                    minimum = Math.min(minimum, num * 2);
                }
            }
            int minDeviation = Integer.MAX_VALUE;
            while (!pq.isEmpty()) {
                int cur = pq.poll();
                minDeviation = Math.min(minDeviation, cur - minimum);
                if (cur % 2 == 0) {
                    pq.offer(cur / 2);
                    minimum = Math.min(minimum, cur / 2);
                } else {
                    // if the max is odd, break
                    break;
                }
            }
            return minDeviation;
        }
    }
}
