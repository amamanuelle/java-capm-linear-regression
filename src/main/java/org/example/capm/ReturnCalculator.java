package org.example.capm;

public class ReturnCalculator {

    public double[] calculateReturns(double[] prices) {
        if (prices == null || prices.length < 2) throw new IllegalArgumentException("Prices must not be null or have length smaller than two");

        double[] returns = new double[prices.length - 1];
        for (double price : prices) {
            if (price <= 0) {
                throw new IllegalArgumentException("Prices must be positive");
            }
        }

        for (int i = 1; i < prices.length; i++) {
            returns[i - 1] = (prices[i] - prices[i - 1]) / prices[i - 1];
        }

        return returns;
    }
}
