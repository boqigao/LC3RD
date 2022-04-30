package microsoft.easy;

public class LC1275FindWinnerOnATicTac {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] moves = new int[6][2];
        moves[0] = new int[] {1, 0};
        moves[1] = new int[] {0, 0};
        moves[2] = new int[] {0, 2};
        moves[3] = new int[] {1, 1};
        moves[4] = new int[] {1, 2};
        moves[5] = new int[] {0, 1};

        System.out.println(s.tictactoe(moves));
    }
    static class Solution {
        public String tictactoe(int[][] moves) {
            int[][] grid = new int[3][3];
            for (int i = 0; i < moves.length; i++) {
                if (i % 2 == 0) {
                    // A
                    grid[moves[i][0]][moves[i][1]] = 1;
                } else {
                    // B
                    grid[moves[i][0]][moves[i][1]] = 2;
                }

                if (checkWinner(grid) == 1) {
                    return "A";
                } else if (checkWinner(grid) == 2) {
                    return "B";
                }
            }

            if (moves.length == 9) {
                return "Draw";
            } else {
                return "Pending";
            }
        }

        private int checkWinner(int[][] grid) {
            int ul = grid[0][0];
            int um = grid[0][1];
            int ur = grid[0][2];
            int ml = grid[1][0];
            int mm = grid[1][1];
            int mr = grid[1][2];
            int dl = grid[2][0];
            int dm = grid[2][1];
            int dr = grid[2][2];

            if (ur == mm && mm == dl) {
                if (ur == 1) {
                    return 1;
                } else if (ur == 2) {
                    return 2;
                }
            }

            if (ur == mr && mr == dr) {
                if (ur == 1) {
                    return 1;
                } else if (ur == 2) {
                    return 2;
                }
            }

            if (ul == um && um == ur) {
                if (ul == 1) {
                    return 1;
                } else if (ul == 2) {
                    return 2;
                }
            }

            if (ul == mm && ul == dr) {
                if (ul == 1) {
                    return 1;
                } else if (ul == 2) {
                    return 2;
                }
            }

            if (ul == ml && ul == dl) {
                if (ul == 1) {
                    return 1;
                } else if (ul == 2) {
                    return 2;
                }
            }

            if (ml == mm && mm == mr) {
                if (ml == 1) {
                    return 1;
                } else if (ml == 2) {
                    return 2;
                }
            }

            if (dl == dm && dm == dr) {
                if (dl == 1) {
                    return 1;
                } else if (dl == 2) {
                    return 2;
                }
            }

            if (um == mm && mm == dm) {
                if (um == 1) {
                    return 1;
                } else if (um == 2) {
                    return 2;
                }
            }
            return 0;
        }
    }
}
