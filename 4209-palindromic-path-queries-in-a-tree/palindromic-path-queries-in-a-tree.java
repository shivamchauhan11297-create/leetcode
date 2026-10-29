import java.util.*;

class Solution {
    private int n;
    private int[] bit;

    private void add(int i, int v) {
        for (i++; i <= n + 1; i += i & -i) bit[i] ^= v;
    }

    private int get(int i) {
        int r = 0;
        for (i++; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        bit = new int[n + 3];

        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * (n - 1)];
        int[] to = new int[2 * (n - 1)];
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1]; nxt[ec] = head[e[0]]; head[e[0]] = ec++;
            to[ec] = e[0]; nxt[ec] = head[e[1]]; head[e[1]] = ec++;
        }

        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        int[][] up = new int[LOG][n];
        int[] depth = new int[n], par = new int[n];
        int[] tin = new int[n], tout = new int[n], pre = new int[n];
        char[] ch = s.toCharArray();

        int[] ptr = head.clone();
        int[] stack = new int[n];
        int sp = 0, timer = 0;
        par[0] = -1;
        pre[0] = 1 << (ch[0] - 'a');
        tin[0] = timer++;
        stack[sp++] = 0;
        while (sp > 0) {
            int u = stack[sp - 1];
            if (ptr[u] != -1) {
                int e = ptr[u];
                ptr[u] = nxt[e];
                int v = to[e];
                if (v == par[u]) continue;
                par[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                pre[v] = pre[u] ^ (1 << (ch[v] - 'a'));
                tin[v] = timer++;
                stack[sp++] = v;
            } else {
                tout[u] = timer - 1;
                sp--;
            }
        }
        for (int k = 1; k < LOG; k++) {
            for (int v = 0; v < n; v++) {
                up[k][v] = up[k - 1][up[k - 1][v]];
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                int delta = (1 << (ch[u] - 'a')) ^ (1 << (c - 'a'));
                if (delta != 0) {
                    add(tin[u], delta);
                    add(tout[u] + 1, delta);
                    ch[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                // find LCA
                int a = u, b = v;
                if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
                int diff = depth[a] - depth[b];
                for (int k = 0; k < LOG; k++) {
                    if (((diff >> k) & 1) != 0) a = up[k][a];
                }
                if (a != b) {
                    for (int k = LOG - 1; k >= 0; k--) {
                        if (up[k][a] != up[k][b]) {
                            a = up[k][a];
                            b = up[k][b];
                        }
                    }
                    a = up[0][a];
                }
                int lca = a;

                int mask = (pre[u] ^ get(tin[u]))
                         ^ (pre[v] ^ get(tin[v]))
                         ^ (1 << (ch[lca] - 'a'));
                res.add((mask & (mask - 1)) == 0);
            }
        }
        return res;
    }
}