package facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC811 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] strs = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(s.subdomainVisits(strs));
    }
    static class Solution {
        public List<String> subdomainVisits(String[] cpdomains) {
            Map<String, Integer> map = new HashMap<>();
            for (String cpdomain : cpdomains) {
                String[] numAndDomains = cpdomain.split(" ");
                int num = Integer.parseInt(numAndDomains[0]);
                String[] domains = numAndDomains[1].split("\\.");

                // only 2 or 3
                if (domains.length == 2) {
                    // 2 sub domain

                    String subDomain1 = domains[0] + "." + domains[1];
                    String subDomain2 = domains[1];

                    if (!map.containsKey(subDomain1)) {
                        map.put(subDomain1, num);
                    } else {
                        map.put(subDomain1, map.get(subDomain1) + 1);
                    }

                    if (!map.containsKey(subDomain2)) {
                        map.put(subDomain2, num);
                    } else {
                        map.put(subDomain2, map.get(subDomain2) + 1);
                    }

                } else {
                    // 3 sub domain
                    String subDomain1 = domains[1] + "." + domains[2];
                    String subDomain2 = domains[2];
                    String subDomain3 = numAndDomains[1];

                    if (!map.containsKey(subDomain1)) {
                        map.put(subDomain1, num);
                    } else {
                        map.put(subDomain1, map.get(subDomain1) + 1);
                    }
                    if (!map.containsKey(subDomain2)) {
                        map.put(subDomain2, num);
                    } else {
                        map.put(subDomain2, map.get(subDomain2) + 1);
                    }
                    if (!map.containsKey(subDomain3)) {
                        map.put(subDomain3, num);
                    } else {
                        map.put(subDomain3, map.get(subDomain3) + 1);
                    }

                }


            }

            List<String> ans = new ArrayList<>();

            for (String key : map.keySet()) {
                String tmp = map.get(key) + " " + key;
                ans.add(tmp);

            }
            return ans;
        }

    }
}
