package hacker;

import java.util.*;

public class ClimbingLeaderBoard {
    public static void main(String[] args) {
        Integer[] ranked = {100, 100, 50, 40, 40, 20, 10};
        Integer[] player = {50, 65, 77, 90, 102};
        climbingLeaderboard(Arrays.asList(ranked), Arrays.asList(player));
    }

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        TreeSet<Integer> h = new TreeSet<>(ranked);
        ranked = new ArrayList<>(h);
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < player.size(); i++) {
            int ele = player.get(i);
            int idx = Math.abs(Collections.binarySearch(ranked, ele) + 1);
            res.add(ranked.size() - idx + 1);
        }
        return res;
    }
}
