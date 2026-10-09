class Solution {
    public int minInsertions(String s) {
        int ans = 0;   // insertions made
        int need = 0;  // ')' still required for open '('

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (need % 2 == 1) {   // previous '(' has only one ')', fix it
                    ans++;
                    need--;
                }
                need += 2;
            } else {
                need--;
                if (need == -1) {      // no '(' to match, insert one
                    ans++;
                    need = 1;          // new '(' needs 2, this ')' supplied 1
                }
            }
        }

        return ans + need;
    }
}