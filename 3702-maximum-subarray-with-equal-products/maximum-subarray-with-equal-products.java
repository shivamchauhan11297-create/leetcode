import java.math.BigInteger;

class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int answer = 1;

        for (int i = 0; i < n; i++) {

            BigInteger product = BigInteger.ONE;
            int gcd = 0;
            BigInteger lcm = BigInteger.ONE;

            for (int j = i; j < n; j++) {

                int x = nums[j];

                // Update product
                product = product.multiply(BigInteger.valueOf(x));

                // Update GCD
                gcd = gcd(gcd, x);

                // Update LCM
                BigInteger xBig = BigInteger.valueOf(x);

                BigInteger currentGcd =
                        lcm.gcd(xBig);

                lcm = lcm
                        .divide(currentGcd)
                        .multiply(xBig);

                // Check product == GCD × LCM
                BigInteger gcdBig = BigInteger.valueOf(gcd);

                BigInteger gcdTimesLcm =
                        gcdBig.multiply(lcm);

                if (product.equals(gcdTimesLcm)) {
                    answer = Math.max(answer, j - i + 1);
                }
            }
        }

        return answer;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}