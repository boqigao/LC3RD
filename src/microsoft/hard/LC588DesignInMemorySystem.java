package microsoft.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC588DesignInMemorySystem {
    class FileSystem {

        // 文件夹里面包含 dir 和 files
        class Dir {
            // 子文件夹
            HashMap<String, Dir> dirs = new HashMap<>();
            HashMap<String, String> files = new HashMap<>();
        }

        Dir root;

        public FileSystem() {
            root = new Dir();
        }

        public List<String> ls(String path) {
            Dir t = root;
            List<String> files = new ArrayList<>();
            if (!path.equals("/")) {
                String[] d = path.split("/");
                for (int i = 1; i < d.length - 1; i++) {
                    t = t.dirs.get(d[i]);
                }
                if (t.files.containsKey(d[d.length - 1])) {
                    // 如果要list的是一个文件
                    files.add(d[d.length - 1]);
                    return files;
                } else {
                    // 要list的是一个dir
                    t = t.dirs.get(d[d.length - 1]);
                }
            }
            files.addAll(new ArrayList<>(t.dirs.keySet()));
            files.addAll(new ArrayList<>(t.files.keySet()));
            Collections.sort(files);
            return files;
        }

        public void mkdir(String path) {
            Dir t = root;
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                if (!t.dirs.containsKey(d[i])) {
                    t.dirs.put(d[i], new Dir());
                }
                t = t.dirs.get(d[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            Dir t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);

        }


        public String readContentFromFile(String filePath) {
            Dir t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            return t.files.get(d[d.length - 1]);
        }
    }
}
