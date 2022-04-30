package hacker;

import java.util.ArrayList;
import java.util.List;

public class DynamicArray {
    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<List<Integer>> arr = new ArrayList<>(n);
        int lastAnswer = 0;
        List<Integer> answers = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }

        for(List<Integer> query : queries) {
            int queryNum = query.get(0);
            int x = query.get(1);
            int y = query.get(2);
            if(queryNum == 1) {
                int idx = (x ^ lastAnswer) % n;
                arr.get(idx).add(y);
            }else {
                int idx = (x ^ lastAnswer) % n;
                lastAnswer = arr.get(idx).get(y % arr.get(idx).size());
                answers.add(lastAnswer);
            }
        }

        return answers;
    }

}

