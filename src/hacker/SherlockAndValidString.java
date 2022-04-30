package hacker;

import java.util.*;

public class SherlockAndValidString {
    public static String isValid(String s) {

        // Write your code here
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Integer> freq = new ArrayList(map.values());
        int min = Collections.min(freq);
        // 如果min为1说明可能只有一个数字或者所有数字都只出现了以一次
        if (min == 1) {
            if (freq.stream().filter(e -> e == min).count() == 1) {
                freq.remove(freq.indexOf(min));
                return (freq.stream().distinct().count() <= 1) ? "YES" : "NO";
            }
        }

        // e 大于min的并且所有大于min的数字与min的差值不大于1才行
        if (freq.stream().filter(e -> e > min).map(e -> e - min).reduce(0, Integer::sum) > 1) {
            return "NO";
        }
        return "YES";
    }
}
