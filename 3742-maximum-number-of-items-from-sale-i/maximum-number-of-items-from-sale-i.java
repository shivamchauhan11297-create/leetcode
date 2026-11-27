import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // dp[b] = maximum copies obtained by activating
        // item types using at most b money.
        int[] dp = new int[budget + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int factor = items[i][0];
            int price = items[i][1];

            minPrice = Math.min(minPrice, price);

            // Count how many item types have
            // factor divisible by current factor.
            int value = 0;

            for (int j = 0; j < n; j++) {

                if (items[j][0] % factor == 0) {
                    value++;
                }
            }

            // 0/1 Knapsack
            // This item type can activate its bonus only once.
            for (int b = budget; b >= price; b--) {

                dp[b] = Math.max(
                    dp[b],
                    dp[b - price] + value
                );
            }
        }

        int answer = 0;

        // Spend remaining money on the cheapest item.
        for (int spent = 0; spent <= budget; spent++) {

            int extra = (budget - spent) / minPrice;

            int total = dp[spent] + extra;

            answer = Math.max(answer, total);
        }

        return answer;
    }
}