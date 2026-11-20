import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {

        // Count frequency of every number
        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // We only need to check values that can actually become targets
        TreeSet<Integer> values = new TreeSet<>();

        for (int num : nums) {
            values.add(num);
            values.add(num + 1);
        }

        int prev = Integer.MIN_VALUE;

        // dp0 = longest consecutive sequence ending at v
        //       where v was made using v-1
        int dp0 = -1000000000;

        // dp1 = longest consecutive sequence ending at v
        //       where v was made using v
        int dp1 = -1000000000;

        int answer = 1;

        for (int v : values) {

            // If v is not consecutive with previous target,
            // we must start a new sequence.
            if (v != prev + 1) {
                dp0 = -1000000000;
                dp1 = -1000000000;
            }

            int old0 = dp0;
            int old1 = dp1;

            int new0 = -1000000000;
            int new1 = -1000000000;

            // Option 1:
            // Make v using an original v
            if (count.getOrDefault(v, 0) >= 1) {

                // Start a new sequence
                new1 = 1;

                // Continue an existing sequence
                if (old0 > 0) {
                    new1 = Math.max(new1, old0 + 1);
                }

                if (old1 > 0) {
                    new1 = Math.max(new1, old1 + 1);
                }
            }

            // Option 2:
            // Make v using an original (v - 1)
            if (count.getOrDefault(v - 1, 0) >= 1) {

                // Start a new sequence
                new0 = 1;

                // Previous target did NOT use v-1,
                // so v-1 is still available.
                if (old0 > 0) {
                    new0 = Math.max(new0, old0 + 1);
                }

                // Previous target DID use one v-1.
                // Therefore we need at least 2 copies of v-1.
                if (old1 > 0 &&
                    count.getOrDefault(v - 1, 0) >= 2) {

                    new0 = Math.max(new0, old1 + 1);
                }
            }

            dp0 = new0;
            dp1 = new1;

            answer = Math.max(answer, Math.max(dp0, dp1));

            prev = v;
        }

        return answer;
    }
}