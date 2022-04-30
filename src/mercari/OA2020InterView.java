package mercari;

import java.util.*;

public class OA2020InterView {
    public static void main(String[] args) {
        Integer[] nums4 = {3,4,6,6,3};
        System.out.println(solve4(nums4));
    }
    private static int solve4(Integer[] nums) {
        Queue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());

        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            maxHeap.offer(n);
            min = Math.min(n, min);
        }
        int res = 0;
        while (min != maxHeap.peek()) {
            // 当前最大值
            int first = maxHeap.poll();
            min = first;
            List<Integer> lst = new ArrayList<>();
            while (!maxHeap.isEmpty()) {
                int next = maxHeap.poll() + 1;
                lst.add(next);
                min = Math.min(min, next);
            }
            maxHeap.offer(first);
            maxHeap.addAll(lst);
            res++;
        }
        return res;
    }

    static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
