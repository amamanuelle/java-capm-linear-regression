package org.example.capm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinearRegressionTest {
    LinearRegression linearRegression = new LinearRegression();

    @Test
    void exceptionTestNullX() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(null, new double[]{1, 2});
        });
    }

    @Test
    void exceptionTestNullY() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(new double[]{1, 2}, null);
        });
    }

    @Test
    void exceptionTestEmptyX() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(new double[0], new double[]{1, 2});
        });
    }

    @Test
    void exceptionTestEmptyY() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(new double[]{1, 2}, new double[0]);
        });
    }

    @Test
    void exceptionDifferentLengthTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(new double[]{1, 2}, new double[]{1});
        });
    }

    @Test
    void exceptionLengthTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(new double[]{1}, new double[]{1});
        });
    }

    @Test
    void exceptionDifferentZeroTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            linearRegression.fit(new double[]{0, 0}, new double[]{0, 0});
        });
    }

    @Test
    void normalTest() {
        RegressionResult result = new RegressionResult(0, 1);
        RegressionResult calculated = linearRegression.fit(new double[]{1, 2}, new double[]{1, 2});
        assertEquals(result, calculated);
    }

    @Test
    void perfectRegressionTest() {
        LinearRegression regression = new LinearRegression();

        double[] x = {1, 2, 3, 4};
        double[] y = {3, 5, 7, 9};

        RegressionResult result = regression.fit(x, y);

        assertEquals(1.0, result.intercept());
        assertEquals(2.0, result.slope());
        assertEquals(21.0, regression.predict(10, result));
        assertEquals(1.0, regression.calculateRSquared(x, y, result));
    }

    @Test
    void nullRRTest() {
        assertThrows(
                IllegalArgumentException.class,
                () -> linearRegression.predict(5.0, null)
        );
    }

    @Test
    void constantYValuesTest() {
        double[] x = {1, 2, 3};
        double[] y = {5, 5, 5};
        RegressionResult result = new RegressionResult(5.0, 0.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> linearRegression.calculateRSquared(x, y, result)
        );
    }
}
