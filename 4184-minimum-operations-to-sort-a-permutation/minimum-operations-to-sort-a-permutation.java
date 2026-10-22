import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        boolean sorted = true;
        for (int i = 0; i < n; i++) if (nums[i] != i) { sorted = false; break; }
        if (sorted) return 0;
        if (n <= 2) return 1;

        int a = nums[0];
        boolean isA = true, isB = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + a) % n) isA = false;
            if (nums[i] != (((a - i) % n) + n) % n) isB = false;
        }
        if (!isA && !isB) return -1;

        // state id: type * n + value  (type 0 = A, 1 = B)
        int start = isA ? a : n + a;
        int target = 0; // A with s = 0

        int[] dist = new int[2 * n];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[start] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == target) return dist[cur];
            int type = cur / n, v = cur % n;
            int nl, nr;
            if (type == 0) {
                nl = (v + 1) % n;                 // A(s) -> A(s+1)
                nr = n + ((v - 1 + n) % n);       // A(s) -> B(s-1)
            } else {
                nl = n + ((v - 1 + n) % n);       // B(t) -> B(t-1)
                nr = (v + 1) % n;                 // B(t) -> A(t+1)
            }
            if (dist[nl] == -1) { dist[nl] = dist[cur] + 1; q.add(nl); }
            if (dist[nr] == -1) { dist[nr] = dist[cur] + 1; q.add(nr); }
        }
        return -1;
    }
}