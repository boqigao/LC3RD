package hacker;

import java.util.Arrays;
import java.util.List;

public class GridChallenge {
    public static String gridChallenge(List<String> grid) {
        // Write your code here
        int n = grid.size();
        char[][] arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = grid.get(i).charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.sort(arr[i]);
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n-1; j++) {
                if (arr[i+1][j] - arr[i][j] < 0) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}
