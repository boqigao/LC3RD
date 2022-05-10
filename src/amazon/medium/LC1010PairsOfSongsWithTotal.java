package amazon.medium;

import java.util.HashMap;

public class LC1010PairsOfSongsWithTotal {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}));
    }

    static class Solution {
        public int numPairsDivisibleBy60(int[] time) {
            HashMap<Integer, Integer> map = new HashMap<>();

            int res = 0;
            for (int j : time) {
                if (j % 60 == 0) {
                    res += map.getOrDefault(0, 0);
                    map.put(0, map.getOrDefault(0, 0) + 1);
                } else {
                    int sub = 60 - j % 60;
                    res += map.getOrDefault(sub, 0);
                    map.put(j % 60, map.getOrDefault(j % 60, 0) + 1);
                }
            }
            return res;
        }
    }
}
