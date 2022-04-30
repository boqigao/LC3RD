package microsoft.medium;

import java.util.HashMap;
import java.util.Map;

public class LC1386CinemaSeatAllocation {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] k = new int[]{1, 6};
        s.maxNumberOfFamilies(2, new int[][]{new int[]{1, 6}, new int[]{1, 8}, new int[]{1, 3}, new int[]{2, 3}, new int[]{1, 10}
                , new int[]{1, 2}, new int[]{1, 5}, new int[]{2, 2}, new int[]{2, 4}, new int[]{2, 10}, new int[]{1, 7}, new int[]{2, 5}});
    }

    static class Solution {
        public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
            Map<Integer, Boolean[]> map = new HashMap<>();
            for (int[] seat : reservedSeats) {
                int p = seat[0];
                int q = seat[1];

                if (q == 1 || q == 10) {
                    continue;
                }
                if (!map.containsKey(p)) {
                    Boolean[] arr = new Boolean[3];
                    arr[0] = true;
                    arr[1] = true;
                    arr[2] = true;
                    map.put(p, arr);
                }

                Boolean[] arr = map.get(p);

                if (q == 2 || q == 3) {
                    arr[0] = false;
                } else if (q == 4 || q == 5) {
                    arr[1] = false;
                    arr[0] = false;
                } else if (q == 6 || q == 7) {
                    arr[1] = false;
                    arr[2] = false;
                } else {
                    arr[2] = false;
                }
                map.put(p, arr);
            }

            // 没人坐的行最多做两组
            int ans = (n - map.size()) * 2;
            for (Boolean[] arr : map.values()) {
                if (arr[0] && arr[1] && arr[2]) {
                    ans += 2;
                } else if (arr[0] || arr[1] || arr[2]) {
                    ans++;
                }
            }
            return ans;

        }
    }
}
