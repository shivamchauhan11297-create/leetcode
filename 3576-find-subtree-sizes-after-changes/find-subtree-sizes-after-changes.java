import java.util.*;

class Solution {

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        // Original tree
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        // newParent[i] = parent of i after the changes
        int[] newParent = parent.clone();

        // last[c] = closest ancestor having character c
        int[] last = new int[26];

        Arrays.fill(last, -1);

        // DFS order
        List<Integer> order = new ArrayList<>();

        dfs(0, tree, s, last, newParent, order);

        // Initially every node contains itself
        int[] answer = new int[n];

        Arrays.fill(answer, 1);

        // Process from children to parents
        for (int i = order.size() - 1; i > 0; i--) {

            int node = order.get(i);

            int p = newParent[node];

            answer[p] += answer[node];
        }

        return answer;
    }

    private void dfs(
            int node,
            List<Integer>[] tree,
            String s,
            int[] last,
            int[] newParent,
            List<Integer> order
    ) {

        order.add(node);

        int ch = s.charAt(node) - 'a';

        // Save previous closest ancestor
        int old = last[ch];

        // If an ancestor with same character exists,
        // make it the new parent
        if (last[ch] != -1) {
            newParent[node] = last[ch];
        }

        // Current node becomes the closest ancestor
        // for this character
        last[ch] = node;

        // Visit children
        for (int child : tree[node]) {
            dfs(child, tree, s, last, newParent, order);
        }

        // Restore previous ancestor
        last[ch] = old;
    }
}