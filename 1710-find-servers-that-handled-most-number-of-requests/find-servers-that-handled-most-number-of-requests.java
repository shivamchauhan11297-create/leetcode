import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        // Stores currently free server IDs
        TreeSet<Integer> free = new TreeSet<>();

        // Initially, all servers are free
        for (int i = 0; i < k; i++) {
            free.add(i);
        }

        // Stores [finishTime, serverId]
        // Earliest finish time comes first
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                return Long.compare(a[1], b[1]);
            }
        );

        // Number of requests handled by each server
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            int currentTime = arrival[i];

            // Make finished servers free again
            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {

                long[] server = busy.poll();

                int serverId = (int) server[1];

                free.add(serverId);
            }

            // If all servers are busy, this request is dropped
            if (free.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find first free server >= preferred
            Integer serverId = free.ceiling(preferred);

            // If not found, wrap around to smallest server
            if (serverId == null) {
                serverId = free.first();
            }

            // Remove server from free set
            free.remove(serverId);

            // Server handled this request
            count[serverId]++;

            // Calculate when this server becomes free
            long finishTime = (long) currentTime + load[i];

            // Put server into busy queue
            busy.offer(new long[]{finishTime, serverId});
        }

        // Find maximum number of requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Return all servers having maximum count
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                answer.add(i);
            }
        }

        return answer;
    }
}