package facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC380InsertDeleteGrtRandom {


    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(51);
        set.insert(2);
        set.insert(10);
        set.insert(63);
        set.insert(57);
        set.remove(10);

    }

    static class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        public RandomizedSet() {
            this.list = new ArrayList<>();
            this.map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            map.put(val, list.size());
            list.add(list.size(), val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            int lastElement = list.get(list.size() - 1);
            int idx = map.get(val);
            list.set(idx, lastElement);
            map.put(lastElement, idx);
            // delete the last element
            list.remove(list.size() - 1);
            map.remove(val);

            return true;
        }

        public int getRandom() {
            int idx = (int)(list.size() * Math.random());
            return list.get(idx);
        }
    }
}
