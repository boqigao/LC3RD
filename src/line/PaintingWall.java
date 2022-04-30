package line;

public class PaintingWall {
    class Solution {
        public int brushCount(int[] A) {
            int brushCount = 0;
            int prevHeight = 0;
            for (int i = 0; i < A.length; i++) {

                if (A[i] > prevHeight) {
                    brushCount += A[i] - prevHeight;
                }

                prevHeight = A[i];
            }
            return brushCount;
        }
    }
}
