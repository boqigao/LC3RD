package hacker;

import java.util.List;

public class SherlockAndArray {
    public static String balancedSums(List<Integer> numbers) {
        final int total = numbers.stream().reduce(0, Integer::sum);
        int sum = 0;
        String result = "NO";
        for(Integer element:numbers){
            sum+=element;
            if(sum-element==total-sum){
                result = "YES";
                break;
            }
        }
        return result;
    }
}
