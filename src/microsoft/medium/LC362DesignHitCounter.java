package microsoft.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LC362DesignHitCounter {
    /**
     * 这里使用map是可以的，但是没有利用到timestamp是单调增这个特性，时间复杂度是一样的，但是空间复杂度变成了O（N）
     */
    class HitCounter {
        Map<Integer, Integer> map;

        public HitCounter() {
            map = new HashMap<>();
        }

        public void hit(int timestamp) {
            if (map.get(timestamp) == null) {
                map.put(timestamp, 1);
            } else {
                int val = map.get(timestamp);
                map.put(timestamp, val + 1);
            }
        }

        public int getHits(int timestamp) {
            int sum = 0;
            for (int i = timestamp - 300 + 1; i <= timestamp; i++) {
                if (map.containsKey(i)) {
                    sum += map.get(i);
                }
            }
            return sum;
        }
    }

    class HitCounter2 {
        private Queue<Integer> hits;

        public HitCounter2() {
            this.hits = new LinkedList<>();
        }

        public void hit(int timestamp) {
            this.hits.add(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while (!this.hits.isEmpty()) {
                int diff = timestamp - this.hits.peek();
                if (diff >= 300) this.hits.remove();
                else break;
            }
            return this.hits.size();
        }
    }
}
