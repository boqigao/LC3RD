package hacker;

import java.util.Locale;

public class LongProblem {
    public static void main(String[] args) {
        String nbits = Long.toBinaryString(46456135843658L);
        int len = nbits.length();

        // Add 1s for empty bits
        String bits = "";
        for (int i = 32; i > len; i--) {
            bits += "1";
        }
        bits.toLowerCase().replace(" ", "").chars().distinct();

        // Add 1 or 0 based on each char of nbits
        for (int i = 0; i < len; i++) {
            if (nbits.charAt(i) == '1') {
                bits += "0";
            } else {
                bits += "1";
            }
        }

        // Return parsed number
        System.out.println(Long.parseLong(bits, 2));

    }
}
