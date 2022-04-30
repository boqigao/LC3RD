package hacker;

public class PageCount {

    public static int pageCount(int n, int p) {
        int half = n / 2;
        if(p <= half){
            return p / 2;
        }
        return half - (p / 2);
    }
}
