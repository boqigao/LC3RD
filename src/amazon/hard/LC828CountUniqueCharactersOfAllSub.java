package amazon.hard;

public class LC828CountUniqueCharactersOfAllSub {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.uniqueLetterString("ABA"));
    }

    static class Solution {
        public int uniqueLetterString(String s) {
            int[] contribution = new int[26];
            int[] lastAppear = new int[26];

            int res = 0;

            for (int i = 0; i < s.length(); i++) {
                int cur = s.charAt(i) - 'A';

                /**
                 * ABCD 的话，以D结尾的字符串就是 abcd,bcd, cd, d,四个
                 */
                int oriContribOfCur = i + 1;

                /**
                 * ABCDB的话，本身B结尾是5个
                 * 但是B的贡献度要减去前面的B，因为ABCDB，和BCDB都不能满足，所以对于以当前B结尾的字符串而言，B
                 * 这个贡献度是 5 - 2
                 */
                contribution[cur] = oriContribOfCur - (lastAppear[cur]);

                int ansOfCurChar = 0;

                for (int j = 0; j < 26; j++) {
                    ansOfCurChar += contribution[j];
                }

                res += ansOfCurChar;
                lastAppear[cur] = i + 1;
            }

            return res;
        }
    }
}
