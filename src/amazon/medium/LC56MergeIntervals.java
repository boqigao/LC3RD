package amazon.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC56MergeIntervals {
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, new ArrayComparator());
            List<int[]> ans = new ArrayList<>();
            ans.add(new int[]{intervals[0][0], intervals[0][1]});

            for (int[] interval : intervals) {
                int start = interval[0];
                int end = interval[1];

                int[] last = ans.get(ans.size() - 1);
                if (start <= last[1]) {
                    // 如果当前数组的起始比屁股要小
                    if (end < last[1]) {
                        continue;
                    } else {
                        last[1] = end;
                    }
                } else {
                    // 如果当前数组的起始比屁股要大
                    ans.add(new int[] {start, end});
                }
            }

            int[][] ansArray = new int[ans.size()][2];
            for (int i = 0; i < ans.size(); i++) {
                ansArray[i][0] = ans.get(i)[0];
                ansArray[i][1] = ans.get(i)[1];
            }
            return ansArray;
        }
    }

    static class ArrayComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a1, int[] a2) {
            return a1[0] - a2[0];
        }
    }
}
