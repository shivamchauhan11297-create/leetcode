class Solution {
    public int numOfSubarrays(int[] arr) {

        final int MOD = 1000000007;

        long even = 1;
        long odd = 0;

        long answer = 0;

        int sum = 0;

        for (int num : arr) {

            sum += num;

            if (sum % 2 == 0) {

                // Current prefix is even
                // Pair it with previous odd prefixes
                answer += odd;
                even++;

            } else {

                // Current prefix is odd
                // Pair it with previous even prefixes
                answer += even;
                odd++;
            }

            answer %= MOD;
        }

        return (int) answer;
    }
}