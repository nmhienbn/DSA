import edu.princeton.cs.algs4.*;

public class UFClient {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        int cnt = 0;
        while (!StdIn.isEmpty()) {
            cnt++;
            int p = StdIn.readInt();
            int q = StdIn.readInt();
                uf.union(p, q);
            if (uf.count() == 1) {
                StdOut.print(cnt);
                return;
            }
        }
        StdOut.print("FAILED");
    }
}