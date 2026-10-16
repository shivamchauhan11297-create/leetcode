class Solution {
    private int n;
    private long[] b1, b2;

    private void upd(long[] b, int i, long v) {
        for (; i <= n; i += i & -i) b[i] += v;
    }

    private long qry(long[] b, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += b[i];
        return s;
    }

    // prefix sum of cnt[1..i]
    private long prefix(int i) {
        return qry(b1, i) * i - qry(b2, i);
    }

    // cnt[l..r] += 1  (1-indexed)
    private void rangeAdd(int l, int r) {
        upd(b1, l, 1);
        upd(b1, r + 1, -1);
        upd(b2, l, l - 1);
        upd(b2, r + 1, -r);
    }

    public int sumCounts(int[] nums) {
        final long MOD = 1_000_000_007L;
        n = nums.length;
        b1 = new long[n + 2];
        b2 = new long[n + 2];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long Q = 0, ans = 0;
        for (int j = 0; j < n; j++) {
            int prev = last[nums[j]];
            int l = prev + 2;   // 1-indexed start of range
            int r = j + 1;      // 1-indexed end of range
            long s = prefix(r) - prefix(l - 1);
            Q += 2 * s + (r - l + 1);
            rangeAdd(l, r);
            ans = (ans + Q) % MOD;
            last[nums[j]] = j;
        }
        return (int) ans;
    }
}