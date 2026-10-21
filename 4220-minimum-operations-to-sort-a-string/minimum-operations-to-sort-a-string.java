class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) { sorted = false; break; }
        }
        if (sorted) return 0;
        if (n == 2) return -1;

        char mn = 'z', mx = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;

        // first strictly greater than all others, last strictly smaller than all others
        boolean firstUniqueMax = true, lastUniqueMin = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) >= s.charAt(0)) { firstUniqueMax = false; break; }
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) <= s.charAt(n - 1)) { lastUniqueMin = false; break; }
        }
        return (firstUniqueMax && lastUniqueMin) ? 3 : 2;
    }
}