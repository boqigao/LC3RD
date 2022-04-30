package microsoft.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC1405LongHappyString {
    public static void main(String[] args) {
        LC1405LongHappyString ls = new LC1405LongHappyString();


    }
    class Solution {
        public String longestDiverseString(int a, int b, int c) {
            StringBuilder ans = new StringBuilder();
            PriorityQueue<Pair> pq = new PriorityQueue<>(new PairComparator());

            if (a != 0) {
                pq.offer(new Pair(a, 'a'));
            }
            if (b != 0) {
                pq.offer(new Pair(b, 'b'));
            }
            if (c != 0) {
                pq.offer(new Pair(c, 'c'));
            }

            while (!pq.isEmpty()) {
                Pair curPair = pq.poll();
                if (ans.length() > 1 && ans.charAt(ans.length() - 1) == curPair.y && ans.charAt(ans.length() - 2) == curPair.y) {
                    // 形如下一个最大的是a, 但是现在的string已经是 bcaa 这样
                    if (pq.isEmpty()) {
                        // 已经空了，说明全是a了，那没结果了
                        break;
                    } else {
                        Pair nextPair = pq.poll();
                        ans.append(nextPair.y);
                        nextPair.x--;
                        if (nextPair.x != 0) {
                            pq.offer(nextPair);
                        }
                    }
                } else {
                    // 可以直接放一个a
                    ans.append(curPair.y);
                    curPair.x--;
                }
                // 只有大于0的时候才会放回去
                if (curPair.x != 0) {
                    pq.offer(curPair);
                }
            }
            return ans.toString();
        }
    }

    class Pair {
        int x;
        char y;
        Pair(int x, char y) {
            this.x = x;
            this.y = y;
        }
    }

    // 大顶堆，大的放前面
    class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair p1, Pair p2) {
            return p2.x - p1.x;
        }
    }
}
