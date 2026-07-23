import org.example.capm.LinearRegression;
import org.example.capm.RegressionResult;
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
}
