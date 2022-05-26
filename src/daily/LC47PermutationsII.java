package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * keyword: backtrack
 */
public class LC47PermutationsII {
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            boolean[] visited = new boolean[nums.length];
            List<List<Integer>> res = new LinkedList<>();
            generatePerm(visited, res, new ArrayList<>(), nums);
            return res;

        }

        void generatePerm(boolean[] visited, List<List<Integer>> result, ArrayList<Integer> curList, int[] nums) {
            if (curList.size() == nums.length) {
                result.add(new LinkedList<>(curList));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    if (!visited[0]) {
                        // 和下面完全一样，主要是为了区分index = 0的情况
                        visited[i] = true;
                        curList.add(nums[i]);
                        generatePerm(visited, result, curList, nums);
                        curList.remove(curList.size() - 1);
                        visited[i] = false;
                    }
                } else if (nums[i] == nums[i - 1] && visited[i - 1]) {
                    continue;
                } else {
                    if (!visited[i]) {
                        visited[i] = true;
                        curList.add(nums[i]);
                        generatePerm(visited, result, curList, nums);
                        curList.remove(curList.size() - 1);
                        visited[i] = false;
                    }
                }
            }
        }
    }
}
