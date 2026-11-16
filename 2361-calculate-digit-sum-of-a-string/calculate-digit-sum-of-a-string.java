class Solution {
    public String digitSum(String s, int k) {

        while (s.length() > k) {

            StringBuilder next = new StringBuilder();

            for (int i = 0; i < s.length(); i += k) {

                int sum = 0;

                // Process one group of k digits
                for (int j = i; j < i + k && j < s.length(); j++) {
                    sum += s.charAt(j) - '0';
                }

                // Add the digit sum to the new string
                next.append(sum);
            }

            s = next.toString();
        }

        return s;
    }
}