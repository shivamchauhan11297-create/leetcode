class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long answer = 0;

        // If type 3 is cheaper than buying one type 1 + one type 2
        if (costBoth < cost1 + cost2) {

            int both = Math.min(need1, need2);

            answer += (long) both * costBoth;

            need1 -= both;
            need2 -= both;

            // Remaining type 1 requirement
            if (need1 > 0) {
                answer += (long) need1 * Math.min(cost1, costBoth);
            }

            // Remaining type 2 requirement
            if (need2 > 0) {
                answer += (long) need2 * Math.min(cost2, costBoth);
            }

        } else {

            // Type 3 is not cheaper than buying type 1 + type 2
            answer += (long) need1 * cost1;
            answer += (long) need2 * cost2;
        }

        return answer;
    }
}