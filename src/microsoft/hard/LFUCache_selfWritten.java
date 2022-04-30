package microsoft.hard;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class LFUCache_selfWritten {

    class LFUCache {

        // 这个值维护了一个最小count，如果要删除东西的时候就从最小count里面删掉一个
        private int min;

        // cache 的 size
        private int capacity;

        // 一个map，存储具体 键值对
        private HashMap<Integer, Integer> keyToVal;

        // 一个map，存储具体 的一个key 和当前这个key的count
        private HashMap<Integer, Integer> keyToCount;

        // 维护了一个count的map， 这个count管理了一个这个count的key
        // linkedHashSet 会维护一个插入的顺序，当遍历该集合的时候，iterator将会以插入顺序
        // 访问集合的元素，i.e., 第一个元素是最早插入的元素
        private  HashMap<Integer, LinkedHashSet<Integer>> countToLRUKeys;

        public LFUCache(int capacity) {
            this.min = -1;
            this.capacity = capacity;
            this.keyToVal = new HashMap<>();
            this.keyToCount = new HashMap<>();
            this.countToLRUKeys = new HashMap<>();
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key)) {
                return -1;
            }

            int count = keyToCount.get(key);
            countToLRUKeys.get(count).remove(key);
            // 这种只有最开始的时候会触发
            if (count == min && countToLRUKeys.get(count).size() == 0) {
                min++;
            }
            putCount(key, count + 1);
            return keyToVal.get(key);
        }

        public void put (int key, int value) {
            if (capacity <= 0) {
                return;
            }
            // 已经有了，update
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, value);
                int count = keyToCount.get(key);
                countToLRUKeys.get(count).remove(key);
                if (count == min && countToLRUKeys.get(count).size() == 0) {
                    // 这里count与min相等，说明count本身在min的list里，同时
                    // 在上一行删除了这个count，说明min也被删除了，那么min要和当前count保持同步
                    min++;
                }
                putCount(key, count + 1);
                return;
            }

            // 如果即将越界了越界了，要把最不频繁的删除，如果count相等，就把最旧的删除
            // 因为最旧的永远是minlist里的第一个，所以将其删除
            if(keyToVal.size() >= capacity) {
                // 单纯是因为用这种方法才能找到第一个
                evict(countToLRUKeys.get(min).iterator().next());
            }

            // 另外，因为是【put新东西以后越界的】，所以此时新东西的min是1,因此min要调整为1
            min = 1;
            putCount(key, min);
            keyToVal.put(key, value);
        }

        private void evict(int key) {
            // 一定是删minlist的第一个
            countToLRUKeys.get(min).remove(key);
            keyToVal.remove(key);
        }
        private void putCount(int key, int count) {
            keyToCount.put(key, count);
            countToLRUKeys.computeIfAbsent(count, kk -> new LinkedHashSet<>());
            countToLRUKeys.get(count).add(key);
        }

    }

}
