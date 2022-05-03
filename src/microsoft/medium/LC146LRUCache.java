package microsoft.medium;

import java.util.HashMap;
import java.util.Map;

public class LC146LRUCache {
    public static void main(String[] args) {
        LRUCache2nd.LRUCache s = new LRUCache2nd.LRUCache(2);
        s.put(1,1);
        s.put(2,2);
        s.put(3,3);
    }

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
         *
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
         *
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
         *
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

    static class LRUCache2nd {
        static class LRUCache {
            // operations about the linked list
            // most basic: add and remove

            // add directly after the head
            private void addANodeToHead(Node node) {
                Node tmpNext = head.next;
                head.next = node;
                node.pre = head;
                node.next = tmpNext;
                tmpNext.pre = node;
            }

            // remove a node: cut the connection
            private void removeNode(Node node) {
                Node tmpPre = node.pre;
                Node tmpNext = node.next;
                tmpPre.next = tmpNext;
                tmpNext.pre = tmpPre;
            }

            // combine the existing two methods
            // move an existing node to head
            private void moveANodeToHead(Node node) {
                removeNode(node);
                addANodeToHead(node);
            }

            private Node removeATailNode() {
                Node res = tail.pre;
                removeNode(res);
                // why return the res? because we use this to delete the entry in the map
                return res;
            }

            private int capacity;
            private int size;
            private HashMap<Integer, Node> map;
            private Node head;
            private Node tail;

            public LRUCache(int capacity) {
                this.map = new HashMap<>();
                this.size = 0;
                this.capacity = capacity;
                this.head = new Node();
                this.tail = new Node();
                this.head.next = tail;
                this.tail.pre = head;
            }

            // if update value:
            // if add new key,value
            //      if not oversize
            //      if oversize : remove tail node
            public void put(int key, int value) {
                Node node = map.get(key);
                if (node != null) {
                    // update value
                    node.val = value;
                    moveANodeToHead(node);
                } else {
                    // add new node to map and list
                    node = new Node();
                    node.key = key;
                    node.val = value;
                    map.put(key, node);
                    addANodeToHead(node);

                    // remove the tail node from map and list if oversize
                    size++;
                    if (size > capacity) {
                        Node toRemove = removeATailNode();
                        map.remove(toRemove.key);
                    }
                }
            }

            // two things, get the value, and move the node to head
            public int get(int key) {
                Node node = map.get(key);
                if (node != null) {
                    moveANodeToHead(node);
                    return node.val;
                } else {
                    return -1;
                }
            }


        }

        static class Node {
            int key;
            int val;
            Node pre;
            Node next;

        }
    }
}
