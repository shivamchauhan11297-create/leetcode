import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] c;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        c = new long[n + 1][n + 1];
        c[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> a) {
        if (a.size() <= 2) return 1;

        int root = a.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < a.size(); i++) {
            int v = a.get(i);
            if (v < root) left.add(v);
            else right.add(v);
        }

        long res = c[left.size() + right.size()][left.size()];
        res = res * dfs(left) % MOD;
        res = res * dfs(right) % MOD;
        return res;
    }
}