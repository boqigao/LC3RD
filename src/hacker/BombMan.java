package hacker;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BombMan {
    public static List<String> bomberMan(int n, List<String> grid) {
        if (n == 0 || n == 1) {
            return grid;
        } else if (n % 2 == 0) {
            return grid.parallelStream().map(x -> x.replaceAll(".", "O")).collect(toList());
        }
        List<String[]> arr = grid.stream().map(x -> x.split("")).collect(toList());
        List<String[]> phaseOne = explode(arr);
        List<String[]> phaseTwo = explode(phaseOne);
        return (n + 1) % 4 == 0 ?
                phaseOne.parallelStream().map(x -> String.join("", x)).collect(toList()) :
                phaseTwo.parallelStream().map(x -> String.join("", x)).collect(toList());
    }

    private static List<String[]> explode(List<String[]> arr) {
        List<String[]> result = new ArrayList<>();
        for (int row = 0; row < arr.size(); row++) {
            String[] buff = new String[arr.get(row).length];
            for (int col = 0; col < arr.get(row).length; col++) {
                if (arr.get(row)[col].equals("O")) {
                    buff[col] = ".";
                } else {
                    boolean explode =
                            (row != 0 && arr.get(row - 1)[col].equals("O")) ||
                                    (row != arr.size() - 1 && arr.get(row + 1)[col].equals("O")) ||
                                    (col != 0 && arr.get(row)[col - 1].equals("O")) ||
                                    (col != arr.get(row).length - 1 && arr.get(row)[col + 1].equals("O"));
                    if (explode)
                        buff[col] = ".";
                    else
                        buff[col] = "O";
                }
            }
            result.add(buff);
        }
        return result;
    }

}

