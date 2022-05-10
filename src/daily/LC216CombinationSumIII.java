package daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC216CombinationSumIII {
    class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            LinkedList<Integer> comb = new LinkedList<Integer>();

            this.backtrack(n, k, comb, 0, results);
            return results;
        }

        private void backtrack(int remain, int k, LinkedList<Integer> comb, int next_start, List<List<Integer>> result) {
            if (remain == 0 && comb.size() == k) {
                // 找到一个结果了
                result.add(new ArrayList<>(comb));
                return;
            } else if (remain < 0 || comb.size() == k) {
                // 超了
                return;
            }

            // Iterate through the reduced list of candidates
            for (int i = next_start; i < 9; i++) {
                comb.add(i + 1);
                this.backtrack(remain - i - 1, k, comb, i + 1, result);
                comb.removeLast();
            }
        }
    }

    class Solution2 {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            combination(ans, new ArrayList<Integer>(), k, 1, n);
            return ans;
        }

        private void combination(List<List<Integer>> ans, List<Integer> comb, int k, int start, int n) {
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i <= 9; i++) {
                comb.add(i);
                // 我们计划i从小到大，而且i不可重复，所以下一个start直接用i+1
                combination(ans, comb, k, i + 1, n - i);
                comb.remove(comb.size() - 1);
            }


        }
    }
}
