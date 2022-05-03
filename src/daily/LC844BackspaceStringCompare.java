package daily;

public class LC844BackspaceStringCompare {
    class Solution {
        public boolean backspaceCompare(String s, String t) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '#' && sb1.length() != 0) {
                    sb1.deleteCharAt(sb1.length() - 1);
                } else if (s.charAt(i) == '#') {
                    continue;
                } else {
                    sb1.append(s.charAt(i));
                }
            }

            for (int i = 0; i < t.length(); i++) {
                if (t.charAt(i) == '#' && sb2.length() != 0) {
                    sb2.deleteCharAt(sb2.length() - 1);
                } else if (t.charAt(i) == '#') {
                    continue;
                } else {
                    sb2.append(t.charAt(i));
                }
            }

            return sb1.toString().equals(sb2.toString());
        }
    }
}
