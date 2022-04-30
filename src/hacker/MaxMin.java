package hacker;

import java.util.Arrays;
import java.util.List;

public class MaxMin {
    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here
        int[] array = arr.stream().mapToInt(i->i).toArray();
        Arrays.sort(array);

        int minVal = Integer.MAX_VALUE;

        int left = 0;
        for (int right = 0; right < array.length; right++) {
            if (right - left + 1 == k) {
                minVal = Math.min(array[right] - array[left], minVal);
                left++;
            }
        }
        return minVal;
    }
}
