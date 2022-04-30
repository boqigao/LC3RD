package facebook;

import java.util.ArrayList;
import java.util.List;

public class LC1570DotProductOfTwoSparseVector {
    class SparseVector {
        List<int[]> pairs;

        SparseVector(int[] nums) {
            pairs = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    pairs.add(new int[]{i, nums[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int res = 0, p = 0, q = 0;

            while (p < pairs.size() && q < vec.pairs.size()) {
                if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                    res += pairs.get(p)[1] * vec.pairs.get(q)[1];
                    q++;
                    p++;
                } else if (pairs.get(p)[0] >= vec.pairs.get(q)[0]) {
                    q++;
                } else {
                    p++;
                }
            }
            return  res;
        }
    }

}
