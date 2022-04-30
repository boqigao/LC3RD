package facebook;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collector;

public class LC1762BuildingsWithAnOceanView {
    public static void main(String[] args) {
        int[] heights = {4,3,2,1};
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.findBuildings(heights)));
    }
    static class Solution {
        public int[] findBuildings(int[] heights) {
            Stack<Integer> stack = new Stack<>();

            int maxHeight = heights[heights.length - 1];
            stack.add(heights.length - 1);
            List<Integer> ans = new ArrayList<>();
            ans.add(heights.length - 1);
            for (int i = heights.length - 2; i >= 0; i--) {
                if (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                    ans.add(i);
                    stack.push(i);
                }
                maxHeight = Math.max(maxHeight, heights[i]);
            }
            List<Integer> arr= new ArrayList<>();
            arr.addAll(stack);
            Collections.sort(arr);

            return arr.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
