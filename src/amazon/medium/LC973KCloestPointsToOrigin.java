package amazon.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * key words: pq, binary search, quick sort
 */
public class LC973KCloestPointsToOrigin {

    /**
     * 一旦看到选择第k个最小或者k个最大之类的，我们就可以考虑quickselect
     * quickselect 的核心是边换位置边找到那个k值，直到pivotIndex是k了
     * 比如pivotIndex = k了，就说明左边的值一定已经全部小于右边的值了
     *
     *
     */
    class SolutionQuickSelection {
        public int[][] kClosest(int[][] points, int k) {
            return quickSelect(points, k);
        }

        private int[][] quickSelect(int[][] points, int k) {
            int left = 0, right = points.length - 1;
            int pivotIndex = points.length;
            while (pivotIndex != k) {
                // repeated partition the array
                // while narrowing in on the kth element
                pivotIndex = paitition(points, left, right);
                if (pivotIndex < k) {
                    left = pivotIndex;
                } else {
                    right = pivotIndex - 1;
                }
            }

            // return the first k elements
            return Arrays.copyOf(points, k);
        }

        private int paitition(int[][] points, int left, int right) {
            // 选一个中轴点
            int[] pivot = choosePivot(points, left, right);
            int pivotDist = squaredDistance(pivot);
            while (left < right) {
                if (squaredDistance(points[left]) >= pivotDist) {
                    // 如果左边大于等于，不管右边，左右先互换
                    int[] tmp = points[left];
                    points[left] = points[right];
                    points[right] = tmp;
                    right--;
                } else {
                    // 左边如果本来就小，就先不管了
                    left++;
                }
            }
            // Ensure the left pointer is just past the end of
            // the left range then return it as the new pivotIndex
            if (squaredDistance(points[left]) < pivotDist)
                left++;
            return left;
        }

        private int[] choosePivot(int[][] points, int left, int right) {
            // Choose a pivot element of the array
            return points[left + (right - left) / 2];
        }

        private int squaredDistance(int[] point) {
            // Calculate and return the squared Euclidean distance
            return point[0] * point[0] + point[1] * point[1];
        }


    }

    class SolutionBinarySearch {
        public int[][] kClosest(int[][] points, int k) {
            double[] distances = new double[points.length];
            double low = 0, high = 0;
            List<Integer> remaining = new ArrayList<>();
            for (int i = 0; i < points.length; i++) {
                distances[i] = euclideanDistance(points[i]);
                high = Math.max(high, distances[i]);
                remaining.add(i);
            }

            // Perform a binary search of the distances
            // to find the k closest points
            List<Integer> closest = new ArrayList<>();
            while (k > 0) {
                double mid = low + (high - low) / 2;
                List<List<Integer>> result = splitDistances(remaining, distances, mid);
                List<Integer> closer = result.get(0), farther = result.get(1);
                if (closer.size() > k) {
                    // if more than k points are in the closer distances
                    // the discard the farther points and continue
                    remaining = closer;
                    high = mid;
                } else {
                    // add the closer points to the answer array and keep searching the farther distances for the remaining
                    // points
                    k -= closer.size();
                    closest.addAll(closer);
                    remaining = farther;
                    low = mid;
                }
            }

            k = closest.size();
            int[][] answer = new int[k][2];
            for (int i = 0; i < k; i++) {
                answer[i] = points[closest.get(i)];
            }
            return answer;
        }

        /**
         * Split the distances around the midpint
         * and return them in separate lists
         *
         * @param remaining
         * @param distances
         * @param mid
         * @return
         */
        private List<List<Integer>> splitDistances(List<Integer> remaining, double[] distances, double mid) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> closer = new ArrayList<>();
            List<Integer> farther = new ArrayList<>();
            result.add(closer);
            result.add(farther);

            for (int point : remaining) {
                if (distances[point] <= mid) {
                    closer.add(point);
                } else {
                    farther.add(point);
                }
            }
            return result;
        }

        private double euclideanDistance(int[] point) {
            // Calculate and return the squared Euclidean distance
            return point[0] * point[0] + point[1] * point[1];
        }
    }

    class SolutionPQ {
        public int[][] kClosest(int[][] points, int k) {
            // PQ's solution maps the dist and index
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.dist - a.dist);
            for (int i = 0; i < points.length; i++) {
                // we need to control the size of pq
                Pair pair = new Pair(squaredDistance(points[i]), i);
                if (pq.size() < k) {
                    pq.add(pair);
                } else {
                    if (pq.peek().dist > pair.dist) {
                        pq.poll();
                        pq.add(pair);
                    }
                }
            }
            int[][] ans = new int[k][2];

            for (int i = 0; i < k; i++) {
                Pair pair = pq.poll();
                ans[i][0] = points[pair.index][0];
                ans[i][1] = points[pair.index][1];
            }
            return ans;

        }

        private int squaredDistance(int[] point) {
            // Calculate and return the squared Euclidean distance
            return point[0] * point[0] + point[1] * point[1];
        }

        class Pair {
            int dist;
            int index;

            public Pair(int dist, int index) {
                this.dist = dist;
                this.index = index;
            }
        }
    }
}
