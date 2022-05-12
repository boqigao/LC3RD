package google.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC1293ShortestPathInAGridWithObstacleElimination {
    class Solution {
        int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

        public int shortestPath(int[][] grid, int k) {
            int rows = grid.length, cols = grid[0].length;
            int[] target = {rows - 1, cols - 1};

            // if we have sufficient quotas to eliminate the obstacles in the worst case,
            // then the shortest distance is the Manhattan distance.
            if (k >= rows + cols - 2) {
                return rows + cols - 2;
            }

            Queue<StepState> queue = new LinkedList<>();
            Set<StepState> seen = new HashSet<>();

            // (steps, row, col, remaining quota to eliminate obstacles)
            StepState start = new StepState(0, 0, 0, k);
            queue.offer(start);
            seen.add(start);

            while (!queue.isEmpty()) {
                StepState curr = queue.poll();

                // we reach the target here
                if (target[0] == curr.row && target[1] == curr.col) {
                    return curr.steps;
                }

                // explore the four directions in the next step
                for (int[] dir : dirs) {
                    int nextRow = curr.row + dir[0];
                    int nextCol = curr.col + dir[1];

                    // out of the boundary of grid
                    if (0 > nextRow || nextRow == rows || 0 > nextCol || nextCol == cols) {
                        continue;
                    }

                    int nextElimination = curr.k - grid[nextRow][nextCol];
                    StepState newState = new StepState(curr.steps + 1, nextRow, nextCol, nextElimination);

                    // add the next move in the queue if it qualifies.
                    if (nextElimination >= 0 && !seen.contains(newState)) {
                        seen.add(newState);
                        queue.offer(newState);
                    }
                }
            }

            // did not reach the target
            return -1;
        }

        class StepState {
            /**
             * data object to keep the state info for each step:
             * <steps, row, col, remaining_eliminations>
             */
            public int steps, row, col, k;

            public StepState(int steps, int row, int col, int k) {
                this.steps = steps;
                this.row = row;
                this.col = col;
                this.k = k;
            }

            @Override
            public boolean equals(Object other) {
                /**
                 * only (row, col, k) matters as the state info
                 */
                if (!(other instanceof StepState)) {
                    return false;
                }
                StepState newState = (StepState) other;
                return (this.row == newState.row) && (this.col == newState.col) && (this.k == newState.k);
            }

            @Override
            public int hashCode() {
                // needed when we put objects into any container class
                return (this.row + 1) * (this.col + 1) * this.k;
            }
        }

    }
}
