package hacker;

public class CaesarCipher {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String s = "middle-Outz";
        int k = 2;
        k %= 26;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
                if (c <= 'Z' && c >= 'A') {
                    char caesarChar = c;
                    caesarChar += k;
                    if (caesarChar > 'Z') {
                        caesarChar-=26;
                    }
                    sb.append(caesarChar);
                } else {
                    char caesarChar = c;
                    caesarChar += k;
                    if (caesarChar > 'z') {
                        caesarChar-=26;
                    }
                    sb.append(caesarChar);
                }
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb.toString());
    }
}
