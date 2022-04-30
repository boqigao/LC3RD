package mercari;

public class LC1432MaxDifferenceYouCanGetFrom {
    class Solution {
        public int maxDiff(int num) {
            char[] a = (num + "").toCharArray();
            char[] b = a.clone();

            char x = a[0], y = 0; // y在初始情况下一定不等于x
            // x 用来记录第一位
            // y 用来记录需要变化的第二位
            for (int i = 0; i < a.length; i++) {
                if (a[i] == x) {
                    // 如果源数字第一位不是9/1，那么最大数就是源数字换9，最小数就是源数字换1，
                    a[i] = '9';
                    b[i] = '1';
                } else if (x == '1' && a[i] > '0' || x == '9' && a[i] < '9') {
                    // 这里是保证还是初始情况，因此可以用来记录了，又因为i是从左到右的，所以只要记录尽可能最左边的就可以了
                    if (y == 0) {
                        y = a[i];
                    }
                    if (y == a[i]) {
                        // 如果a的第一位是1，那么说明b的第一位不用换了，所以还有一次机会，那么我们可以把b的尽量左的换成0
                        if (x == '1') {
                            b[i] = '0';
                        } else {
                            // 如果a的第一位是9，那么说明a的第一次不用还了，那么我们还有一次机会，我们可以把a的尽量左换成9
                            a[i] = '9';
                        }
                    }
                }
            }
            return Integer.parseInt(String.valueOf(a)) - Integer.parseInt(String.valueOf(b));
        }
    }
}
