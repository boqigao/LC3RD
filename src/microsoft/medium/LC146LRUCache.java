package microsoft.medium;

import java.util.HashMap;
import java.util.Map;

public class LC146LRUCache {
    class LRUCache {

        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        // dummy tail and dummy head
        private DLinkedNode head, tail;

        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        /**
         * Add a new node to the linked list, remember that we always add it after the head
         * @param node
         */
        private void addNode(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        /**
         * remove an existing node from the list
         * @param node
         */
        private void removeNode(DLinkedNode node) {
            DLinkedNode prev = node.prev;
            DLinkedNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        /**
         * move a certain node to head.
         * @param node
         */
        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addNode(node);
        }

        /**
         * delete the tail node.
         */
        private DLinkedNode popTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode();

            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                node = new DLinkedNode();
                node.key = key;
                node.value = value;
                cache.put(key, node);
                addNode(node);
                size++;
                if (size > capacity) {
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                }
            } else {
                node.value = value;
                moveToHead(node);
            }

        }
    }
}
