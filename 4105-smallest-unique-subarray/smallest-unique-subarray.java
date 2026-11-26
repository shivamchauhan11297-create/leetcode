import java.util.*;

class Solution {

    static class State {
        int len;
        int link;
        int occ;
        HashMap<Integer, Integer> next = new HashMap<>();

        State() {
            len = 0;
            link = -1;
            occ = 0;
        }
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        // A suffix automaton has at most 2*n states
        State[] st = new State[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            st[i] = new State();
        }

        int size = 1;
        int last = 0;

        // Build suffix automaton
        for (int x : nums) {

            int cur = size++;
            st[cur].len = st[last].len + 1;
            st[cur].occ = 1;

            int p = last;

            while (p != -1 && !st[p].next.containsKey(x)) {
                st[p].next.put(x, cur);
                p = st[p].link;
            }

            if (p == -1) {
                st[cur].link = 0;
            } else {
                int q = st[p].next.get(x);

                if (st[p].len + 1 == st[q].len) {
                    st[cur].link = q;
                } else {

                    int clone = size++;

                    st[clone].len = st[p].len + 1;
                    st[clone].link = st[q].link;
                    st[clone].next.putAll(st[q].next);

                    while (p != -1 &&
                           st[p].next.getOrDefault(x, -1) == q) {

                        st[p].next.put(x, clone);
                        p = st[p].link;
                    }

                    st[q].link = clone;
                    st[cur].link = clone;
                }
            }

            last = cur;
        }

        // Sort states by length
        int[] count = new int[n + 1];

        for (int i = 0; i < size; i++) {
            count[st[i].len]++;
        }

        for (int i = 1; i <= n; i++) {
            count[i] += count[i - 1];
        }

        int[] order = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            int len = st[i].len;
            order[--count[len]] = i;
        }

        // Propagate occurrence counts from longer states
        // to their suffix links.
        for (int i = size - 1; i > 0; i--) {
            int v = order[i];
            int parent = st[v].link;

            if (parent != -1) {
                st[parent].occ += st[v].occ;
            }
        }

        int answer = n;

        // Find shortest substring that occurs exactly once
        for (int i = 1; i < size; i++) {

            if (st[i].occ == 1) {

                int shortestLength =
                        st[st[i].link].len + 1;

                answer = Math.min(answer, shortestLength);
            }
        }

        return answer;
    }
}