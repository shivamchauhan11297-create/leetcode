import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, sq;
    int[] lz;

    private void apply(int o, int len, int v) {
        sq[o] += 2L * v * sum[o] + (long) v * v * len;
        sum[o] += (long) v * len;
        lz[o] += v;
    }

    private void push(int o, int l, int r) {
        if (lz[o] != 0) {
            int mid = (l + r) >>> 1;
            apply(o * 2, mid - l + 1, lz[o]);
            apply(o * 2 + 1, r - mid, lz[o]);
            lz[o] = 0;
        }
    }

    private void update(int o, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            apply(o, r - l + 1, 1);
            return;
        }
        push(o, l, r);
        int mid = (l + r) >>> 1;
        if (ql <= mid) update(o * 2, l, mid, ql, qr);
        if (qr > mid) update(o * 2 + 1, mid + 1, r, ql, qr);
        sum[o] = sum[o * 2] + sum[o * 2 + 1];
        sq[o] = sq[o * 2] + sq[o * 2 + 1];
    }

    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        sum = new long[4 * n];
        sq = new long[4 * n];
        lz = new int[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        for (int j = 0; j < n; j++) {
            int x = nums.get(j);
            int prev = last.getOrDefault(x, -1);
            update(1, 0, n - 1, prev + 1, j);
            ans = (ans + sq[1]) % MOD;
            last.put(x, j);
        }
        return (int) ans;
    }
}