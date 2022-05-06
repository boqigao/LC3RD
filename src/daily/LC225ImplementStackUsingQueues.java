package daily;

import java.util.LinkedList;
import java.util.Queue;

public class LC225ImplementStackUsingQueues {
    class MyStack {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        private int top;

        public MyStack() {
            top = 0;
        }

        public void push(int x) {
            q1.add(x);
            int sz = q1.size();
            while (sz > 1) {
                q1.add(q1.remove());
                sz--;
            }
        }

        public int pop() {
            return q1.remove();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.size() == 0;
        }
    }
}
