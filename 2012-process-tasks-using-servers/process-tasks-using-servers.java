import java.util.*;

class Solution {

    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Free servers:
        // smallest weight first, then smallest index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers:
        // earliest free time first, then smallest index
        PriorityQueue<long[]> busyServers = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                return Long.compare(a[1], b[1]);
            }
        );

        // Initially every server is free
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {

            // Task j arrives at second j
            time = Math.max(time, j);

            // Move all servers that have become free
            while (!busyServers.isEmpty()
                    && busyServers.peek()[0] <= time) {

                long[] server = busyServers.poll();

                int index = (int) server[1];

                freeServers.offer(
                    new int[]{servers[index], index}
                );
            }

            // If no server is free,
            // jump time to the earliest server's free time
            if (freeServers.isEmpty()) {

                time = busyServers.peek()[0];

                // Add all servers that become free at this time
                while (!busyServers.isEmpty()
                        && busyServers.peek()[0] <= time) {

                    long[] server = busyServers.poll();

                    int index = (int) server[1];

                    freeServers.offer(
                        new int[]{servers[index], index}
                    );
                }
            }

            // Select the best free server
            int[] server = freeServers.poll();

            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // Server will become free after this task finishes
            long freeTime = time + tasks[j];

            busyServers.offer(
                new long[]{freeTime, index}
            );
        }

        return ans;
    }
}