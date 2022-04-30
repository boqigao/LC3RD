package weekely;

import java.util.*;

/**
 * 这道题明确说明没有嵌套的
 */
public class LC1087BraceExpansion {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.expand("{a,b}c{d,e}f")));
    }
    static class Solution {
        public String[] expand(String s) {
            List<List<String>> storeList = new ArrayList<>();

            for (int i = 0; i < s.length(); i++) {
                // 1. {
                if (s.charAt(i) == '{') {
                    int j = s.indexOf("}", i);
                    String[] sub = s.substring(i + 1, j).split(",");
                    List<String> tmp = new ArrayList<>(Arrays.asList(sub));
                    storeList.add(tmp);
                    i = j;
                } else {
                    // normal char
                    List<String> tmp = new ArrayList<>();
                    tmp.add(s.charAt(i) + "");
                    storeList.add(tmp);
                }
            }

            // for the list
            List<String> ansList = new ArrayList<>();

            ansList.add("");

            for (List<String> list : storeList) {
                List<String> tmpList = new ArrayList<>();
                for (String str : list) {
                    for (String ext : ansList) {
                        tmpList.add(ext + str);
                    }
                }
                ansList = tmpList;
            }

            String[] res = new String[ansList.size()];
            for (int i = 0;i  < ansList.size(); i ++) {
                res[i]  = ansList.get(i);
            }
            Arrays.sort(res);
            return (res);

        }
    }
}
