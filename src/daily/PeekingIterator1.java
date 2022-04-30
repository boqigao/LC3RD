package daily;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator1 {

    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer peekedValue = null;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (!(peekedValue == null)) {
                return peekedValue;
            } else {
                if (!iterator.hasNext()) {
                    throw new NoSuchElementException();
                }
                peekedValue = iterator.next();
                return peekedValue;
            }
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (!(peekedValue == null)) {
                Integer toReturn = peekedValue;
                peekedValue = null;
                return toReturn;
            }
            if (!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return peekedValue != null || iterator.hasNext();
        }
    }
}
