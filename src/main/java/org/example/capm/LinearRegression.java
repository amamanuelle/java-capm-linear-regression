package org.example.capm;

public class LinearRegression {

    public RegressionResult fit(double[] x, double[] y) {
        validate(x);
        validate(y);

        if (x.length != y.length) throw new IllegalArgumentException("Arrays must have same length");
        if (x.length < 2) throw new IllegalArgumentException("At least two data points are required");

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXSquared = 0;
        int n = x.length;


        for (int i = 0; i < x.length; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumXSquared += x[i] * x[i];
        }
        double xAvg = sumX / n;
        double yAvg = sumY / n;

        double denominator = n * sumXSquared - (sumX * sumX);
        if (denominator == 0) throw new IllegalArgumentException("Regression cannot be calculated when all x values are equal");

        double bHat = (n * sumXY - (sumX * sumY)) / denominator;
        double aHat = yAvg - bHat * xAvg;

        return new RegressionResult(aHat, bHat);
    }

    private void validate(double[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array cannot be null or empty");
    }
}
