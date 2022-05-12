package weekely;


import java.util.HashSet;
import java.util.Set;

/**
 * keywords: backtrack
 */
public class LC489RobotRoomCleaner {
    class Solution {

        // 这里不能乱写，因为我们要按照顺时针旋转的，所以是，右下左上
        int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

        public void cleanRoom(Robot robot) {
            Set<String> visited = new HashSet<>();
            backtracking(robot, visited, 0, 0, 0);
        }

        /**
         *
         * @param r
         * @param visited
         * @param x
         * @param y
         * @param arrow  arrow在这道题里应该是一个最重要的东西，因为我们需要他来记录当前的方向
         */
        private void backtracking(Robot r, Set<String> visited, int x, int y, int arrow) {
            r.clean();

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[arrow][0];
                int ny = y + dirs[arrow][1];
                String path = nx + "-" + ny;
                // r 是向着arrow方向前进的
                if (!visited.contains(path) && r.move()) {
                    visited.add(path);
                    backtracking(r, visited, nx, ny, arrow);
                }
                r.turnRight();
                arrow = (arrow + 1) % 4;
            }
            r.turnRight();
            r.turnRight();
            r.move();
            r.turnRight();
            r.turnRight();
        }
    }


    class Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move() {
            return true;
        }

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft() {

        }

        void turnRight() {

        }

        // Clean the current cell.
        void clean() {
        }

    }
}
