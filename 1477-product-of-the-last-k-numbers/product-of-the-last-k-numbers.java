import java.util.*;

class ProductOfNumbers {

    private List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();

        // Dummy value
        prefix.add(1);
    }

    public void add(int num) {

        // If num is 0, reset everything
        if (num == 0) {
            prefix.clear();
            prefix.add(1);
        }

        else {
            int lastProduct = prefix.get(prefix.size() - 1);

            prefix.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {

        // If there are fewer than k numbers
        // after the last zero, the answer is 0.
        if (k >= prefix.size()) {
            return 0;
        }

        int totalProduct = prefix.get(prefix.size() - 1);

        int productBeforeLastK =
                prefix.get(prefix.size() - 1 - k);

        return totalProduct / productBeforeLastK;
    }
}