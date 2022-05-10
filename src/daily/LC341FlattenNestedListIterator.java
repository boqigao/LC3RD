package daily;

public class LC341FlattenNestedListIterator {
    /*
    class NestedIterator implements Iterator<Integer> {

        private Deque<NestedInteger> stack;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new ArrayDeque(nestedList);
        }

        @Override
        public Integer next() {
            // As per java specs, throw an exception if there's no elements left.
            if (!hasNext()) throw new NoSuchElementException();
            // hasNext ensures the stack top is now an integer. Pop and return
            // this integer.
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            makeStackTopAnInteger();
            return !stack.isEmpty();
        }

        private void makeStackTopAnInteger() {
            while(!stack.isEmpty() && !stack.peek().isInteger()) {
                List<NestedInteger> nestedList = stack.pop().getList();
                for (int i = nestedList.size() - 1; i >= 0; i--) {
                    stack.push(nestedList.get(i));
                }
            }
        }
    }*/
}
