package hacker;

import java.util.List;

public class SeperateChocolate {
    public static int birthday(List<Integer> s, int d, int m) {

        int start = 0;
        int count = 0;
        int sum = 0;

        for(int i = 0; i < s.size(); i++) {
            sum += s.get(i);
            if(i - start +1 == m) {
                if(sum == d) {
                    count++;
                }

                sum -= s.get(start);
                start++;
            }
        }
        return count;
    }

}

