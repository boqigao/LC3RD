package amazon.medium;

import java.util.*;

public class LC253MeetingRooms {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] time ={{1, 10}, {2, 7}, {3, 19}, {8, 12}, {10, 20}, {11, 30}};
        System.out.println(s.minMeetingRooms(time));;
    }
    static class Solution {
        public int minMeetingRooms(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            // end time of first meeting
            pq.offer(intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                // if start time > the min end time before
                if (intervals[i][0] >= pq.peek()) {
                    // the meeting before is over
                    pq.poll();
                }

                // add the new end time to pq
                pq.add(intervals[i][1]);
            }

            return pq.size();
        }
    }

    /**
     * 果然有点问题。。
     */
    class SolutionWrong {
        public int minMeetingRooms(int[][] intervals) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

            PriorityQueue<Integer> endTime = new PriorityQueue<>();
            pq.addAll(Arrays.asList(intervals));

            List<Integer> list = new ArrayList<>();
            int ans = 0;
            int minEndTime = Integer.MAX_VALUE;

            while (!pq.isEmpty()) {
                int[] time = pq.poll();
                int start = time[0];
                int end = time[1];
                if (endTime.size() == 0) {
                    ans++;

                } else if (start < endTime.peek()) {
                    ans++;

                } else if (start >= endTime.peek()) {
                    endTime.poll();
                    ans--;
                }
                list.add(ans);
                endTime.add(end);
                minEndTime = Math.min(minEndTime, end);
            }
            return list.stream().min(Comparator.comparingInt(a -> a)).get();
        }
    }
}
