import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store queries with their original index
        int[][] q = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i]; // query time
            q[i][1] = i;          // original index
        }

        // Sort queries by time
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        int[] answer = new int[queries.length];

        // frequency[server] = number of requests currently in window
        int[] frequency = new int[n + 1];

        int left = 0;
        int right = 0;

        // Number of unique servers having at least one request
        int activeServers = 0;

        for (int[] query : q) {

            int time = query[0];
            int index = query[1];

            int start = time - x;

            // Add logs whose time <= query time
            while (right < logs.length && logs[right][1] <= time) {

                int server = logs[right][0];

                if (frequency[server] == 0) {
                    activeServers++;
                }

                frequency[server]++;
                right++;
            }

            // Remove logs whose time < start
            while (left < right && logs[left][1] < start) {

                int server = logs[left][0];

                frequency[server]--;

                if (frequency[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            // Servers without any request
            answer[index] = n - activeServers;
        }

        return answer;
    }
}