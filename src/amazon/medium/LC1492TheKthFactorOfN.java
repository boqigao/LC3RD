package amazon.medium;

import com.sun.source.doctree.SeeTree;

import java.util.*;

public class LC1492TheKthFactorOfN {
    class Solution3 {
        public int kthFactor(int n, int k) {
            List<Integer> divisors = new ArrayList();
            int sqrtN = (int) Math.sqrt(n);
            for (int x = 1; x < sqrtN + 1; ++x) {
                if (n % x == 0) {
                    --k;
                    divisors.add(x);
                    if (k == 0) {
                        return x;
                    }
                }
            }

            // If n is a perfect square
            // we have to skip the duplicate
            // in the divisor list
            if (sqrtN * sqrtN == n) {
                ++k;
            }

            int nDiv = divisors.size();
            return (k <= nDiv) ? n / divisors.get(nDiv - k) : -1;
        }
    }
    class Solution2 {
        // max heap -> to keep max element always on top
        Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        // push into heap
        // by limiting size of heap to k
        public void heappushK(int x, int k) {
            heap.add(x);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        public int kthFactor(int n, int k) {
            int sqrtN = (int) Math.sqrt(n);
            for (int x = 1; x < sqrtN + 1; ++x) {
                if (n % x == 0) {
                    heappushK(x, k);
                    if (x != n / x) {
                        heappushK(n / x, k);
                    }
                }
            }

            return k == heap.size() ? heap.poll() : -1;
        }
    }
    class Solution {
        public int kthFactor(int n, int k) {
            Set<Integer> set = new LinkedHashSet<>();
            for (int i = 0; i <= n; i++) {
                if (n % i == 0) {
                    set.add(i);
                }
            }
            List<Integer> list = new ArrayList<>(set);
            if (list.size() < k) {
                return  -1;
            }
            return list.get(k - 1);
        }
    }
}
