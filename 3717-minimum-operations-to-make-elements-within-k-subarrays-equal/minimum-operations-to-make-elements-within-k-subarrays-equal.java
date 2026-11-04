import java.util.*;

class Solution {
    private int m;
    private long[] cnt, sm;

    private void upd(int i, int c, long v) {
        for (; i <= m; i += i & -i) {
            cnt[i] += c;
            sm[i] += v;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // coordinate compression
        int[] s = nums.clone();
        Arrays.sort(s);
        int u = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || s[i] != s[i - 1]) s[u++] = s[i];
        }
        m = u;
        cnt = new long[m + 1];
        sm = new long[m + 1];

        int[] rk = new int[n];
        for (int i = 0; i < n; i++) {
            rk[i] = Arrays.binarySearch(s, 0, u, nums[i]) + 1;
        }

        int w = n - x + 1;
        long[] cost = new long[w];
        int top = Integer.highestOneBit(m);
        int target = (x + 1) / 2;
        long windowSum = 0;

        for (int i = 0; i < n; i++) {
            upd(rk[i], 1, nums[i]);
            windowSum += nums[i];
            if (i >= x) {
                upd(rk[i - x], -1, -nums[i - x]);
                windowSum -= nums[i - x];
            }
            if (i >= x - 1) {
                // find smallest rank whose prefix count >= target
                int pos = 0, rem = target;
                for (int pw = top; pw > 0; pw >>= 1) {
                    if (pos + pw <= m && cnt[pos + pw] < rem) {
                        pos += pw;
                        rem -= cnt[pos];
                    }
                }
                int idx = pos + 1;
                long med = s[idx - 1];

                long c = 0, sumLE = 0;
                for (int j = idx; j > 0; j -= j & -j) {
                    c += cnt[j];
                    sumLE += sm[j];
                }
                cost[i - x + 1] = med * c - sumLE + (windowSum - sumLE) - med * (x - c);
            }
        }

        final long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) Arrays.fill(dp[j], INF);

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                long best = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] < INF) {
                    best = Math.min(best, dp[j - 1][i - x] + cost[i - x]);
                }
                dp[j][i] = best;
            }
        }
        return dp[k][n];
    }
}