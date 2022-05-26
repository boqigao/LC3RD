package codility.queueandstack;
import java.util.*;
public class Fish {
    class Solution {
        public int solution(int[] A, int[] B) {
            // write your code in Java SE 8
            Stack<Integer> stack = new Stack<>();
            // prev means the current largest num when b[i] == 1
            int prev = -1;
            int ans = 0;
            for (int i = 0; i < A.length; i++) {
                int fish = A[i];
                int direction = B[i];
                if (direction == 0) {
                    boolean survies = true;
                    while(!stack.isEmpty()) {
                        int oppFish = stack.pop();
                        if (oppFish > fish) {
                            stack.push(oppFish);
                            survies = false;
                            break;
                        }
                    }
                    if (survies) {
                        ans++;
                    }
                } else {
                    stack.push(fish);
                }
            }
            ans += stack.size();
            return ans;
        }
    }
}
