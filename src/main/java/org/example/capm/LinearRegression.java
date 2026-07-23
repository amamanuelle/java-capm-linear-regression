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

    public double predict(double x, RegressionResult result) {
        if (result == null) throw new IllegalArgumentException("Regression result cannot be null");
        return result.intercept() + result.slope() * x;
    }

    private double getAverage(double[] arr) {
        double sum = 0;
        for (double value : arr) {
            sum += value;
        }

        return sum / arr.length;
    }

    //R^2 = 1 - RSS/TSS
    public double calculateRSquared(double[] x, double[] y, RegressionResult result) {
        validate(x);
        validate(y);
        if (x.length != y.length) throw new IllegalArgumentException("Arrays must have the same length");
        if (x.length < 2) throw new IllegalArgumentException("At least two data points are required");
        if (result == null) throw new IllegalArgumentException("Regression result cannot be null");

        double rss = 0;
        double tss = 0;
        double yAvg = getAverage(y);
        for (int i = 0; i < x.length; i++) {
            double predicted = predict(x[i], result);
            double residual = y[i] - predicted;
            rss += residual * residual;

            double deviation = y[i] - yAvg;
            tss += deviation * deviation;
        }

        if (tss == 0) throw new IllegalArgumentException("R-squared cannot be calculated when all y values are equal");
        return 1 - (rss / tss);
    }
}
