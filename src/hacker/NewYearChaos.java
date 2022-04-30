package hacker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NewYearChaos {
    public static void minimumBribes(List<Integer> q) {
        // Write your code here

        int bribes = 0;
        for (int i = q.size() - 1; i >= 0; i--) {
            int targetValue = i + 1;
            int skips = 0;
            int targetIndex = i;
            while(q.get(targetIndex) != targetValue) {
                ++skips;
                if (skips > 2) {
                    System.out.println("Too chaotic");
                    return;
                }
                --targetIndex;
            }
            bribes += skips;
            q.remove(targetIndex);
        }
        System.out.println(bribes);

    }

}
